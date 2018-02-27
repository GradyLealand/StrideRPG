package stride.com.striderpg.rpg.models.Player;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.generators.ActivityGenerator;
import stride.com.striderpg.rpg.models.Activity.Activity;
import stride.com.striderpg.rpg.models.Encounter.Boss;
import stride.com.striderpg.rpg.models.Item.Item;
import stride.com.striderpg.rpg.utils.TimeParser;

/**
 * ActiveEncounter container class for the Players Boss battle encounter
 * when one is active, the Boss property will not be null. But the active boolean
 * will be false, vice versa on active Encounter.
 */
public class ActiveEncounter {

    /**
     * PropertyChangedSupport to deal with raising events
     * when a Property on this object/bean is changed.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /**
     * Active boolean property for determining if an Encounter is currently
     * active. Instantiated as false so a node is available in the Database.
     */
    private boolean active = false;

    /**
     * Encounter Boss property. Contains all information about the Boss enemy.
     */
    private Boss boss;

    /**
     * Default Constructor for calls to
     * DataSnapshot.getValue(ActiveEncounter.class).
     */
    public ActiveEncounter() { }

    /**
     * Construct an ActiveEncounter using the properties passed to method.
     */
    public ActiveEncounter(boolean active, Boss activeEncounter) {
        this.active = active;
        this.boss = activeEncounter;
    }

    /**
     * Implementation of a ActiveEncounters toString method to print out the
     * properties of a ActiveEncounter object.
     * @return Properties of the ActiveEncounter object.
     */
    @Override
    public String toString() {
        return "ActiveEncounter{" +
                "active=" + active +
                ", boss=" + boss +
                '}';
    }

    /**
     * Attach a new PropertyChangeListener to this classes
     * PropertyChangeSupport object.
     * @param listener Listener implementation.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Check if a Boss has expired based on the current DateTime.
     * @return Boolean if Boss has expired.
     */
    public boolean checkExpired() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return now.isAfter(TimeParser.parseTimestamp(boss.getExpiration()));
    }

    public void expire() {
        Activity bossExpireActivity = ActivityGenerator.generateActivity(
                Enums.ActivityType.BOSS_EXPIRE,
                boss
        );

        // Fire PropertyChange event for the expired ActiveEncounter.
        changes.firePropertyChange(Constants.PROPERTY_ACTIVE_ENCOUNTER_EXPIRES, null, bossExpireActivity);

        // Reset the Players ActiveEncounter object.
        active = false;
        boss = null;
    }

    public void attackBoss(Integer steps) {
        // Decrease bosses health by the amount of steps this turn.
        boss.setHealth(boss.getHealth() - steps);

        // Ensure bosses health isn't less than 0.
        if (boss.getHealth() < 0) {
            boss.setHealth(0);

        }
        changes.firePropertyChange(Constants.PROPERTY_ACTIVE_ENCOUNTER_UPDATE_HEALTH, null, boss.getHealth());
    }

    public void defeatBoss() {
        // Increment Active Players experience with Experience reward from Boss.
        G.activePlayer.setExperience(G.activePlayer.getExperience() + boss.getEncounterExperienceReward());

        // Check for Active Player level up.
        if (G.activePlayer.canLevelUp()) {
            G.activePlayer.levelUp();
        }

        // Give Player the Items rewarded from Boss fight.
        for (Item reward : boss.getRewards()) {
            if (reward.isBetter(G.activePlayer.getEquipment().getItem(reward.getItemType()))) {
                G.activePlayer.getEquipment().replaceItem(reward.getItemType(), reward);
            }
        }

        Activity bossDefeatActvity = ActivityGenerator.generateActivity(
                Enums.ActivityType.BOSS_DEFEAT,
                boss
        );

        // Reset the ActiveEncounter.
        active = false;
        boss = null;

        // Fire PropertyChange event so UI knows the ActiveEncounter has ended.
        changes.firePropertyChange(Constants.PROPERTY_ACTIVE_ENCOUNTER_FINISH, null, bossDefeatActvity);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
        changes.firePropertyChange(Constants.PROPERTY_ACTIVE_ENCOUNTER_SET, null, boss);
    }
}
