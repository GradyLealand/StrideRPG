package stride.com.striderpg.rpg.generators;


import java.util.Random;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Activity.Activity;
import stride.com.striderpg.rpg.utils.TimeParser;

/**
 * OnlineGenerator RPG Class for building and generating online Activities
 * for the current Player in game while the Application is open.
 */
public class OnlineGenerator {

    /**
     * OnlineGenerator Logging tag.
     */
    private static final String TAG = "OnlineGenerator";

    /**
     * Calculate and check if an online Activity is possible right now
     * with the Players current online steps count.
     */
    public static void calculateOnlineActivity() {
        Random rnd = new Random();

        // Check for threshold reached so an online Activity will be generated.
        if (G.onlineActivitySteps > Constants.ONLINE_ACTIVITY_STEP_THRESHOLD) {

            // Roll a random int between 1-100
            int roll = rnd.nextInt(100) + 1;

            // The Players total speed
            int totalSpeed = G.activePlayer.getSkills().getSpeed() + G.activePlayer.getEquipment().getItem(Enums.ItemType.BOOTS).getStatBoost();

            // Reset current sessions steps counter and generate a new
            // default Activity.
            G.onlineActivitySteps = 0;

            // If the roll + speed modifier is greater then the encounter chance, trigger an encounter
            if (roll + (totalSpeed / 2) > Constants.ONLINE_ACTIVE_ENCOUNTER_CHANCE_PERCENT)
            {
                // Generate an Activity to represent a Player Encountering an Enemy.
                Activity activity = ActivityGenerator.generateActivityOfType(Enums.ActivityType.ENEMY);
                activity.setTimestamp(TimeParser.makeTimestamp());

                // Add new Activity to Players ActivityLog.
                G.activePlayer.getActivityLog().addOnlineActivity(activity);

                // Begin check for Player ActiveEncounter/Boss encounter.
                if (!G.activePlayer.getActiveEncounter().isActive()) {
                    EncounterGenerator.calculateActiveEncounter(G.activePlayer);
                }
            }
        }
    }
}
