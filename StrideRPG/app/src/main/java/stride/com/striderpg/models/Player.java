package stride.com.striderpg.models;


import com.google.firebase.auth.FirebaseUser;

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
     * Default constructor required for calls to
     * DataSnapshot.getValue(User.class).
     */
    public Player() { }

    /**
     * Construct a Player using a FirebaseUser object to create the player object.
     * @param user FirebaseUser Object to retrieve information about the player.
     */
    public Player(FirebaseUser user) {
        this.uniqueId = user.getUid();
        this.email = user.getEmail();
        this.username = user.getDisplayName();
        this.level = 0;
        this.experience = 0;
    }

    /**
     * Implementation of a Players toString method to print out the properties of a Player.
     *
     * @return Properties of the Player.
     */
    @Override
    public String toString() {
        return "Player{" +
                "uniqueId='" + uniqueId + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", level=" + level +
                ", experience=" + experience +
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
}
