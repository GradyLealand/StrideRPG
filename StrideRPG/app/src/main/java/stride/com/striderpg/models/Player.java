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
     * Construct a Player using a FirebaseUser object to create the player object.
     * @param user FirebaseUser Object to retrieve information about the player.
     */
    public Player(FirebaseUser user) {

    }
}
