package stride.com.striderpg.rpg.Generators;


import java.util.Random;

import stride.com.striderpg.rpg.models.Item.Item;
import stride.com.striderpg.rpg.models.Player.Player;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;

/**
 * RPG Generator class to generate in game items with stats and attributes related to the Global
 * Player activePlayer Object that each game session will have.
 */
public class ItemGenerator {

    /**
     * Random instance available to the ItemGenerator class for creating new items and
     * determining random values for making random choices.
     */
    private static Random r = new Random();

    /**
     * String[] Array to hold the different possible Item name adjectives
     */
    private static String[] itemAdjectives = { "Therapeutic", "Greasy", "Private", "Glamorous", "Withered", "Profane", "Webbed", "Suspicious", "Large" };

    /**
     * Generate a random item based off of the Player object passed into the function.
     * @param p Player used to determine item properties.
     * @return Newly generated Item object with randomized properties.
     */
    public static Item generate(Player p) {
        // Retrieve random Enumeration value for the ItemType and ItemRarity enum for the new Item.
        Enums.ItemType itemType = Enums.random(Enums.ItemType.class);
        Enums.ItemRarity itemRarity = Enums.ItemRarity.weightedRarity();

        // Set the stats/properties of the new Item.
        int[] stats = buildItemStats(itemRarity, p);
        int powerLevel = generatePowerLevel(stats, p);

        String name = parseName(itemType);
        return new Item(name, powerLevel, stats[0], stats[1], stats[2], itemRarity, itemType);
    }

    /**
     * Generate a random default item with default stats, used to generate a new Inventory with some
     * rookie items.
     * @return Rookie Item.
     */
    public static Item generate() {
        // Retrieve random Enumeration value for the ItemType and ItemRarity enum for the new Item.
        Enums.ItemType itemType = Enums.random(Enums.ItemType.class);
        Enums.ItemRarity itemRarity = Enums.ItemRarity.COMMON;

        // Set the stats/properties of the new Item.
        int[] stats = { r.nextInt(3) + 1, r.nextInt(3) + 1, r.nextInt(3) + 1 };
        int powerLevel = Constants.ITEM_POWER_LEVEL_MODIFIER;

        String name = parseName(itemType);
        return new Item(name, powerLevel, stats[0], stats[1], stats[2], itemRarity, itemType);
    }

    /**
     * Parse out a new Items name by choosing a random adjective and appending the Items ItemType enum
     * to the end of the String after fixing the capitalization on the ItemType.
     * @param itemType New Item ItemType.
     * @return New Item Name.
     */
    private static String parseName(Enums.ItemType itemType) {
        return itemAdjectives[r.nextInt(itemAdjectives.length)] + " " + itemType.getName();
    }

    /**
     * Generate a new Items power level by adding the Items stats together, that number is added
     * to the active Players level, then that number is multiplied by the POWER_LEVEL_MODIFIER.
     * @param stats New Item stats.
     * @param p activePlayer Player object.
     * @return New Item powerlevel.
     */
    private static int generatePowerLevel(int[] stats, Player p) {
        int powerLevel = 0;
        for (int stat : stats) {
            powerLevel += stat;
        }
        return (powerLevel + p.getLevel()) * Constants.ITEM_POWER_LEVEL_MODIFIER;
    }

    /**
     * Create an int[] array to hold the stats for the new Item. Randomly choose a skill
     * each loop iteration and increment the skill bonus for the Item.
     * @param itemRarity Item itemRarity chosen.
     * @param p Player.
     * @return int[] array holding new Item stats.
     */
    private static int[] buildItemStats(Enums.ItemRarity itemRarity, Player p) {
        int strBoost = 0, vitBoost = 0, spdBoost = 0, points = 0;

        // Determine amount of points this item can have put into its attribute boost.
        switch (itemRarity) {
            case COMMON: points = p.getLevel() + 3; break;
            case UNCOMMON: points = p.getLevel() + 5; break;
            case RARE: points = p.getLevel() + 7; break;
            case EPIC: points = p.getLevel() + 10; break;
            case LEGENDARY: points = p.getLevel() + 15; break;
        }

        // Loop through these points and randomly select an item to add skill points too.
        for (int i = 0; i < points; i++) {
            int choice = r.nextInt(3) % 3;
            switch (choice) {
                case 0: strBoost++; break;
                case 1: vitBoost++; break;
                case 2: spdBoost++; break;
            }
        }
        return new int[] { vitBoost, strBoost, spdBoost };
    }
}
