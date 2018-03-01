package stride.com.striderpg.rpg.generators;


import android.util.Log;

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
        Log.d(TAG, String.format(
                G.locale,
                "calculateOnlineActivity:check:onlineActivitySteps=%d",
                G.onlineActivitySteps));

        // Check for threshold reached so an online Activity will be generated.
        if (G.onlineActivitySteps > Constants.ONLINE_ACTIVITY_STEP_THRESHOLD) {

            // Reset current sessions steps counter and generate a new
            // default Activity.
            G.onlineActivitySteps = 0;

            // Pick a random activity type (Loot or Monster) and begin building new Activity.
            Activity activity = ActivityGenerator.generateActivity(
                    Enums.ActivityType.generic()
            );

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
