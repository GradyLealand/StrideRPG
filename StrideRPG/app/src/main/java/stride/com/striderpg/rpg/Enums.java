package stride.com.striderpg.rpg;


import java.util.ArrayList;
import java.util.Random;

import stride.com.striderpg.R;


/**
 * Custom Class to hold the different enumerations that the RPG
 * package can use freely.
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
     * Enumeration ItemRarity to hold the different rarities that
     * an Item can be.
     */
    public enum ItemRarity {
        COMMON("Common"),
        UNCOMMON("Uncommon"),
        RARE("Rare"),
        EPIC("Epic"),
        LEGENDARY("Legendary");

        /**
         * ItemRarity name property for the ItemRarity constant.
         */
        private final String name;

        /**
         * ItemRarity constructor to set the name property.
         * @param name Name of constant.
         */
        ItemRarity(String name) {
            this.name = name;
        }

        /**
         * Get the enums name property.
         * @return ItemRarity name.
         */
        public String getName() {
            return name;
        }

        /**
         * Custom function to return a random ItemRarity using a
         * weighted percentage for each one.
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
     * Enumeration ItemType to hold the different Item types a item can be.
     */
    public enum ItemType {
        WEAPON("Weapon"),
        HELMET("Helmet"),
        CHEST("Chest"),
        LEGS("Legs"),
        BOOTS("Boots");

        /**
         * ItemType name property for the ItemType constant.
         */
        private final String name;

        /**
         * ItemType constructor to set the name property.
         * @param name Name of constant.
         */
        ItemType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum EnemyType {
        MONSTER,
        BOSS
    }

    /**
     * Enumeration Enemies to hold the different enemy types in game.
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
         * Enemies Icon integer id.
         */
        private final Integer enemyIcon;

        /**
         * Enemies constructor to set the name property.
         * @param name Name of constant.
         * @param type Type of enemy.
         * @param enemyIcon Enemies icon resource id.
         */
        Enemies(String name, EnemyType type, Integer enemyIcon) {
            this.name = name;
            this.type = type;
            this.enemyIcon = enemyIcon;
        }

        /**
         * Return a random Enemies enum based on the EnemyType passed.
         * @param type EnemyType to grab all Enemies of type.
         * @return One enemy of specified type.
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
     * Enumeration BossTier to hold the different Boss tiers in game.
     */
    public enum BossTier {
        ONE(1, "I", 30),
        TWO(2, "II", 60),
        THREE(3, "III", 90);

        /**
         * Number representation of enum.
         */
        private final Integer number;

        /**
         * Numeral representation of enum.
         */
        private final String numeral;

        /**
         * Expiration in minutes for enum.
         */
        private final Integer expires;

        /**
         * Constructor method for setting number and numeral properties.
         * @param number Enum number.
         * @param numeral Enum numeral.
         * @param expires Enum expires.
         */
        BossTier(Integer number, String numeral, Integer expires) {
            this.number = number;
            this.numeral = numeral;
            this.expires = expires;
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
    }

    /**
     * Enumeration PlayerSort for holding the different possible
     * sort types for the any Collections sorting of the Player class.
     */
    public enum PlayerSort {
        EXPERIENCE,
        LEVEL,
        ENEMIES_DEFEATED,
        STEPS
    }

    /**
     * Enumeration ActivityType to hold the different possible
     * encounters that a Player can generate whenever a new encounter
     * is being generated.
     */
    public enum ActivityType {
        ENEMY("Monster"),
        LOOT("Loot"),
        BOSS_EXPIRE("Boss Expiration"),
        BOSS_DEFEAT("Boss Defeat");

        /**
         * String to hold the activity types human friendly name.
         */
        private final String name;

        /**
         * Constructor method for setting an activity types name.
         * @param name ActivityType readable name.
         */
        ActivityType(String name) {
            this.name = name;
        }

        /**
         * Retrieve the ActivityType name property.
         * @return name property.
         */
        public String getName() {
            return name;
        }

        /**
         * Choose between a generic Monster or Loot ActivityType.
         * @return Enumeration ActivityType Monster/Loot.
         */
        public static ActivityType generic() {
            // Generate percent roll 0 to 100 to choose LOOT or ENEMY ActivityType.
            Random random = new Random();
            Double percent = random.nextDouble() * 100;
            if (percent < 50)
                return LOOT;
            else
                return ENEMY;
        }
    }

    /**
     * Enumeration QuestType for holding the different Quests that a
     * Player has in game.
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
         * QuestType Constructor to initialize the QuestTypes with
         * proper title and description.
         * @param title QuestType title.
         * @param description QuestType description.
         */
        QuestType(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * Enumeration QuestLevel to hold the different possible Quest levels that
     * a Player can reach while progressing through the game.
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
         * QuestLevel String to represent a roman numeral for
         * QuestLevel representation.
         */
        private final String numeral;

        /**
         * QuestLevel Integer to represent the goal a Player must
         * reach to complete this level
         * of the Quest.
         */
        private final Integer goal;

        /**
         * Create a static copy of the QuestLevel values to avoid
         * copying each time next is called.
         */
        private static QuestLevel[] values = values();

        /**
         * QuestLevel Constructor to set the numeral property on construction.
         * @param numeral QuestLevel numeral String.
         */
        QuestLevel(String numeral, Integer goal) {
            this.numeral = numeral;
            this.goal = goal;
        }

        public QuestLevel next() {
            return values[(this.ordinal() + 1) % values.length];
        }

        public String getNumeral() {
            return numeral;
        }

        public Integer getGoal() { return goal; }
    }
}
