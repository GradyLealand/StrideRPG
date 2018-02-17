package stride.com.striderpg.rpg.models.Player;


import com.google.firebase.auth.FirebaseUser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

import stride.com.striderpg.database.DBKeys;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Generators.ItemGenerator;
import stride.com.striderpg.rpg.Generators.LevelGenerator;


/**
 * A Player class to represent a Users account information and player information encapsulated
 * in one Object.
 */
public class Player {

    /**
     * PropertyChangedSupport object to deal with raising events when a Property on
     * this object/bean is changed.
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
     * Players history and log.
     */
    private History history;

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
     * Construct a Player using a FirebaseUser object to create an empty (new) player object.
     * @param user FirebaseUser Object to retrieve information about the player.
     */
    public Player(FirebaseUser user) {
        this.uniqueId = user.getUid();
        this.username = user.getDisplayName();
        this.email = user.getEmail();
        this.level = 1;
        this.experience = 200;
        this.steps = 0;

        this.history = new History();
        this.stats = new Stats();
        this.skills = new Skills(Constants.PLAYER_DEFAULT_VITALITY, Constants.PLAYER_DEFAULT_STRENGTH, Constants.PLAYER_DEFAULT_SPEED);
        this.equipment = ItemGenerator.generateDefaultInventory(this);
    }

    /**
     * Implementation of a Players toString method to print out the properties of a Player object.
     * @return Properties of the Player object.
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
                ", history=" + history +
                ", stats=" + stats +
                ", skills=" + skills +
                ", equipment=" + equipment +
                '}';
    }

    /**
     * Attach a new PropertyChangeListener to this classes PropertyChangeSupport object.
     * @param listener Listener implementation.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Determines if a Player can level up or not by comparing their experience with the
     * experience required to reach their next level.
     * @return Boolean for if Player should be levelled up.
     */
    public boolean canLevelUp() {
        return this.getExperience() > LevelGenerator.experienceToNextLevel(this.getLevel());
    }

    /**
     * Level a Player up by incrementing their level property by one. Uses the public level
     * property setter so a property change event is fired. A Players exp is also set on a level up.
     */
    public void levelUp() {
        // Set users experience to the proper amount so exp is carried over on level up.
        this.setExperience(this.getExperience() -
                LevelGenerator.experienceToNextLevel(this.getLevel()));
        this.setLevel(this.getLevel() + 1);
    }

    /**
     * Calculate and update a users steps property based on the amount of
     * total steps for the current day.
     * @param total Total steps taken from Fitness api for current day.
     */
    public void updateSteps(Integer total) {
        if (G.lastStepCount == null) {
            G.lastStepCount = total;
        } else {
            if (total >= 0) {
                // Increment user steps by new steps calculated.
                this.setSteps(this.getSteps() + (total - G.lastStepCount));

                // Increment users experience by new steps calculated divided by
                // the experience step modifier.
                this.setExperience(this.getExperience() +
                        (total - G.lastStepCount) /
                                Constants.PLAYER_STEPS_EXP_MODIFIER);

                // Check if a Player can level up after step and exp changes have been made.
                if (this.canLevelUp()) {
                    this.levelUp();
                }

                // Finally, set the last step count to the total from our readData() call
                // in the FitnessUtil.
                G.lastStepCount = total;
            }
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
            changes.firePropertyChange(DBKeys.LEVEL_KEY, this.level, level);
        this.level = level;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        if (!Objects.equals(this.experience, experience))
            changes.firePropertyChange(DBKeys.EXPERIENCE_KEY, this.experience, experience);
        this.experience = experience;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        if (!Objects.equals(this.steps, steps))
            changes.firePropertyChange(DBKeys.STEPS_KEY, this.steps, steps);
        this.steps = steps;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
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