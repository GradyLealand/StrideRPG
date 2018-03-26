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

        // Calculate a percent roll out fo 100 for determining if a Boss encounter
        // should be generated.
        Double roll = r.nextDouble() * 100;

        // If an Encounter is being generated. Set Players Encounter to active,
        // and generate a new Boss.
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
        Enums.BossTier bossTier = Enums.BossTier.getEligibleTier(p.getLevel());

        // Generic Boss properties.
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
        int totalVitality = p.getSkills().getSpeed() + p.getEquipment().getItem(Enums.ItemType.BOOTS).getStatBoost();
        // The vit needed for the monster to have normal expiration time
        int baseVit = calculateBossBaseVitality(bossTier);
        Integer time = bossTier.getExpires() +
                (totalVitality - baseVit);

        // Boss time can not be less then the minimum constant defined.
        if (time < Constants.BOSS_ENCOUNTER_TIME_MINIMUM) {
            time = Constants.BOSS_ENCOUNTER_TIME_MINIMUM;
        }
        return TimeParser.makeTimestamp(TimeParser.getCurrentTimePlusMinutes(time));
    }

    /**
     * Generate a bosses health with the bossTier and constant
     * defined for multiplying a bosses health.
     * @param bossTier Boss tier being generated.
     * @return New Boss base health.
     */
    private static Integer calculateBossHealth(Enums.BossTier bossTier, Player p) {
        int totalStrenght = p.getSkills().getStrength() + p.getEquipment().getItem(Enums.ItemType.WEAPON).getStatBoost();
        // The attack needed for the boss to have normal health
        int baseStr = calculateBossBaseStrength(bossTier);
        int health = Constants.BOSS_ENCOUNTER_HEALTH_MODIFIER +
                ((baseStr -  totalStrenght) * Constants.BOSS_ENCOUNTER_HEALTH_CHANGE);

        // Boss health can not be less then the minimum constant.
        if (health < Constants.BOSS_ENCOUNTER_HEALTH_MINIMUM) {
            health = Constants.BOSS_ENCOUNTER_HEALTH_MINIMUM;
        }
        return health;
    }

    /**
     * Calculate a new Bosses base Vitality value by using the BossTier and the constants
     * defined for dealing with Vitality Skill mins/floors.
     * @param bossTier New Boss BossTier.
     * @return New Boss Minimum Vitality value.
     */
    private static Integer calculateBossBaseVitality(Enums.BossTier bossTier) {
        switch (bossTier) {
            case ONE:
                return Constants.BOSS_ENCOUNTER_TIER_ONE_VITALITY_FLOOR;
            case TWO:
                return Constants.BOSS_ENCOUNTER_TIER_TWO_VITALITY_FLOOR;
            case THREE:
                return Constants.BOSS_ENCOUNTER_TIER_THREE_VITALITY_FLOOR;
            default:
                Log.e(TAG, "calculateBossBaseVitality:error:no bossTier was found? bossTier=[" + bossTier + "]");
                Log.d(TAG, "calculateBossBaseVitality:using BOSS_ENCOUNTER_TIER_ONE_VITALITY_FLOOR for now...");
                return Constants.BOSS_ENCOUNTER_TIER_ONE_VITALITY_FLOOR;
        }
    }

    /**
     * Calculate a new Bosses base Strength value by using the BossTier and the constants
     * defined for dealing with Strength Skill mins/floors.
     * @param bossTier New Boss BossTier.
     * @return New Boss Minimum Strength value.
     */
    private static Integer calculateBossBaseStrength(Enums.BossTier bossTier) {
        switch (bossTier) {
            case ONE:
                return Constants.BOSS_ENCOUNTER_TIER_ONE_STRENGTH_FLOOR;
            case TWO:
                return Constants.BOSS_ENCOUNTER_TIER_TWO_STRENGTH_FLOOR;
            case THREE:
                return Constants.BOSS_ENCOUNTER_TIER_THREE_STRENGTH_FLOOR;
            default:
                Log.e(TAG, "calculateBossBaseStrength:error:no bossTier was found? bossTier=[" + bossTier + "]");
                Log.d(TAG, "calculateBossBaseStrength:using BOSS_ENCOUNTER_TIER_ONE_STRENGTH_FLOOR for now...");
                return Constants.BOSS_ENCOUNTER_TIER_ONE_STRENGTH_FLOOR;
        }
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
     * Parse a new Boss name property using a random adjective from list and the
     * BossType name property.
     * @param bossType BossType enumeration for determining Boss name.
     * @return New Boss name.
     */
    private static String parseName(Enums.Enemies bossType) {
        Random r = new Random();
        String name = bossAdjectives[r.nextInt(bossAdjectives.length)] + " " + bossType.getName();

        Log.d(TAG,  String.format(G.locale, "parseName:success:name=%s", name));
        return name;
    }
}
