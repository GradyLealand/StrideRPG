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
import stride.com.striderpg.rpg.models.Enemy.Enemy;
import stride.com.striderpg.rpg.models.Item.Item;

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
        Log.d(TAG, String.format(G.locale, "calculateOfflineActivities:progress:diffMinutes=%d", diffMinutes));

        // Quick check for old date being older than current date
        // time minus constant threshold. Do this so that the actual
        // Database log nodes for these new Activities will not be
        // removed by the ActivityLog.clean() method instantly.
        if (old.isBefore(now.minusHours(Constants.ACTIVITY_CLEANUP_THRESHOLD_HOURS))) {
            old = now.minusHours(Constants.ACTIVITY_CLEANUP_THRESHOLD_HOURS);
        }

        // Divide difference in minutes by the constant defined for
        // activities offline increment.
        Integer possibleActivities = ((int)diffMinutes / Constants.OFFLINE_EVENT_INCREMENT_MINUTES);

        // Return if possible activates isn't greater than 0.
        if (possibleActivities <= 0) {
            Log.d(TAG, String.format(G.locale, "calculateOfflineActivities:return:possibleActivities=%d", possibleActivities));
            return;
        }

        // Loop through possible activities property and add events
        // if the percent is greater than the Constant defined for
        // the offline activity chance percent.
        int activities = 0;
        for (int i = 0; i < possibleActivities; i++) {
            double chance = Math.random() * 100;
            if (chance > Constants.OFFLINE_ACTIVITY_CHANCE_PERCENT - (G.activePlayer.getSkills().getSpeed()/3)) {
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

        // Loop for each activities integer. Use it to generate a new
        // Activity for each one.
        for (int i = 0; i < activities; i++) {
            Activity activity = new Activity();

            // Generate a date for this activity.
            long random = ThreadLocalRandom.current().nextLong(old.getMillis(), now.getMillis());
            DateTime activityDate = new DateTime(random, DateTimeZone.UTC);

            // Pick a random activity type and build a new activity.
            Enums.ActivityType type = Enums.random(Enums.ActivityType.class);
            switch (type) {
                case LOOT:

                    // Generate new random Item.
                    Item item = ItemGenerator.generate(G.activePlayer);

                    // Create the new Activity and set the timestamp to a random date between
                    activity = ActivityGenerator.generateLootActivity(item);

                    // Update the Players Stats total items looted property.
                    G.activePlayer.getStats().updateItemsLooted();

                    // Update Players Items looted quest.
                    G.activePlayer.getQuestLog().update(Enums.QuestType.LOOT_ITEMS, 1);

                    // Check if the new Item is better than the Item equipped currently
                    // on the Global activePlayer.
                    // TODO : Clean this up?? Shorthand, new method in getEquipment maybe.
                    if (item.isBetter(G.activePlayer.getEquipment().getItem(item.getItemType()))) {
                        G.activePlayer.getEquipment().replaceItem(item.getItemType(), item);
                    }
                    break;
                case ENEMY:

                    // Generate new random Enemy.
                    Enemy enemy = EnemyGenerator.generate(G.activePlayer);

                    // Check if the Player has defeated, or lost to this new Enemy.
                    boolean fightResult = G.activePlayer.fightEnemy(enemy);

                    // Based on the result of the fightEnemy method, generate
                    // a new Activity for the Players ActivityLog.
                    if (fightResult) {
                        activity = ActivityGenerator.generateEnemyDefeatedActivity(enemy);
                    } else {
                        activity = ActivityGenerator.generateDefeatedByEnemyActivity(enemy);
                    }
                    break;
            }

            // Set the timestamp for this new Activity.
            activity.setTimestamp(TimeParser.makeTimestamp(activityDate));

            // Add New Activity the current Players ActivityLog.
            G.activePlayer.getActivityLog().addActivity(activity);
            Log.d(TAG, String.format(G.locale, "calculateOfflineActivities:success:activity=%s", activity));
        }
    }
}
