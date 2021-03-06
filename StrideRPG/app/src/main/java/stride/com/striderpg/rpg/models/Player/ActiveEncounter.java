package stride.com.striderpg.rpg.models.Player;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.generators.ActivityGenerator;
import stride.com.striderpg.rpg.models.Activity.Activity;
import stride.com.striderpg.rpg.models.Enemy.Boss;
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
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Check if a Boss has expired based on the current DateTime.
     */
    public boolean checkExpired() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return now.isAfter(TimeParser.parseTimestamp(boss.getExpiration()));
    }

    /**
     * Method for resetting and expiring a Boss once the checkExpired is True.
     */
    public void expire() {
        // Update Players Stats/QuestLog on Boss expire.
        G.activePlayer.getStats().updateBossesExpired();
        G.activePlayer.getQuestLog().update(Enums.QuestType.FAIL_DEFEAT_BOSSES, 1);

        Activity bossExpireActivity = ActivityGenerator.generateActiveEncounterActivity(
                Enums.ActivityType.BOSS_EXPIRE,
                boss
        );

        // Fire PropertyChange event for the expired ActiveEncounter.
        changes.firePropertyChange(Constants.PROPERTY_ACTIVE_ENCOUNTER_EXPIRES, null, bossExpireActivity);

        // Reset the Players ActiveEncounter object.
        active = false;
        boss = null;
    }

    /**
     * Attack the current Boss and decrement it's health by the steps passed to the method.
     */
    public void attackBoss(Integer steps) {
        // Decrease Bosses health by the amount of steps passed to method.
        boss.setHealth(boss.getHealth() - steps);

        // Ensure bosses health isn't less than 0.
        if (boss.getHealth() < 0) {
            boss.setHealth(0);
        }
        changes.firePropertyChange(Constants.PROPERTY_ACTIVE_ENCOUNTER_UPDATE_HEALTH, null, boss.getHealth());
    }

    /**
     * Defeat the current Boss and set Players experience and statistics properties.
     */
    public void defeatBoss() {
        // Increment Active Players experience with Experience reward from Boss.
        G.activePlayer.setExperience(G.activePlayer.getExperience() + boss.getExperienceReward());

        // Update Players Bestiary/Quests/Stats on Boss defeat.
        G.activePlayer.getBestiary().update(this.boss.getType());
        G.activePlayer.getQuestLog().update(Enums.QuestType.DEFEAT_BOSSES, 1);
        G.activePlayer.getStats().updateBossesDefeated();

        // Check for Active Player level up.
        if (G.activePlayer.canLevelUp()) {
            G.activePlayer.levelUp();
        }

        // Create an ArrayList of Activities that will hold the Activities for each Item
        // that was equipped from defeating the Boss.
        ArrayList<Activity> newLootActivities = new ArrayList<>();

        // Give Player the Items rewarded from Boss fight.
        for (Item reward : boss.getRewards()) {

            // Create a temporary Item to hold the Active Players current Item of the Type
            // generated from defeating the Boss.
            Item temp = G.activePlayer.getEquipment().getItem(reward.getType());

            // Compare both Item's, if the reward Item's stat boost is greater than
            // the temporary Item storing active Player's current Item, then replace.
            if (reward.compare(temp)) {

                G.activePlayer.getEquipment().replaceItem(reward.getType(), reward);

                // Generate an Activity that corresponds to this new Loot
                // being equipped by the Player.
                newLootActivities.add(ActivityGenerator.generateLootActivity(reward));
            }
        }

        // Generate the corresponding Activity to this Boss being defeated.
        Activity bossDefeatActivity = ActivityGenerator.generateActiveEncounterActivity(
                Enums.ActivityType.BOSS_DEFEAT,
                boss
        );

        // Reset the ActiveEncounter.
        active = false;
        boss = null;

        // Fire PropertyChange event so UI knows the ActiveEncounter has ended.
        changes.firePropertyChange(Constants.PROPERTY_ACTIVE_ENCOUNTER_FINISH, null, bossDefeatActivity);

        // Check for any new Activities that should be appended to the ActivityLog.
        if (newLootActivities.size() != 0) {
            for (Activity activity : newLootActivities) {
                // Add the new Activity to the Players ActivityLog. This allows for the
                // Activity to be displayed in the Users Dashboard.
                G.activePlayer.getActivityLog().addOnlineActivity(activity);
            }
        }
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
