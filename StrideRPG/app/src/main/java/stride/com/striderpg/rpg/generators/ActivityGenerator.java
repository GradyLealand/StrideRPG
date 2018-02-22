package stride.com.striderpg.rpg.generators;


import android.util.Log;

import java.util.Random;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Activity.Activity;
import stride.com.striderpg.rpg.utils.TimeParser;
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
     * Random instance available to the ActivityGenerator class for
     * creating new activities.
     */
    private static Random r = new Random();

    /**
     * Generate a loot activity from the item passed.
     * @param item Item used to build activity description.
     * @return Activity.
     */
    public static Activity generateLootActivity(Item item) {
        Activity newActivity = new Activity(
                TimeParser.makeTimestamp(),
                Enums.ActivityType.LOOT,
                generateLootDescription(item),
                // TODO : Use actual icons for loot. This is placeholder.
                R.drawable.ic_launcher_foreground
        );

        Log.d(TAG, String.format(G.locale, "generateLootActivity:success:activity=%s", newActivity));
        return newActivity;
    }

    /**
     * Generate a String to represent this Loot Activities description.
     * @param item Item.
     * @return Activity description.
     */
    private static String generateLootDescription(Item item) {
        String desc;
        if (item.isBetter(G.activePlayer.getEquipment().getItem(item.getItemType()))) {
            desc = String.format(G.locale,
                    "You found some %s loot! %s (%s) and replaced your old equipment! " +
                            "Power: %d VIT: %d STR: %d SPD: %d...",
                    item.getItemRarity().getName(),
                    item.getName(),
                    item.getItemType().getName(),
                    item.getPowerLevel(),
                    item.getVitalityBoost(),
                    item.getStrengthBoost(),
                    item.getSpeedBoost()
            );
        } else {
            desc = String.format(G.locale,
                    "You found some %s loot! %s (%s)! " +
                            "Power: %d VIT: %d STR: %d SPD: %d...",
                    item.getItemRarity().getName(),
                    item.getName(),
                    item.getItemType().getName(),
                    item.getPowerLevel(),
                    item.getVitalityBoost(),
                    item.getStrengthBoost(),
                    item.getSpeedBoost()
            );
        }

        Log.d(TAG, String.format(G.locale, "generateLootDescription:success:description=%s", desc));
        return desc;
    }

    /**
     * Generate an enemy activity from the enemy passed.
     * @param enemy Enemy used to build activity description.
     * @return Activity.
     */
    public static Activity generateEnemyDefeatedActivity(Enemy enemy) {
        Activity newActivity = new Activity(
                TimeParser.makeTimestamp(),
                Enums.ActivityType.ENEMY,
                generateEnemyDefeatedDescription(enemy),
                // TODO : Use actual icons for enemies. This is placeholder.
                R.drawable.ic_launcher_foreground
        );

        Log.d(TAG, String.format(G.locale, "generateEnemyDefeatedActivity:success:activity=%s", newActivity));
        return newActivity;
    }

    /**
     * Generate a String to represent this Enemy Activities description.
     * @param enemy Enemy.
     * @return Activity description.
     */
    private static String generateEnemyDefeatedDescription(Enemy enemy) {
        String desc = String.format(G.locale,
                "You encountered and defeated a %s, earning %d experience points!",
                enemy.getName(),
                enemy.getExperienceReward()
        );

        Log.d(TAG, String.format(G.locale, "generateEnemyDefeatedDescription:success:description=%s", desc));
        return desc;
    }

    /**
     * Generate an Activity to represent a Player being defeated
     * by an enemy they have encountered.
     * @param enemy Enemy the Player was defeated by.
     * @return Activity representing this defeat.
     */
    public static Activity generateDefeatedByEnemyActivity(Enemy enemy) {
        Activity newActivity = new Activity(
                TimeParser.makeTimestamp(),
                Enums.ActivityType.ENEMY,
                generateDefeatedByEnemyDescription(enemy),
                R.drawable.ic_launcher_foreground
        );

        Log.d(TAG, String.format(G.locale, "generateDefeatedByEnemyActivity:success:activity=%s", newActivity));
        return newActivity;
    }

    /**
     * Generate a String to represent the description of defeated by enemy Activity.
     * @param enemy Enemy that has defeated Player.
     * @return New Activity description.
     */
    private static String generateDefeatedByEnemyDescription(Enemy enemy) {
        String desc = String.format(G.locale,
                "You encountered and were defeated by a %s, you only earned %d experience points!",
                enemy.getName(),
                enemy.getLevel()
        );

        Log.d(TAG, String.format(G.locale, "generateDefeatedByEnemyDescription:success:description=%s", desc));
        return desc;
    }
}
