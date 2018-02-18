package stride.com.striderpg.rpg.generators;

import android.util.Log;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.utils.TimestampParser;
import stride.com.striderpg.rpg.models.Activity.Activity;
import stride.com.striderpg.rpg.models.Enemy.Enemy;
import stride.com.striderpg.rpg.models.Item.Item;

/**
 * RPG Generator class to calculate a Players progression while they are offline.
 */
public class OfflineGenerator {

    /**
     * OfflineGenerator Logging tag.
     */
    private static final String TAG = "OfflineGenerator";

    /**
     * Calculate a Players offline activities by looking at their last signed in date
     * and determining how many activities will take place for them in that time.
     */
    public static void calculateOfflineActivities() {
        Log.d(TAG, "calculateOfflineActivities:begin");

        // Create date objects to hold the old date and the new current date.
        Date old = TimestampParser.parseTimestamp(G.activePlayer.getLastSignedIn());
        Date now = TimestampParser.parseTimestamp(TimestampParser.makeTimestamp());

        // Determine how many events, if any, will be generated.
        long diff = now.getTime() - old.getTime();
        // Get the difference in minutes.
        long diffMinutes = (diff / 1000) / 60;
        Log.d(TAG, String.format(G.locale, "calculateOfflineActivities:progress:diffMinutes=%d", diffMinutes));

        // Divide difference in minutes by the constant defined for activities offline increment.
        Integer possibleActivities = ((int)diffMinutes / Constants.OFFLINE_EVENT_INCREMENT_MINUTES);

        // Return if possible activates isn't greater than 0.
        if (possibleActivities <= 0) {
            Log.d(TAG, String.format(G.locale, "calculateOfflineActivities:return:possibleActivities=%d", possibleActivities));
            return;
        }

        // Loop through possible activities property and add events if the percent
        // is greater than the Constant defined for the offline activity chance percent.
        int activities = 0;
        for (int i = 0; i < possibleActivities; i++) {
            double chance = Math.random() * 100;
            if (chance > Constants.OFFLINE_ACTIVITY_CHANCE_PERCENT) {
                activities++;
            }
        }

        // If after the for loop, no activities have been added. return.
        if (activities == 0) {
            Log.d(TAG, "calculateOfflineActivities:return:activities=0");
            return;
        } else {
            Log.d(TAG, String.format(G.locale, "calculateOfflineActivities:progress:activities=%d", activities));
        }

        for (int i = 0; i < activities; i++) {
            Activity activity = new Activity();

            // Check if the last time a Player logged in is greater than the constant defined for
            // cleaning up activities in the log. This will allow all new Activities generated to be
            // within a safe range so they aren't deleted after being generated.
            long random;
            if (old.getTime() < TimestampParser.getDateTimeMinusHours(now, Constants.ACTIVITY_CLEANUP_THRESHOLD_HOURS).getTime()) {
                // Generate a long time in milliseconds within a range of
                // current time / current time minus threshold.
                random = ThreadLocalRandom.current().nextLong(
                        TimestampParser.getDateTimeMinusHours(now, Constants.ACTIVITY_CLEANUP_THRESHOLD_HOURS).getTime(),
                        now.getTime()
                );
                Log.d(TAG, String.format(
                        G.locale,
                        "calculateOfflineActivities:progress:lastSignedIn is older than threshold: %d",
                        Constants.ACTIVITY_CLEANUP_THRESHOLD_HOURS)
                );
            // Otherwise, the old date is less than the constant threshold
            // and can be retrieved normally.
            } else {
                random = ThreadLocalRandom.current().nextLong(old.getTime(), now.getTime());
                Log.d(TAG, String.format(
                        G.locale,
                        "calculateOfflineActivities:progress:lastSignedIn is newer than threshold: %d",
                        Constants.ACTIVITY_CLEANUP_THRESHOLD_HOURS)
                );
            }
            Date activityDate = new Date(random);

            // Pick a random activity type and build a new activity.
            Enums.ActivityType type = Enums.random(Enums.ActivityType.class);
            switch (type) {
                case LOOT:
                    // Generate new random Item.
                    Item item = ItemGenerator.generate(G.activePlayer);

                    // Update the Players Stats total items looted property.
                    G.activePlayer.getStats().updateItemsLooted();

                    // Create the new Activity and set the timestamp to a random date between
                    // old and now date.
                    activity = ActivityGenerator.generateLootActivity(item);
                    activity.setTimestamp(TimestampParser.makeTimestamp(activityDate));

                    // Check if the new Item is better than the Item equipped currently
                    // on the Global activePlayer.
                    if (item.isBetter(G.activePlayer.getEquipment().getItem(item.getItemType()))) {
                        G.activePlayer.getEquipment().replaceItem(item.getItemType(), item);
                    }
                    break;
                case ENEMY:
                    // Generate new random Enemy.
                    Enemy enemy = EnemyGenerator.generate(G.activePlayer);
                    G.activePlayer.defeatEnemy(enemy);
                    activity = ActivityGenerator.generateEnemyActivity(enemy);
                    break;
            }
            Log.d(TAG, String.format(G.locale, "calculateOfflineActivities:success:activity=%s", activity));
            G.activePlayer.getHistory().addActivity(activity);
        }
    }
}
