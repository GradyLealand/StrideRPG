package stride.com.striderpg.database;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import stride.com.striderpg.rpg.models.Player.Player;

/**
 * Database Utility Class to control and simplify saving user data
 * inside of the Firebase Database.
 */
public class FirebaseDBUtil {

    /**
     * FirebaseDBUtil Logging tag.
     */
    private static final String TAG = "FirebaseDBUtil";

    /**
     * Globally available private DatabaseReference used to
     * interact with the FirebaseDatabase. It will be instantiated
     * with a usable reference on Construction.
     */
    private final DatabaseReference database;

    /**
     * Constructor to set the database variable to the current
     * FirebaseDatabase Instance using the getReference() method.
     */
    public FirebaseDBUtil() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Push the specified Player directly to the Firebase Database.
     * When a Player is pushed to the database. Firebase will take care
     * of the CRUD functionality directly by adding/removing/updating
     * the players node on a successful push.
     */
    public void pushPlayer(Player player) {
        // Access specified player in the database by traversing the
        // Firebase Database (users -> uniqueId -> player).
        try {
            database.child(DBKeys.USERS_KEY)
                    .child(player.getUniqueId())
                    .setValue(player);
        } catch (Exception e) {
            Log.e(TAG, "pushPlayer:error", e);
        }
    }
}
