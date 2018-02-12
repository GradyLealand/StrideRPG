package stride.com.striderpg.rpg.models.Player;


import com.google.firebase.auth.FirebaseUser;

/**
 * A Player class to represent a Users account information and player information encapsulated
 * in one Object.
 */
public class Player {

    /**
     * Every player has a unique identifier attached to their account.
     */
    private String uniqueId;

    /**
     * Every player has an email attached to the Google Account they chose
     * to sign in with on authentication.
     */
    private String email;

    /**
     * Player Username. Used to identify a player in a human friendly way.
     */
    private String username;

    /**
     * Player Level, as a player plays the game and participates in activities, they are
     * rewarded experience, which will add to their level over time.
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
     * Reference to this players skills object.
     */
    private Skills skills;

    /**
     * Reference to this players inventory object.
     */
    private Inventory inventory;

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
        this.email = user.getEmail();
        this.username = user.getDisplayName();

        this.level = 1;
        this.experience = 0;
        this.steps = 0;

        this.skills = new Skills();
        this.inventory = new Inventory();
    }

    /**
     * Implementation of a Players toString method to print out the properties of a Player object.
     * @return Properties of the Player object.
     */
    @Override
    public String toString() {
        return "Player{" +
                "uniqueId='" + uniqueId + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", steps=" + steps +
                ", skills=" + skills +
                ", inventory=" + inventory +
                '}';
    }

    /**
     * Get a Players unique identifier.
     * @return Player unique token.
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * Get a Players email address.
     * @return Player email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get a Players username.
     * @return Player username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Set a Players username.
     * @param username New Player username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get a Players current level.
     * @return Player current level.
     */
    public Integer getLevel() {
        return level;
    }
    /**
     * Set a Players current level.
     * @param level New Player level.
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Get a Players current experience amount.
     * @return Player current experience.
     */
    public Integer getExperience() {
        return experience;
    }
    /**
     * Set a Players current experience amount.
     * @param experience New Player experience.
     */
    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    /**
     * Get a Players current steps amount.
     * @return Player current steps.
     */
    public Integer getSteps() {
        return steps;
    }
    /**
     * Set a Players current steps amount.
     * @param steps New Player steps.
     */
    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    /**
     * Get a Players Skills object.
     * @return Player Skills object.
     */
    public Skills getSkills() {
        return skills;
    }

    /**
     * Get a Players inventory.
     * @return Player inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Set a Players inventory.
     * @param inventory New Player inventory.
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
