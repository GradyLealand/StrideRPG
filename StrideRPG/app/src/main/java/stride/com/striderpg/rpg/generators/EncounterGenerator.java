package stride.com.striderpg.rpg.generators;


import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Enemy.Boss;
import stride.com.striderpg.rpg.models.Item.Item;
import stride.com.striderpg.rpg.models.Player.Player;
import stride.com.striderpg.rpg.utils.TimeParser;

/**
 * EncounterGenerator RPG Class for building and generating active Encounters
 * for the Player in game while the Application is open.
 */
public class EncounterGenerator {

    /**
     * EncounterGenerator Logging tag.
     */
    private static final String TAG = "EncounterGenerator";

    /**
     * String array for choosing the Bosses possible name adjectives.
     */
    private static final String[] bossAdjectives = { "Demonic", "Ultra", "Legendary" };

    /**
     * Randomly decide if an Active Encounter is going to be generated for the Player.
     * @param p Player object for determining values.
     */
    public static void calculateActiveEncounter(Player p) {
        Random r = new Random();
        Double roll = r.nextDouble() * 100;

        if (roll < Constants.ONLINE_ACTIVE_ENCOUNTER_CHANCE_PERCENT) {
            G.activePlayer.getActiveEncounter().setBoss(generateBoss(p));
            G.activePlayer.getActiveEncounter().setActive(true);
        }
    }

    /**
     * Boss Object generator to create an entire Boss object based on the Player
     * object passed into the method.
     * @param p Player used for determining Boss stats.
     * @return New Boss object.
     */
    private static Boss generateBoss(Player p) {
        // Determine new Boss Type and Tier.
        Enums.Enemies bossType = Enums.Enemies.getRandomEnemiesType(Enums.EnemyType.BOSS);
        Enums.BossTier bossTier;
        do {
            bossTier = Enums.random(Enums.BossTier.class);
        }
        while(p.getLevel() < bossTier.getEligible());

        String name = "Tier " + bossTier.getNumeral() + ": " + parseName(bossType);
        String expires = calculateBossExpiration(bossTier, p);
        int health = calculateBossHealth(bossTier, p);
        int experience = calculateBossExp(p, bossTier);
        ArrayList<Item> rewards = calculateBossRewards(p, bossTier);
        int icon = parseIcon(bossType);

        return new Boss(name, bossType, health, icon, experience, bossTier, expires, rewards);
    }

    /**
     * Calculate the bosses expiration date by retrieving a time from now
     * + amount of hours.
     * @param bossTier BossTier enumeration type for determining hours.
     * @return Timestamp for Boss expiration date.
     */
    private static String calculateBossExpiration(Enums.BossTier bossTier, Player p) {
        //the vit needed for the monster to have normal expiration time
        int baseVit = 0;
        int tier = bossTier.getNumber();

        // TODO : Move into function/generate Skill floors based on skills points given on level.
        switch (tier) {
            case 1:
                baseVit = 17;
                break;
            case 2:
                baseVit = 51;
                break;
            case 3:
                baseVit = 105;
                break;
        }

        Integer time = bossTier.getExpires() +
                (p.getSkills().getStrength() - baseVit);

        // boss time can not be less then 100
        if (time < Constants.BOSS_ENCOUNTER_TIME_MINIMUM) {
            time = Constants.BOSS_ENCOUNTER_TIME_MINIMUM;
        }
        return TimeParser.makeTimestamp(TimeParser.getCurrentTimePlusMinutes(time));
    }

    /**
     * Generate an amount of Items that will be rewarded to a Player when they defeat
     * a Boss in the given time.
     * @param p Player used to determine Item statistics.
     * @param tier BossTier for determining how many Items to generate.
     * @return New ArrayList of Items.
     */
    private static ArrayList<Item> calculateBossRewards(Player p, Enums.BossTier tier) {
        ArrayList<Item> rewards = new ArrayList<>();
        for (int i = 0; i < tier.getNumber(); i++) {
            rewards.add(ItemGenerator.generate(p));
        }

        return rewards;
    }

    /**
     * Parse a Bosses Icon by looking for an drawable resource with the same name as
     * the boss type name in all lowercase.
     * @param bossType BossType Enumeration.
     * @return Drawable Resource ID for boss type.
     */
    private static int parseIcon(Enums.Enemies bossType) {
        int iconId = 0;
        try {
            iconId = R.drawable.class.getField(bossType.getName().toLowerCase()).getInt(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Log.e(TAG, "parseIcon:error:", e);
        }

        Log.d(TAG, String.format(G.locale, "parseIcon:success:icon=%d", iconId));
        return iconId;
    }

    /**
     * Calculate the amount of experience this Boss will be worth on defeat.
     * @param p Player object for determining exp amount.
     * @param tier BossTier for modifying the experience more so.
     * @return New boss experience reward.
     */
    private static int calculateBossExp(Player p, Enums.BossTier tier) {
        Random r = new Random();
        int expReward = (tier.getNumber() * Constants.BOSS_EXPERIENCE_MODIFIER - p.getLevel())
                + (r.nextInt((50) + (Constants.BOSS_EXPERIENCE_MODIFIER))
                * tier.getNumber());

        Log.d(TAG, String.format(G.locale, "calculateBossExp:success:expReward=%d", expReward));
        return expReward;
    }

    /**
     * Generate a bosses health with the bossTier and constant
     * defined for multiplying a bosses health.
     * @param bossTier Boss tier being generated.
     * @return New Boss base health.
     */
    private static Integer calculateBossHealth(Enums.BossTier bossTier, Player p) {
        //the atk needed for the monster to have normal health
        int baseStr = 0;
        //the tier of the current boss
        int tier = bossTier.getNumber();

        // TODO : Move into function/generate Skill floors based on skills points given on level.
        switch (tier) {
            case 1:
                baseStr = 17;
                break;
            case 2:
                baseStr = 51;
                break;
            case 3:
                baseStr = 105;
                break;
        }

        int health = Constants.BOSS_ENCOUNTER_HEALTH_MODIFIER +
                (baseStr - p.getSkills().getStrength()) * Constants.BOSS_ENCOUNTER_HEALTH_CHANGE;

        // boss health can not be less then 100
        if (health < Constants.BOSS_ENCOUNTER_HEALTH_MINIMUM) {
            health = Constants.BOSS_ENCOUNTER_HEALTH_MINIMUM;
        }
        return health;
    }

    /**
     * Parse a new Boss name property using a random adjective from list and the
     * BossType name property.
     * @param bossType BossType enumeration for determining Boss name.
     * @return New Boss name.
     */
    private static String parseName(Enums.Enemies bossType) {
        Random r = new Random();
        String name =
                bossAdjectives[r.nextInt(bossAdjectives.length)] +
                " " +
                bossType.getName();

        Log.d(TAG,  String.format(G.locale, "parseName:success:name=%s", name));
        return name;
    }
}
