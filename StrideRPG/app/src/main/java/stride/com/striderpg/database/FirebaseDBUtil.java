package stride.com.striderpg.database;


import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import stride.com.striderpg.global.Globals;
import stride.com.striderpg.rpg.models.Item.Item;
import stride.com.striderpg.rpg.models.Player.Player;

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
        // Get a reference to the FirebaseDatabase.
        database = FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Push the ActivePlayer from the Globals class directly to the FirebaseDatabase. Any data
     * that has been changed in this Player since the last push will be updated.
     */
    public void pushActivePlayer() {
        database.child(DBKeys.USERS_KEY)
                .child(Globals.activePlayer.getUniqueId())
                .setValue(Globals.activePlayer);
    }

    /**
     * Push the ActivePlayers Inventory from the Globals class directly to the FirebaseDatabase.
     */
    public void pushPlayerInventory() {
        database.child(DBKeys.USERS_KEY)
                .child(Globals.activePlayer.getUniqueId())
                .setValue(DBKeys.INVENTORY_KEY, Globals.activePlayer.getInventory());
    }

    /**
     * Push an Item directly to the ActivePlayers Inventory in the FirebaseDatabase.
     * TODO: Is this needed? Can we control data in one spot (ActivePlayer) and just push
     * TODO: every x amount of seconds to decrease database calls required.
     */
    public void pushItem(Item item) {
        database.child(DBKeys.USERS_KEY)
                .child(Globals.activePlayer.getUniqueId())
                .child(DBKeys.INVENTORY_KEY)
                .child(DBKeys.INVENTORY_ITEMS_KEY)
                .setValue(Globals.activePlayer.getInventory().makeKey(), item);
    }

    /**
     * Push the ActivePlayers Skills from the Globals class directly to the FirebaseDatabase.
     */
    public void pushPlayerSkills() {
        database.child(DBKeys.USERS_KEY)
                .child(Globals.activePlayer.getUniqueId())
                .child(DBKeys.SKILLS_KEY)
                .setValue(DBKeys.SKILLS_KEY, Globals.activePlayer.getSkills());
    }

    /**
     * Push a specified skill value to the FirebaseDatabase for the current activePlayer.
     * @param KEY String representing the node to be updated.
     * @param value New value for specified skill.
     */
    public void pushPlayerSkill(String KEY, Integer value) {
        database.child(DBKeys.USERS_KEY)
                .child(Globals.activePlayer.getUniqueId())
                .child(DBKeys.SKILLS_KEY)
                .child(KEY).setValue(value);
    }

    /**
     * Push the ActivePlayers current steps from the Globals class directly to the FirebaseDatabase.
     */
    public void pushPlayerSteps() {
        database.child(DBKeys.USERS_KEY)
                .child(Globals.activePlayer.getUniqueId())
                .setValue(DBKeys.STEPS_KEY, Globals.activePlayer.getSteps());
    }

    /**
     * Push the ActivePlayers current level from the Globals class directly to the FirebaseDatabase.
     */
    public void pushPlayerLevel() {
        database.child(DBKeys.USERS_KEY)
                .child(Globals.activePlayer.getUniqueId())
                .setValue(DBKeys.LEVEL_KEY, Globals.activePlayer.getLevel());
    }

    /**
     * Push the ActivePlayers current experience from the Globals class directly to the FirebaseDatabase.
     */
    public void pushPlayerExperience() {
        database.child(DBKeys.USERS_KEY)
                .child(Globals.activePlayer.getUniqueId())
                .setValue(DBKeys.EXPERIENCE_KEY, Globals.activePlayer.getExperience());
    }
}
