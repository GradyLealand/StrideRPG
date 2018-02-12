package stride.com.striderpg.rpg;


import java.util.Random;


/**
 * Custom Class to hold the different enumerations that the RPG package can use freely.
 */
public class Enums {

    /**
     * Generic Function to return a completely random enum from any enum instance passed
     * into the function.
     * @param tClass .class property of the Enum object being randomly chosen.
     * @param <T> The enum object the method is being called from.
     * @return Random enum constant from specified enum.
     */
    public static <T extends Enum<?>> T random(Class<T> tClass) {
        Random random = new Random();
        int x = random.nextInt(tClass.getEnumConstants().length);
        return tClass.getEnumConstants()[x];
    }

    /**
     * Enumeration ItemRarity to hold the different rarities that a Item can be.
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
         * TODO: SUBJECT TO CHANGE.
         * Custom function to return a random ItemRarity using a weighted percentage for each one.
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

        /**
         * Get the enums name property.
         * @return ItemType name.
         */
        public String getName() {
            return name;
        }
    }

    /**
     * Enumeration EnemyType to hold the different enemy types in game.
     */
    public enum EnemyType {
        TROLL("Troll"),
        GOBLIN("Goblin"),
        LIZARD("Lizard"),
        NECROMANCER("Necromancer");

        /**
         * ItemType name property for the ItemType constant.
         */
        private final String name;

        /**
         * EnemyType constructor to set the name property.
         * @param name Name of constant.
         */
        EnemyType(String name) {
            this.name = name;
        }

        /**
         * Get the enums name property.
         * @return EnemyType name.
         */
        public String getName() {
            return name;
        }
    }
}
