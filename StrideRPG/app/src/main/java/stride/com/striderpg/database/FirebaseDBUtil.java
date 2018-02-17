package stride.com.striderpg.database;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * Database Utility class to encapsulate any data transfer from the FirebaseDatabase to the application
 * or vice versa, application to the FirebaseDatabase.
 */
public class FirebaseDBUtil {

    /**
     * FirebaseDBUtil Logging tag.
     */
    private static final String TAG = "FirebaseDBUtil";

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
     * Push the specified Player directly to the FirebaseDatabase. Any data
     * that has been changed in this Player since the last push will be updated.
     */
    public void pushPlayer(Player player) {
        try {
            database.child(DBKeys.USERS_KEY)
                    .child(player.getUniqueId())
                    .setValue(player);
            Log.d(TAG, String.format(G.locale, "pushPlayer:success, Player: %s", player.toString()));
        } catch (Exception e) {
            Log.e(TAG, "pushPlayer:error", e);
        }
    }
}
