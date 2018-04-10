package stride.com.striderpg.rpg.generators;


import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.concurrent.ThreadLocalRandom;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.utils.TimeParser;
import stride.com.striderpg.rpg.models.Activity.Activity;

/**
 * RPG Generator class to calculate a Players progression while they
 * are offline.
 */
public class OfflineGenerator {

    /**
     * OfflineGenerator Logging tag.
     */
    private static final String TAG = "OfflineGenerator";

    /**
     * Calculate a Players offline activities by looking at their
     * last signed in date and determining how many activities will
     * take place for them in that time.
     */
    public static void calculateOfflineActivities() {
        Log.d(TAG, "calculateOfflineActivities:begin");

        // Create date objects to hold the old date and the new current date.
        DateTime old = TimeParser.parseTimestamp(G.activePlayer.getLastSignedIn());
        DateTime now = TimeParser.parseTimestamp(TimeParser.makeTimestamp());

        // Get the difference in minutes for both DateTimes.
        long diffMinutes = TimeParser.getDifferenceInMinutes(old, now);
        Log.d(TAG, String.format(
                G.locale,
                "calculateOfflineActivities:progress:diffMinutes=%d",
                diffMinutes)
        );

        // Quick check for old date being older than current date
        // time minus constant threshold. Do this so that the actual
        // Database log nodes for these new Activities will not be
        // removed by the ActivityLog.clean() method instantly.
        if (old.isBefore(now.minusHours(Constants.ACTIVITY_CLEANUP_THRESHOLD_HOURS))) {
            old = now.minusHours(Constants.ACTIVITY_CLEANUP_THRESHOLD_HOURS);
        }

        // Divide difference in minutes by the constant defined for
        // offlineActivities offline increment.
        Integer possibleActivities = ((int)diffMinutes / Constants.OFFLINE_EVENT_INCREMENT_MINUTES);

        // Return if possible activates isn't greater than 0.
        if (possibleActivities <= 0) {
            Log.d(TAG, String.format(
                    G.locale,
                    "calculateOfflineActivities:return:possibleActivities=%d",
                    possibleActivities)
            );
            return;
        }

        // Loop through possible offlineActivities property and add events
        // if the percent is greater than the Constant defined for
        // the offline activity chance percent.
        int offlineActivities = 0;
        for (int i = 0; i < possibleActivities; i++) {
            double chance = Math.random() * 100;
            if (chance > Constants.OFFLINE_ACTIVITY_CHANCE_PERCENT - (G.activePlayer.getSkills().getSpeed()/3)) {
                offlineActivities++;
            }
        }

        // If after the for loop, no offlineActivities have been added. return.
        if (offlineActivities == 0) {
            Log.d(TAG, "calculateOfflineActivities:return:offlineActivities=0");
            return;
        }

        // Loop for each offlineActivities integer. Use it to generate a new
        // Activity for each one.
        for (int i = 0; i < offlineActivities; i++) {

            // Generate a random date for this activity between the
            // old and now DateTime.
            long random = ThreadLocalRandom.current().nextLong(old.getMillis(), now.getMillis());
            DateTime activityDate = new DateTime(random, DateTimeZone.UTC);

            // Generate a simple Enemy Encounter Activity.
            Activity activity = ActivityGenerator.generateActivityOfType(Enums.ActivityType.ENEMY);

            // Set the timestamp for this new Activity.
            activity.setTimestamp(TimeParser.makeTimestamp(activityDate));

            // Add New Activity the current Players ActivityLog.
            G.activePlayer.getActivityLog().addActivity(activity);
        }
    }
}
