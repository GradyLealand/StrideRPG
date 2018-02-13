package stride.com.striderpg.database;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import stride.com.striderpg.global.G;

/**
 * Database Utility class to encapsulate any data transfer from the FirebaseDatabase to the application
 * or vice versa, application to the FirebaseDatabase.
 */
public class FirebaseDBUtil {

    /**
     * Globally available private DatabaseReference used to interact with the
     * FirebaseDatabase. It will be instantiated with a usable reference on Construction.
     */
    private final DatabaseReference database;

    /**
     * Constructor to set the database variable to the current FirebaseDatabase Instance
     * using the getReference() method.
     */
    public FirebaseDBUtil() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Push the ActivePlayer from the G class directly to the FirebaseDatabase. Any data
     * that has been changed in this Player since the last push will be updated.
     */
    public void pushActivePlayer() {
        database.child(DBKeys.USERS_KEY)
                .child(G.activePlayer.getUniqueId())
                .setValue(G.activePlayer);
    }
}
