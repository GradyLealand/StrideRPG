package stride.com.striderpg.rpg.models.Player;


import com.google.firebase.auth.FirebaseUser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;
import java.util.Random;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.generators.ItemGenerator;
import stride.com.striderpg.rpg.generators.LevelGenerator;
import stride.com.striderpg.rpg.generators.OnlineGenerator;
import stride.com.striderpg.rpg.models.Bestiary.Bestiary;
import stride.com.striderpg.rpg.utils.TimeParser;
import stride.com.striderpg.rpg.models.Enemy.Monster;


/**
 * A Player class to represent a Users account information and player information encapsulated
 * in one Object.
 */
public class Player {

    /**
     * PropertyChangedSupport object to deal with raising events
     * when a Property on this object/bean is changed.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /**
     * Player unique identifier.
     */
    private String uniqueId;

    /**
     * Player current username.
     */
    private String username;

    /**
     * Player email property.
     */
    private String email;

    /**
     * Player current level.
     */
    private Integer level;

    /**
     * The total amount of experience a Player has accumulated.
     */
    private Integer experience;

    /**
     * The total amount of steps this Player has taken.
     */
    private Integer steps;

    /**
     * The last time the user has signed in.
     */
    private String lastSignedIn;

    /**
     * ActiveEncounter container for holding the Players
     * current Active boss fight if one exists.
     */
    private ActiveEncounter activeEncounter;

    /**
     * Players Bestiary information.
     */
    private Bestiary bestiary;

    /**
     * Players Quests information.
     */
    private QuestLog questLog;

    /**
     * Players activityLog and log.
     */
    private ActivityLog activityLog;

    /**
     * Reference to this players stats object.
     */
    private Stats stats;

    /**
     * Reference to this players skills object.
     */
    private Skills skills;

    /**
     * Reference to this players equipment object.
     */
    private Equipment equipment;

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Player.class).
     */
    public Player() { }

    /**
     * Construct a Player using a FirebaseUser object to create an
     * empty (new) player object.
     */
    public Player(FirebaseUser user) {
        this.uniqueId = user.getUid();
        this.username = user.getDisplayName();
        this.email = user.getEmail();
        this.level = 1;
        this.experience = 0;
        this.steps = 0;
        this.lastSignedIn = TimeParser.makeTimestamp();

        this.activeEncounter = new ActiveEncounter();
        this.bestiary = new Bestiary();
        this.questLog = new QuestLog();
        this.activityLog = new ActivityLog();
        this.stats = new Stats();
        this.skills = new Skills();
        this.equipment = ItemGenerator.generateDefaultInventory(this);
    }

    /**
     * Implementation of a Players toString method to print out the
     * properties of a Player object.
     */
    @Override
    public String toString() {
        return "Player{" +
                "uniqueId='" + uniqueId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", steps=" + steps +
                ", lastSignedIn='" + lastSignedIn + '\'' +
                ", activeEncounter=" + activeEncounter +
                ", bestiary=" + bestiary +
                ", questLog=" + questLog +
                ", activityLog=" + activityLog +
                ", stats=" + stats +
                ", skills=" + skills +
                ", equipment=" + equipment +
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
     * Determines if a Player can level up or not by comparing
     * their experience with the experience required to reach their next level.
     */
    public boolean canLevelUp() {
        return this.getExperience() > LevelGenerator.experienceToNextLevel(this.getLevel());
    }

    /**
     * Level a Player up by incrementing their level property by one.
     * Uses the public level property setter so a property change
     * event is fired. A Players exp is also set on a level up.
     */
    public void levelUp() {
        // Set users experience to the proper amount so exp is carried over on level up.
        this.setExperience(this.getExperience() - LevelGenerator.experienceToNextLevel(this.getLevel()));
        this.setLevel(this.getLevel() + 1);

        // Give the Player new skill points that can be assigned later.
        this.skills.gainSkillPoints();
    }

    /**
     * Calculate and update a users steps property based on the amount
     * of total steps for the current day.
     */
    public void updateSteps(Integer total) {
        // Initial check for a null step count (first check on app start-up).
        // Secondary check for total being less than lastStepCount (midnight fitness reset).
        if (G.lastStepCount == null || total < G.lastStepCount) {
            G.lastStepCount = total;
        } else {
            if (total >= 0) {
                Integer steps = (total - G.lastStepCount) + 500;

                // Increment user steps by new steps calculated.
                this.setSteps(this.getSteps() + (steps));

                // Increment users experience by new steps calculated divided by
                // the experience step modifier.
                this.setExperience(this.getExperience() +
                        (steps / Constants.PLAYER_STEPS_EXP_MODIFIER));

                // Check if a Player can level up after step and exp changes have been made.
                if (this.canLevelUp()) {
                    this.levelUp();
                }

                // Update Players Quest for steps taken.
                this.getQuestLog().update(Enums.QuestType.TAKE_STEPS, steps);

                // Finally, set the last step count to the total from the readData() call
                // in the FitnessUtil.
                G.lastStepCount = total;

                // Also increment the Global session steps counter and then do a
                // check to see if an Activity will be generated for the user.
                G.onlineActivitySteps += steps;
                OnlineGenerator.calculateOnlineActivity();

                // Check that the ActiveEncounter hasn't expired.
                if (this.activeEncounter.isActive()) {
                    if (this.activeEncounter.checkExpired()) {
                        this.activeEncounter.expire();
                        return;
                    }

                    // and new steps if amount isn't == 0.
                    if (steps != 0) {
                        activeEncounter.attackBoss(steps);
                        if (activeEncounter.getBoss().getHealth() <= 0) {
                            activeEncounter.defeatBoss();
                        }
                    }
                }
            }
        }
    }

    /**
     * Calculate and update a users experience based on the enemy
     * passed to the method.
     */
    public boolean fightEnemy(Monster enemy) {
        int totalStrength = this.skills.getStrength() + this.getEquipment().getItem(Enums.ItemType.WEAPON).getStatBoost();
        int totalVitality = this.skills.getVitality() + this.getEquipment().getItem(Enums.ItemType.HELMET).getStatBoost();

        // Create a new Random instance for rolling.
        Random r = new Random();

        // Random roll to modify player attack from skills.
        int roll = r.nextInt(Constants.OFFLINE_BATTLE_MODIFIER);

        // Calculate attack value from Player strength property
        // and Player vitality property.
        int attack = ((totalStrength + totalVitality) / 2) + roll;

        // Check here for fight results, Player may defeat or be defeated by Monster.
        if (attack >= enemy.getHealth()) {
            // Increment Players current experience by the Enemies experience reward.
            this.setExperience(this.getExperience() + enemy.getExperienceReward());

            // Check if the Player can level up after defeating the Monster.
            if (this.canLevelUp()) {
                this.levelUp();
            }
            return true;
        } else {
            // Increment Players current experience by the Enemies level
            this.setExperience(this.getExperience() + enemy.getLevel());

            // Check if the player can level up after being defeated
            if (this.canLevelUp()) {
                this.levelUp();
            }
            return false;
        }
    }

    public String getUniqueId() {
        return uniqueId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        if (!Objects.equals(this.level, level))
            changes.firePropertyChange(Constants.PROPERTY_LEVEL, this.level, level);
        this.level = level;
    }
    public Integer getExperience() {
        return experience;
    }
    public void setExperience(Integer experience) {
        if (!Objects.equals(this.experience, experience))
            changes.firePropertyChange(Constants.PROPERTY_EXP, this.experience, experience);
        this.experience = experience;
    }
    public Integer getSteps() {
        return steps;
    }
    public void setSteps(Integer steps) {
        if (!Objects.equals(this.steps, steps))
            changes.firePropertyChange(Constants.PROPERTY_STEPS, this.steps, steps);
        this.steps = steps;
    }
    public String getLastSignedIn() {
        return lastSignedIn;
    }
    public void setLastSignedIn(String lastSignedIn) {
        this.lastSignedIn = lastSignedIn;
    }
    public ActiveEncounter getActiveEncounter() {
        return activeEncounter;
    }
    public Bestiary getBestiary() {
        return bestiary;
    }
    public QuestLog getQuestLog() {
        return questLog;
    }
    public void setQuestLog(QuestLog questLog) {
        this.questLog = questLog;
    }
    public ActivityLog getActivityLog() {
        return activityLog;
    }
    public void setActivityLog(ActivityLog activityLog) {
        this.activityLog = activityLog;
    }
    public Stats getStats() {
        return stats;
    }
    public Skills getSkills() {
        return skills;
    }
    public Equipment getEquipment() {
        return equipment;
    }
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}