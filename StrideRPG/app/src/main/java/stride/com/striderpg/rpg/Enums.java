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
        COMMON("Common", "#F4F4F4"),
        UNCOMMON("Uncommon", "#66F00F"),
        RARE("Rare", "#0FD2F0"),
        EPIC("Epic", "#9D0FF0"),
        LEGENDARY("Legendary", "#F0C60F");

        /**
         * Item Rarity name property.
         */
        private final String name;

        /**
         * Item Rarity color property.
         */
        private final String color;

        /**
         * Constructor method to set name property.
         */
        ItemRarity(String name, String color) {
            this.name = name;
            this.color = color;
        }

        /**
         * Get a random ItemRarity based on weighted percentages.
         * TODO : Move weights out into the Constants class.
         * COMMON = 65%
         * UNCOMMON = 15%
         * RARE = 10%
         * EPIC = 7%
         * LEGENDARY = 3%
         */
        public static ItemRarity weightedRarity() {
            double d = Math.random() * 100;
            if ((d -= 55) < 0) return COMMON;
            if ((d -= 20) < 0) return UNCOMMON;
            if ((d -= 15) < 0) return RARE;
            if ((d - 7) < 0) return EPIC;
            return LEGENDARY;
        }

        public String getName() {
            return name;
        }
        public String getColor() {
            return color;
        }
    }

    /**
     * ItemType Enumeration to store each possible ItemType in the game.
     */
    public enum ItemType {
        WEAPON("Weapon", R.drawable.ic_broadsword),
        HELMET("Helmet", R.drawable.ic_visored_helm),
        BOOTS("Boots", R.drawable.ic_leg_armor);

        /**
         * Item Type name property.
         */
        private final String name;

        /**
         * Item Icon property.
         */
        private Integer icon;

        /**
         * Constructor to set the name property.
         */
        ItemType(String name, Integer icon) {
            this.name = name;
            this.icon = icon;
        }

        public String getName() {
            return name;
        }
        public Integer getIcon() {
            return icon;
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
        ALIEN("Alien", EnemyType.MONSTER, R.drawable.ic_alien_bug),
        BRUTE("Brute", EnemyType.MONSTER, R.drawable.ic_brute),
        DINOSAUR("Dinosaur", EnemyType.MONSTER, R.drawable.ic_dinosaur_rex),
        BOWMAN("Bowman", EnemyType.MONSTER, R.drawable.ic_bowman),

        DRAGON("Dragon", EnemyType.BOSS, R.drawable.ic_double_dragon),
        REAPER("Reaper", EnemyType.BOSS, R.drawable.ic_grim_reaper),
        ANUBIS("Anubis", EnemyType.BOSS, R.drawable.ic_anubis);

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
         */
        Enemies(String name, EnemyType type, Integer enemyIcon) {
            this.name = name;
            this.type = type;
            this.enemyIcon = enemyIcon;
        }

        /**
         * Return a random Enemies enum based on the EnemyType passed.
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

        public String getName() {
            return name;
        }
        public EnemyType getType() {
            return type;
        }
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

        public Integer getNumber() {
            return number;
        }
        public String getNumeral() {
            return numeral;
        }
        public Integer getExpires() {
            return expires;
        }
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
        QUEST_COMPLETE("Quest"),
        BOSS_EXPIRE("Boss Expiration"),
        BOSS_DEFEAT("Boss Defeat");

        /**
         * ActivityType name.
         */
        private final String name;

        /**
         * Constructor to set the name property.
         */
        ActivityType(String name) {
            this.name = name;
        }

        /**
         * Gets a random generic ActivityType.
         * A generic ActivityType includes the LOOT and ENEMY types.
         */
        public static ActivityType generic() {
            Random random = new Random();
            Double percent = random.nextDouble() * 100;
            if (percent < 50)
                return LOOT;
            else
                return ENEMY;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * QuestType enumeration to store the different quests inside of the game.
     */
    public enum QuestType {
        DEFEAT_ENEMIES(Constants.QUEST_DEFEAT_ENEMIES_TITLE, Constants.QUEST_DEFEAT_ENEMIES_DESCRIPTION, R.drawable.ic_defeat_enemies),
        DEFEAT_BOSSES(Constants.QUEST_DEFEAT_BOSSES_TITLE, Constants.QUEST_DEFEAT_BOSSES_DESCRIPTION, R.drawable.ic_defeat_bosses),
        FAIL_DEFEAT_ENEMIES(Constants.QUEST_FAIL_DEFEAT_ENEMIES_TITLE, Constants.QUEST_FAIL_DEFEAT_ENEMIES_DESCRIPTION, R.drawable.ic_fail_enemies),
        FAIL_DEFEAT_BOSSES(Constants.QUEST_FAIL_DEFEAT_BOSSES_TITLE, Constants.QUEST_FAIL_DEFEAT_BOSSES_DESCRIPTION, R.drawable.ic_fail_boss),
        LOOT_ITEMS(Constants.QUEST_LOOT_ITEMS_TITLE, Constants.QUEST_LOOT_ITEMS_DESCRIPTION, R.drawable.ic_loot_items),
        TAKE_STEPS(Constants.QUEST_TAKE_STEPS_TITLE, Constants.QUEST_TAKE_STEPS_DESCRIPTION, R.drawable.ic_steps);

        /**
         * QuestType title.
         */
        private final String title;

        /**
         * QuestType description.
         */
        private final String description;

        /**
         * QuestType icon.
         */
        private final Integer icon;

        /**
         * Constructor to set the title and description properties.
         */
        QuestType(String title, String description, Integer icon) {
            this.title = title;
            this.description = description;
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }
        public String getDescription() {
            return description;
        }
        public Integer getIcon() {
            return icon;
        }
    }

    /**
     * QuestLevel enumeration to store the different levels that the
     * different QuestTypes can be at as a Player progresses in game.
     */
    public enum QuestLevel {
        ONE(1, "I"),
        TWO(2, "II"),
        THREE(3, "III"),
        FOUR(4, "IV"),
        FIVE(5, "V"),
        SIX(6, "VI"),
        SEVEN(7, "VII"),
        EIGHT(8, "VIII"),
        NINE(9, "IX"),
        TEN(10, "X");

        /**
         * QuestLevel number Integer.
         */
        private final Integer number;

        /**
         * QuestLevel numeral representation.
         */
        private final String numeral;


        /**
         * Static copy of values used for retrieving next QuestLevel
         * after an instance of one already exists.
         */
        private static QuestLevel[] values = values();

        /**
         * Constructor to set the numeral and goal properties.
         */
        QuestLevel(Integer number, String numeral) {
            this.number = number;
            this.numeral = numeral;
        }

        /**
         * Gets the next QuestLevel based on the values() of this Enumeration.
         */
        public QuestLevel next() {
            return values[(this.ordinal() + 1) % values.length];
        }

        public Integer getNumber() {
            return number;
        }
        public String getNumeral() {
            return numeral;
        }
    }
}
