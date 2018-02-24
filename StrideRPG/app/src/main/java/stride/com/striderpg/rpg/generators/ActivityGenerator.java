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
     * Base Activity generator that will create a new Activity
     * based on the ActivityType passed to the method.
     * @param type BaseActivity enumeration type.
     * @return Newly generated Activity.
     */
    public static Activity generateActivity(Enums.ActivityType type) {
        // Generate a new base Activity.
        Activity activity = new Activity();

        // Switch case through the ActivityType passed into method.
        switch (type) {

            // LOOT ActivityType.
            case LOOT:
                // Generate new Item based on Active Player stats and generate
                // the loot Activity that will be associated with this item.
                Item item = ItemGenerator.generate(G.activePlayer);
                activity = generateLootActivity(item);

                // Update the Players Stats total items looted property.
                G.activePlayer.getStats().updateItemsLooted();
                // Update Players Items looted quest.
                G.activePlayer.getQuestLog().update(Enums.QuestType.LOOT_ITEMS, 1);

                // Check if the new Item is better than the Item equipped currently
                // on the Global activePlayer.
                if (item.isBetter(G.activePlayer.getEquipment().getItem(item.getItemType()))) {
                    G.activePlayer.getEquipment().replaceItem(item.getItemType(), item);
                }
                break;

            // ENEMY ActivityType
            case ENEMY:
                // Generate new random Enemy.
                Enemy enemy = EnemyGenerator.generate(G.activePlayer);
                // Check if the Player has defeated, or lost to this new Enemy.
                boolean fightResult = G.activePlayer.fightEnemy(enemy);

                // Based on the result of the fightEnemy method, generate
                // a new Activity for the Players ActivityLog.
                if (fightResult) {
                    activity = generateEnemyDefeatedActivity(enemy);
                } else {
                    activity = generateDefeatedByEnemyActivity(enemy);
                }
                break;
        }
        return activity;
    }

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
                R.mipmap.ic_launcher
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
                    "You found some %s equipment...\n%s\nYou replaced your old equipment!\n" +
                            "Power: %d\nVitality: %d\nStrength: %d\nSpeed: %d",
                    item.getItemRarity().getName(),
                    item.getName(),
                    item.getPowerLevel(),
                    item.getVitalityBoost(),
                    item.getStrengthBoost(),
                    item.getSpeedBoost()
            );
        } else {
            desc = String.format(G.locale,
                    "You found some %s equipment...\n%s\n" +
                            "Power: %d\nVitality: %d\nStrength: %d\nSpeed: %d",
                    item.getItemRarity().getName(),
                    item.getName(),
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
                R.mipmap.ic_launcher
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
                "You encountered and defeated %s.\nYou earned %d experience points!",
                parseEnemyNameToProperAOrAn(enemy.getName()),
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
                // TODO : Find real enemy icons.
                R.mipmap.ic_launcher
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
                "You encountered and were defeated by %s.\nYou only earned %d experience points!",
                parseEnemyNameToProperAOrAn(enemy.getName()),
                enemy.getLevel()
        );

        Log.d(TAG, String.format(G.locale, "generateDefeatedByEnemyDescription:success:description=%s", desc));
        return desc;
    }

    /**
     * Parse an Enemy name passed to the method into the proper "a" or "an"
     * based on the first letter of the Enemies name. "a,e,i,o,u" would
     * result in an "an" return, otherwise return "a".
     * @param enemyName Enemy name being parsed.
     * @return "a" or "an" plus Enemy name.
     */
    private static String parseEnemyNameToProperAOrAn(String enemyName) {
        String firstLetterLower = String.valueOf(enemyName.charAt(0)).toLowerCase();
        String[] vowels = {"a", "e", "i", "o", "u"};

        for (String s : vowels) {
            if (firstLetterLower.equals(s)) {
                return String.format(G.locale, "an %s", enemyName);
            }
        }
        return String.format(G.locale, "a %s", enemyName);
    }
}
