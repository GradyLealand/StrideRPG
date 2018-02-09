package stride.com.striderpg.database;


import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import stride.com.striderpg.models.Player.Player;

/**
 * Database Utility class to encapsulate any data transfer from the FirebaseDatabase to the application
 * or vice versa, application to the FirebaseDatabase.
 */
public class FirebaseDBUtil {

    /**
     * Globally available private DatabaseReference used to interact with the
     * FirebaseDatabase. It will be instantiated with a usable reference on Construction.
     */
    private DatabaseReference database;

    /**
     * Constructor to set the database variable to the current FirebaseDatabase Instance
     * using the getReference() method.
     */
    public FirebaseDBUtil() {
        // Get a reference to the FirebaseDatabase.
        database = FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Add a Player directly to the Database with their UID as the key and their
     * relevant information as the child to that key.
     * @param user FirebaseUser that will be added to the Database as a new Player.
     */
    public void addPlayer(FirebaseUser user) {
        // Generate the uniqueId for the Player object here. This will be used as
        // the identifier for Player in memory as well as the player in the Database.
        String playerId = user.getUid();

        // Construct a new Player object with the given FirebaseUser.
        Player player = new Player(user);

        // Append this new Player to the Database as a child of the "users" key.
        database.child(DBKeys.USERS_KEY).child(playerId).setValue(player);
    }

    /**
     * Push the ActivePlayer from the Globals class directly to the FirebaseDatabase. Any data
     * that has been changed in this Player since the last push will be updated.
     * @param player Globals.activePlayer Player object to Push to FirebaseDatabase.
     */
    public void pushActivePlayer(Player player) {
        database.child(DBKeys.USERS_KEY).child(player.getUniqueId()).setValue(player);
    }
}
