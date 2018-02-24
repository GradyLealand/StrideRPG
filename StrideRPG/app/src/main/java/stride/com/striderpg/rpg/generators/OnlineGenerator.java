package stride.com.striderpg.rpg.generators;


import android.util.Log;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Activity.Activity;
import stride.com.striderpg.rpg.utils.TimeParser;

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
            Log.d(TAG, "calculateOnlineActivity:begin");

            // Reset current sessions steps counter and generate a new
            // default Activity.
            G.onlineActivitySteps = 0;

            // Pick a random activity type and begin building new Activity.
            Activity activity = ActivityGenerator.generateActivity(
                    Enums.random(Enums.ActivityType.class)
            );

            // Set timestamp for this new Activity.
            activity.setTimestamp(TimeParser.makeTimestamp());
            Log.d(TAG, String.format(
                    G.locale,
                    "calculateOnlineActivity:success:activity=%s",
                    activity.getTimestamp())
            );

            // Add new Activity to Players ActivityLog.
            G.activePlayer.getActivityLog().addOnlineActivity(activity);
        } else {
            Log.d(TAG, String.format(
                    G.locale,
                    "calculateOnlineActivity:end:%d<%d",
                    G.onlineActivitySteps,
                    Constants.ONLINE_ACTIVITY_STEP_THRESHOLD)
            );
        }
    }

}
