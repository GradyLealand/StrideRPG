package stride.com.striderpg.rpg.Generators;


import android.util.Log;

import java.util.Locale;
import java.util.Random;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Activity.Activity;
import stride.com.striderpg.rpg.models.Activity.TimestampParser;
import stride.com.striderpg.rpg.models.Enemy.Enemy;
import stride.com.striderpg.rpg.models.Item.Item;

/**
 * RPG Generator class to generate Activities for a Player.
 */
public class ActivityGenerator {

    /**
     * ActivityGenerator Logging tag.
     */
    private static final String TAG = "ActivityGenerator";

    /**
     * Random instance available to the ActivityGenerator class for creating new activities.
     */
    private static Random r = new Random();

    /**
     * Generate a loot activity from the item passed.
     * @param item Item used to build activity description.
     * @return Activity.
     */
    public static Activity generateLootActivity(Item item) {
        Activity newActivity = new Activity(
                TimestampParser.makeTimestamp(),
                Enums.ActivityType.LOOT,
                generateLootDescription(item),
                // TODO : Use actual icons for loot. This is placeholder.
                R.drawable.ic_launcher_foreground
        );

        Log.d(TAG, String.format(G.locale, "generateLootActivity:success:activity=%s", newActivity));
        return newActivity;
    }

    /**
     * Generate an enemy activity from the enemy passed.
     * @param enemy Enemy used to build activity description.
     * @return Activity.
     */
    public static Activity generateEnemyActivity(Enemy enemy) {
        Activity newActivity = new Activity(
                TimestampParser.makeTimestamp(),
                Enums.ActivityType.ENEMY,
                generateEnemyDescription(enemy),
                R.drawable.ic_launcher_foreground
        );

        Log.d(TAG, String.format(G.locale, "generateEnemyActivity:success:activity=%s", newActivity));
        return newActivity;
    }

    /**
     * Generate a String to represent this Enemy Activities description.
     * @param enemy Enemy.
     * @return Activity description.
     */
    private static String generateEnemyDescription(Enemy enemy) {
        String desc = String.format(G.locale,
                "You encountered and defeated a %s, earning %d experience points!",
                enemy.getName(),
                enemy.getExperienceReward()
        );

        Log.d(TAG, String.format(G.locale, "generateEnemyDescription:success:description=%s", desc));
        return desc;
    }

    /**
     * Generate a String to represent this Loot Activities description.
     * @param item Item.
     * @return Activity description.
     */
    private static String generateLootDescription(Item item) {
        String desc = String.format(G.locale,
                "You found some %s loot! %s (%s) VIT: %d STR: %d SPD: %d.",
                item.getItemRarity().getName(),
                item.getName(),
                item.getItemType().getName(),
                item.getVitalityBoost(),
                item.getStrengthBoost(),
                item.getSpeedBoost()
        );

        Log.d(TAG, String.format(G.locale, "generateLootDescription:success:description=%s", desc));
        return desc;
    }
}
