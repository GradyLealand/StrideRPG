package stride.com.striderpg.rpg;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import stride.com.striderpg.R;


/**
 * Enums Class to store any Enumeration Types and the custom functionality
 * contained in each one. Many of these Enumerations are used to generate rpg
 * elements inside of the game.
 */
public class Enums {

    /**
     * Generic Function to return a completely random enum from any
     * enum instance passed into the function.
     * @param tClass .class property of the Enum object being randomly
     *               chosen.
     * @param <T> The enum object the method is being called from.
     * @return Random enum constant from specified enum.
     */
    public static <T extends Enum<?>> T random(Class<T> tClass) {
        Random random = new Random();
        int x = random.nextInt(tClass.getEnumConstants().length);
        return tClass.getEnumConstants()[x];
    }

    /**
     * ItemRarity Enumeration to store each possible rarity that an Item
     * can possibly be. Item Rarity also affects an Items statistics.
     */
    public enum ItemRarity {
        COMMON("Common"),
        UNCOMMON("Uncommon"),
        RARE("Rare"),
        EPIC("Epic"),
        LEGENDARY("Legendary");

        /**
         * Item Rarity name property.
         */
        private final String name;

        /**
         * Constructor method to set name property.
         * @param name Name of enum.
         */
        ItemRarity(String name) {
            this.name = name;
        }

        /**
         * Gets the name property.
         * @return ItemRarity name.
         */
        public String getName() {
            return name;
        }

        /**
         * Get a random ItemRarity based on weighted percentages.
         * TODO : Move weights out into the Constants class.
         * COMMON = 65%
         * UNCOMMON = 15%
         * RARE = 10%
         * EPIC = 7%
         * LEGENDARY = 3%
         * @return ItemRarity enum based on percent calculated.
         */
        public static ItemRarity weightedRarity() {
            double d = Math.random() * 100;
            if ((d -= 55) < 0) return COMMON;
            if ((d -= 20) < 0) return UNCOMMON;
            if ((d -= 15) < 0) return RARE;
            if ((d - 7) < 0) return EPIC;
            return LEGENDARY;
        }
    }

    /**
     * ItemType Enumeration to store each possible ItemType in the game.
     */
    public enum ItemType {
        WEAPON("Weapon"),
        HELMET("Helmet"),
        CHEST("Chest"),
        LEGS("Legs"),
        BOOTS("Boots");

        /**
         * Item Type name property.
         */
        private final String name;

        /**
         * Constructor to set the name property.
         * @param name Name of enum.
         */
        ItemType(String name) {
            this.name = name;
        }

        /**
         * Gets the name property.
         * @return ItemType name.
         */
        public String getName() {
            return name;
        }
    }

    /**
     * EnemyType Enumeration to store each possible EnemyType in the game.
     */
    public enum EnemyType {
        MONSTER,
        BOSS
    }

    /**
     * Enemies Enumeration to store each possible Enemy in the game and
     * their image resource.
     */
    public enum Enemies {
        TROLL("Troll", EnemyType.MONSTER, R.mipmap.ic_launcher),
        GOBLIN("Goblin", EnemyType.MONSTER, R.mipmap.ic_launcher),
        LIZARD("Lizard", EnemyType.MONSTER, R.mipmap.ic_launcher),
        NECROMANCER("Necromancer", EnemyType.MONSTER, R.mipmap.ic_launcher),

        DRAGON("Dragon", EnemyType.BOSS, R.mipmap.ic_launcher),
        DEMON("Demon", EnemyType.BOSS, R.mipmap.ic_launcher),
        KRAKEN("Kraken", EnemyType.BOSS, R.mipmap.ic_launcher);

        /**
         * Enemies name property.
         */
        private final String name;

        /**
         * Enemies EnemyType enum.
         */
        private final EnemyType type;

        /**
         * Enemies Icon Image resource.
         */
        private final Integer enemyIcon;

        /**
         * Constructor to set the name, type and image resource id.
         * @param name Name of constant.
         * @param type Type of enemy.
         * @param enemyIcon Enemies icon image resource.
         */
        Enemies(String name, EnemyType type, Integer enemyIcon) {
            this.name = name;
            this.type = type;
            this.enemyIcon = enemyIcon;
        }

        /**
         * Return a random Enemies enum based on the EnemyType passed.
         * @param type EnemyType to search for while looping through Enemies.
         * @return random Enemies enumeration.
         */
        public static Enemies getRandomEnemiesType(EnemyType type) {
            ArrayList<Enemies> enemies = new ArrayList<>();
            for (Enemies enemy : Enemies.values()) {
                if (enemy.type == type) {
                    enemies.add(enemy);
                }
            }

            Random random = new Random();
            return enemies.get(random.nextInt(enemies.size()));
        }

        /**
         * Gets the name property.
         * @return Enemies name.
         */
        public String getName() {
            return name;
        }

        /**
         * Gets the type property.
         * @return Enemies type.
         */
        public EnemyType getType() {
            return type;
        }

        /**
         * Gets the icon resource id.
         * @return Enemies icon resource.
         */
        public Integer getEnemyIcon() {
            return enemyIcon;
        }
    }

    /**
     * BossTier enumeration to store each tier of boss present in the game.
     */
    public enum BossTier {
        ONE(1, "I", 30, Constants.BOSS_ENCOUNTER_TIER_ONE_MINIMUM_LEVEL),
        TWO(2, "II", 60, Constants.BOSS_ENCOUNTER_TIER_TWO_MINIMUM_LEVEL),
        THREE(3, "III", 90, Constants.BOSS_ENCOUNTER_TIER_THREE_MINIMUM_LEVEL);

        /**
         * BossTier Number.
         */
        private final Integer number;

        /**
         * BossTier numeral, representing a roman numeral for the tier.
         * Ex: 1->I 2->II 3->III 10->X.
         */
        private final String numeral;

        /**
         * BossTier expiration value in minutes.
         */
        private final Integer expires;

        /**
         * BossTier level requirement for a Player to be eligible
         * to encounter specific BossTier's.
         */
        private final Integer eligible;

        /**
         * Constructor to set the number, numeral, expires and eligible properties.
         * @param number Number representing BossTier.
         * @param numeral Numeral representing BossTier.
         * @param expires Expiration time in minutes for BossTier.
         * @param eligible Level requirement for BossTier.
         */
        BossTier(Integer number, String numeral, Integer expires, Integer eligible) {
            this.number = number;
            this.numeral = numeral;
            this.expires = expires;
            this.eligible = eligible;
        }

        /**
         * Gets a random BossTier enumeration as long as the level passed to the method
         * is less than or equal to a Boss Tier's eligible property.
         * @param level Level being compared to a Boss Tier's eligible property.
         * @return Random BossTier.
         */
        public static BossTier getEligibleTier(Integer level) {
            ArrayList<BossTier> tempTiers = new ArrayList<>();
            for (BossTier tier : values()) {
                if (tier.getEligible() <= level) {
                    tempTiers.add(tier);
                }
            }
            Collections.shuffle(tempTiers);
            return tempTiers.get(0);
        }

        /**
         * Gets the number property.
         * @return BossTier number.
         */
        public Integer getNumber() {
            return number;
        }

        /**
         * Gets the numeral property.
         * @return BossTier numeral.
         */
        public String getNumeral() {
            return numeral;
        }

        /**
         * Gets the expires property.
         * @return BossTier expires.
         */
        public Integer getExpires() {
            return expires;
        }

        /**
         * Gets the eligible property.
         * @return BossTier eligible.
         */
        public Integer getEligible() {
            return eligible;
        }
    }

    /**
     * PlayerSort enumeration to store the different sort types available when
     * sorting Players in the leaderboards.
     */
    public enum PlayerSort {
        EXPERIENCE,
        LEVEL,
        ENEMIES_DEFEATED,
        STEPS
    }

    /**
     * ActivityType enumeration to store the different Activities that
     * can be generated inside of the game.
     */
    public enum ActivityType {
        ENEMY("Monster"),
        LOOT("Loot"),
        BOSS_EXPIRE("Boss Expiration"),
        BOSS_DEFEAT("Boss Defeat");

        /**
         * ActivityType name.
         */
        private final String name;

        /**
         * Constructor to set the name property.
         * @param name Activity name.
         */
        ActivityType(String name) {
            this.name = name;
        }

        /**
         * Gets the name property.
         * @return ActivityType name.
         */
        public String getName() {
            return name;
        }

        /**
         * Gets a random generic ActivityType.
         * A generic ActivityType includes the LOOT and ENEMY types.
         * @return ActivityType.
         */
        public static ActivityType generic() {
            Random random = new Random();
            Double percent = random.nextDouble() * 100;
            if (percent < 50)
                return LOOT;
            else
                return ENEMY;
        }
    }

    /**
     * QuestType enumeration to store the different quests inside of the game.
     */
    public enum QuestType {
        DEFEAT_ENEMIES(
                Constants.QUEST_DEFEAT_ENEMIES_TITLE,
                Constants.QUEST_DEFEAT_ENEMIES_DESCRIPTION
        ),
        DEFEAT_BOSSES(
                Constants.QUEST_DEFEAT_BOSSES_TITLE,
                Constants.QUEST_DEFEAT_BOSSES_DESCRIPTION
        ),
        FAIL_DEFEAT_ENEMIES(
                Constants.QUEST_FAIL_DEFEAT_ENEMIES_TITLE,
                Constants.QUEST_FAIL_DEFEAT_ENEMIES_DESCRIPTION
        ),
        FAIL_DEFEAT_BOSSES(
                Constants.QUEST_FAIL_DEFEAT_BOSSES_TITLE,
                Constants.QUEST_FAIL_DEFEAT_BOSSES_DESCRIPTION
        ),
        LOOT_ITEMS(
                Constants.QUEST_LOOT_ITEMS_TITLE,
                Constants.QUEST_LOOT_ITEMS_DESCRIPTION
        ),
        TAKE_STEPS(
                Constants.QUEST_TAKE_STEPS_TITLE,
                Constants.QUEST_TAKE_STEPS_DESCRIPTION
        );

        /**
         * QuestType title.
         */
        private final String title;

        /**
         * QuestType description.
         */
        private final String description;

        /**
         * Constructor to set the title and description properties.
         * @param title QuestType title.
         * @param description QuestType description.
         */
        QuestType(String title, String description) {
            this.title = title;
            this.description = description;
        }

        /**
         * Gets the title property.
         * @return QuestType title.
         */
        public String getTitle() {
            return title;
        }

        /**
         * Gets the description property.
         * @return QuestType description.
         */
        public String getDescription() {
            return description;
        }
    }

    /**
     * QuestLevel enumeration to store the different levels that the
     * different QuestTypes can be at as a Player progresses in game.
     */
    public enum QuestLevel {
        ONE("I", Constants.QUEST_LEVEL_ONE_GOAL),
        TWO("II", Constants.QUEST_LEVEL_TWO_GOAL),
        THREE("III", Constants.QUEST_LEVEL_THREE_GOAL),
        FOUR("IV", Constants.QUEST_LEVEL_FOUR_GOAL),
        FIVE("V", Constants.QUEST_LEVEL_FIVE_GOAL),
        SIX("VI", Constants.QUEST_LEVEL_SIX_GOAL),
        SEVEN("VII", Constants.QUEST_LEVEL_SEVEN_GOAL),
        EIGHT("VIII", Constants.QUEST_LEVEL_EIGHT_GOAL),
        NINE("IX", Constants.QUEST_LEVEL_NINE_GOAL),
        TEN("X", Constants.QUEST_LEVEL_TEN_GOAL);

        /**
         * QuestLevel numeral representation.
         */
        private final String numeral;

        /**
         * QuestLevel goal.
         */
        private final Integer goal;

        /**
         * Static copy of values used for retrieving next QuestLevel
         * after an instance of one already exists.
         */
        private static QuestLevel[] values = values();

        /**
         * Constructor to set the numeral and goal properties.
         * @param numeral QuestLevel numeral.
         * @param goal QuestLevel goal.
         */
        QuestLevel(String numeral, Integer goal) {
            this.numeral = numeral;
            this.goal = goal;
        }

        /**
         * Gets the next QuestLevel based on the values() of this Enumeration.
         * @return QuestLevel.
         */
        public QuestLevel next() {
            return values[(this.ordinal() + 1) % values.length];
        }

        /**
         * Gets the numeral property.
         * @return QuestLevel numeral.
         */
        public String getNumeral() {
            return numeral;
        }

        /**
         * Gets the goal property.
         * @return QuestLevel goal.
         */
        public Integer getGoal() { return goal; }
    }
}
