package stride.com.striderpg.rpg.Generators;

import android.util.Log;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.Util.TimestampParser;
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
     * and determining how many activities will take place for them.
     */
    public static void calculateOfflineActivities() {
        // Create date objects to hold the old date and the new current date.
        Date old = TimestampParser.parseTimestamp(G.activePlayer.getLastSignedIn());
        Date now = TimestampParser.parseTimestamp(TimestampParser.makeTimestamp());

        // Determine how many events, if any, will be generated.
        long diff = now.getTime() - old.getTime();
        // Get the difference in minutes.
        long diffMinutes = (diff / 1000) / 60;
        Log.d(TAG, String.format(G.locale, "calculateOfflineActivities:success:diffMinutes=%d", diffMinutes));

        // Divide difference in minutes by the constant defined for activities offline increment.
        Integer possibleActivities = ((int)diffMinutes / Constants.OFFLINE_EVENT_INCREMENT_MINUTES);

        // Return if possible activates isn't greater than 0.
        if (possibleActivities <= 0) {
            Log.d(TAG, String.format(G.locale, "calculateOfflineActivities:return:possibleActivities=%d", possibleActivities));
            return;
        }

        int activities = 0;
        for (int i = 0; i < possibleActivities; i++) {
            double d = Math.random() * 100;
            if (d > 50) {
                activities++;
            }
        }

        // If after the for loop, no activities have been added. return.
        if (activities == 0) {
            Log.d(TAG, "calculateOfflineActivities:return:activities=0");
            return;
        }

        for (int i = 0; i < activities; i++) {
            Activity activity = new Activity();

            // Generate a new random Date to use for this Activity.
            // Otherwise the Activities will have the same date and end up over-writing
            // each other in the Database.
            long random = ThreadLocalRandom.current().nextLong(old.getTime(), now.getTime());
            Date activityDate = new Date(random);

            // Pick a random activity type and build a new activity.
            Enums.ActivityType type = Enums.random(Enums.ActivityType.class);
            switch (type) {
                case LOOT:
                    // Generate new random Item.
                    Item item = ItemGenerator.generate(G.activePlayer);

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
            G.activePlayer.getHistory().addActivity(activity);
        }
    }
}
