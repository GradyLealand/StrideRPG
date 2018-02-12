package stride.com.striderpg.rpg;


import java.util.Random;

/**
 * Custom Class to hold the different global enumerations that the RPG package can use freely.
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
     * Enumeration Rarity to hold the different rarities that a Item can be.
     */
    public enum Rarity {
        COMMON,
        UNCOMMON,
        RARE,
        EPIC,
        LEGENDARY;

        /**
         * // TODO: SUBJECT TO CHANGE.
         * Custom function to return a random Rarity using a weighted percentage for each one.
         * COMMON = 65%
         * UNCOMMON = 15%
         * RARE = 10%
         * EPIC = 7%
         * LEGENDARY = 3%
         * @return Rarity enum based on percent calculated.
         */
        public static Rarity weightedRarity() {
            double d = Math.random() * 100;
            if ((d -= 55) < 0) return COMMON;
            if ((d -= 20) < 0) return UNCOMMON;
            if ((d -= 15) < 0) return RARE;
            if ((d - 7) < 0) return EPIC;
            return LEGENDARY;
        }
    }

    /**
     * Enumeration Type to hold the different Item types a item can be.
     */
    public enum Type {
        WEAPON,
        HELMET,
        CHEST,
        LEGS,
        BOOTS,
    }
}
