package stride.com.striderpg.rpg.generators;


import android.util.Log;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Activity.Activity;
import stride.com.striderpg.rpg.models.Enemy.Boss;
import stride.com.striderpg.rpg.models.Enemy.Monster;
import stride.com.striderpg.rpg.utils.TimeParser;
import stride.com.striderpg.rpg.models.Item.Item;

/**
 * RPG Generator class to generate Activities for a Player.
 */
public class ActivityGenerator {

    /**
     * ActivityGenerator Logging tag.
     */
    private static final String TAG = "ActivityGenerator";


    private static Activity generateLootActivity(Item item) {
        // Update the Players Stats total items looted property.
        G.activePlayer.getStats().updateItemsLooted();
        // Update Players Items looted quest.
        G.activePlayer.getQuestLog().update(Enums.QuestType.LOOT_ITEMS, 1);

        // Check if the new Item is better than the Item equipped currently
        // on the Global activePlayer.
        if (item.isBetter(G.activePlayer.getEquipment().getItem(item.getItemType()))) {
            G.activePlayer.getEquipment().replaceItem(item.getItemType(), item);
        }

        // Generate new Activity with ActivityTye Loot based on
        // Item passed into method.
        return new Activity(
                TimeParser.makeTimestamp(),
                Enums.ActivityType.LOOT,
                generateLootDescription(item),
                R.mipmap.ic_launcher);
    }

    /**
     * Generate the corresponding Activity in the Event a Player
     * wins against an Monster.
     * @param enemy Monster Player beat.
     * @return Monster win Activity.
     */
    private static Activity generateEnemyWinActivity(Monster enemy) {
        // Update Players Bestiary after enemy defeat.
        G.activePlayer.getBestiary().update(enemy.getType());

        // Increment the Players Stats on enemy defeat.
        G.activePlayer.getStats().updateEnemiesDefeated();
        G.activePlayer.getStats().updateTotalExperience(enemy.getExperienceReward());

        // Update Players Enemies defeated quest.
        G.activePlayer.getQuestLog().update(Enums.QuestType.DEFEAT_ENEMIES, 1);

        return new Activity(
                TimeParser.makeTimestamp(),
                Enums.ActivityType.ENEMY,
                generateEnemyWinDescription(enemy),
                R.mipmap.ic_launcher
        );
    }

    /**
     * Generate the corresponding Activity in the Event a Player
     * loses to an Monster.
     * @param enemy Monster Player lost to.
     * @return Monster loss Activity.
     */
    private static Activity generateEnemyLossActivity(Monster enemy) {
        // Update Active Players Quest log to reflect Monster loss.
        G.activePlayer.getQuestLog().update(Enums.QuestType.FAIL_DEFEAT_ENEMIES, 1);

        // Increment the Players Stats on enemy loss.
        G.activePlayer.getStats().updateLosses();
        G.activePlayer.getStats().updateTotalExperience(enemy.getExperienceReward());

        return new Activity(
                TimeParser.makeTimestamp(),
                Enums.ActivityType.ENEMY,
                generateEnemyLossDescription(enemy),
                R.mipmap.ic_launcher
        );
    }

    private static Activity generateBossExpireActivity(Boss boss) {
        // Update Active Players Quest log to reflect Boss expiration.
        G.activePlayer.getQuestLog().update(Enums.QuestType.FAIL_DEFEAT_BOSSES, 1);

        // Increment the Players stats on boss expiration.
        G.activePlayer.getStats().updateBossesExpired();
        G.activePlayer.getStats().updateTotalExperience(boss.getExperienceReward() / 10);

        return new Activity(
                TimeParser.makeTimestamp(),
                Enums.ActivityType.BOSS_EXPIRE,
                generateBossExpireDescription(boss),
                R.mipmap.ic_launcher
        );
    }

    private static Activity generateBossDefeatActivity(Boss boss) {
        // Update Active Players Quest log to reflect Boss win.
        G.activePlayer.getQuestLog().update(Enums.QuestType.DEFEAT_BOSSES, 1);

        // Increment the Players stats on boss win.
        G.activePlayer.getStats().updateBossesDefeated();
        G.activePlayer.getStats().updateTotalExperience(boss.getExperienceReward());

        return new Activity(
                TimeParser.makeTimestamp(),
                Enums.ActivityType.BOSS_DEFEAT,
                generateBossWinDescription(boss),
                R.mipmap.ic_launcher
        );
    }

    /**
     * Base Activity generator that will create a new Generic Activity
     * based on the ActivityType passed to the method.
     * This function is used to generate a Loot or Monster Activity through
     * the Offline/Online Generators.
     * @param type BaseActivity enumeration type.
     * @return Newly generated Activity.
     */
    public static Activity generateActivity(Enums.ActivityType type) {
        Activity activity = new Activity();

        switch (type) {
            case LOOT:
                Item item = ItemGenerator.generate(G.activePlayer);
                activity = generateLootActivity(item);
                break;
            case ENEMY:
                Monster enemy = EnemyGenerator.generate(G.activePlayer);
                boolean fightResult = G.activePlayer.fightEnemy(enemy);

                if (fightResult)
                    activity = generateEnemyWinActivity(enemy);
                else
                    activity = generateEnemyLossActivity(enemy);
                break;
        }
        return activity;
    }

    /**
     * Overloaded Activity generateActivity method for dealing with Boss encounter
     * Activities and generation.
     * @param type ActivityType Enumeration, will only ever be BOSS_EXPIRE / BOSS_DEFEAT.
     * @param boss Boss object to generate Activity information.
     * @return New Boss Activity.
     */
    public static Activity generateActivity(Enums.ActivityType type, Boss boss) {
        Activity activity = new Activity();

        switch (type) {
            case BOSS_EXPIRE:
                activity = generateBossExpireActivity(boss);
                break;
            case BOSS_DEFEAT:
                activity = generateBossDefeatActivity(boss);
                break;
        }
        return activity;
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

        Log.d(TAG, String.format(
                G.locale,
                "generateLootDescription:success:description=%s",
                desc.replace('\n', ' '))
        );

        return desc;
    }

    /**
     * Generate a String to represent this Bosses expiration Activity description.
     * @param boss Boss that has expired.
     * @return New Boss Expiration Activity description.
     */
    private static String generateBossExpireDescription(Boss boss) {
        return String.format(G.locale,
                "The %s ran away with %d health left before you defeated it!\n" +
                        "You only gained %d experience during the battle",
                boss.getName(),
                boss.getHealth(),
                boss.getExperienceReward() / 10
        );
    }

    /**
     * Generate a String to represent this Bosses defeat Activity description.
     * @param boss Boss that has been defeated.
     * @return New Boss defeat Activity description.
     */
    private static String generateBossWinDescription(Boss boss) {
        return String.format(G.locale,
                "You have defeated the %s!\nYou earned %d experience and found %d pieces of loot!",
                boss.getName(),
                boss.getExperienceReward(),
                boss.getRewards().size()
        );
    }

    /**
     * Generate a String to represent this Monster Activities description.
     * @param enemy Monster.
     * @return Activity description.
     */
    private static String generateEnemyWinDescription(Monster enemy) {
        return String.format(G.locale,
                "You encountered and defeated %s.\nYou earned %d experience points!",
                parseEnemyNameToProperAOrAn(enemy.getName()),
                enemy.getExperienceReward()
        );
    }

    /**
     * Generate a String to represent the description of defeated by enemy Activity.
     * @param enemy Monster that has defeated Player.
     * @return New Activity description.
     */
    private static String generateEnemyLossDescription(Monster enemy) {
        return String.format(G.locale,
                "You encountered and were defeated by %s.\nYou only earned %d experience points!",
                parseEnemyNameToProperAOrAn(
                        enemy.getName()), enemy.getLevel()
        );
    }

    /**
     * Parse an Monster name passed to the method into the proper "a" or "an"
     * based on the first letter of the Enemies name. "a,e,i,o,u" would
     * result in an "an" return, otherwise return "a".
     * @param enemyName Monster name being parsed.
     * @return "a" or "an" plus Monster name.
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
