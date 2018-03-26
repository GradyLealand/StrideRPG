package stride.com.striderpg.rpg.generators;


import android.util.Log;

import java.util.Arrays;
import java.util.Random;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.models.Item.Item;
import stride.com.striderpg.rpg.models.Player.Equipment;
import stride.com.striderpg.rpg.models.Player.Player;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;

/**
 * RPG Generator class to generate in game items with stats and attributes related to the Global
 * Player activePlayer Object that each game session will have.
 */
public class ItemGenerator {

    /**
     * ItemGenerator Logging tag.
     */
    private static final String TAG = "ItemGenerator";

    /**
     * String Array to hold the different possible Item name adjectives
     */
    private static String[] itemAdjectives = { "Therapeutic", "Greasy", "Private", "Glamorous",
            "Withered", "Profane", "Webbed", "Suspicious", "Large" };

    /**
     * Generate a random item based off of the Player object passed
     * into the function.
     * @param p Player used to determine item properties.
     * @return Newly generated Item object with randomized properties.
     */
    public static Item generate(Player p) {
        // Generate a random ItemType Enumeration.
        Enums.ItemType type = Enums.random(Enums.ItemType.class);

        // Generate a random ItemRarity Enumeration based on percents/weights.
        Enums.ItemRarity rarity = Enums.ItemRarity.weightedRarity();

        // Generate new Item's stat boost.
        Integer statBoost = buildItemStatBoost(rarity, p);

        // Generate Item name and description.
        String name = parseName(type);
        String description = parseDescription(type);

        return new Item(name, description, type, rarity, statBoost);
    }

    /**
     * Generate a random item of a specific ItemType.
     * @param p Player object to determine Item stats.
     * @param type Item ItemType.
     * @return New random Item of type specified.
     */
    public static Item generate(Player p, Enums.ItemType type) {
        // Generate a random ItemRarity Enumeration based on percent/weights.
        Enums.ItemRarity rarity = Enums.ItemRarity.weightedRarity();

        // Generate new Item's stat boost.
        Integer statBoost = buildItemStatBoost(rarity, p);

        // Generate Item name and description.
        String name = parseName(type);
        String description = parseDescription(type);

        return new Item(name, description, type, rarity, statBoost);
    }

    /**
     * Generate a random item of a specific ItemRarity.
     * @param p Player object to determine Item stats.
     * @param rarity Item ItemRarity.
     * @return New random Item of rarity specified.
     */
    public static Item generate(Player p, Enums.ItemRarity rarity) {
        // Generate a random ItemType Enumeration.
        Enums.ItemType type = Enums.random(Enums.ItemType.class);

        // Generate new Item's stat boost.
        Integer statBoost = buildItemStatBoost(rarity, p);

        // Generate Item name and description.
        String name = parseName(type);
        String description = parseDescription(type);

        return new Item(name, description, type, rarity, statBoost);
    }

    /**
     * Generate a rookie Equipment with an Item of each type in it.
     * @param p Player object.
     * @return Equipment with new items in HashMap.
     */
    public static Equipment generateDefaultInventory(Player p) {
        // Create a new Equipment object to hold the Inventory.
        Equipment equipment = new Equipment();

        // Replace each item in the new Equipment object with newly generated Items
        // for each. Acting as a default set of Items for the Player.
        equipment.replaceItem(Enums.ItemType.BOOTS, generate(p, Enums.ItemType.BOOTS));
        equipment.replaceItem(Enums.ItemType.HELMET, generate(p, Enums.ItemType.HELMET));
        equipment.replaceItem(Enums.ItemType.WEAPON, generate(p, Enums.ItemType.WEAPON));

        return equipment;
    }

    /**
     * Parse out a new Items name by choosing a random adjective and appending the Items ItemType enum
     * to the end of the String after fixing the capitalization on the ItemType.
     * @param itemType New Item ItemType.
     * @return New Item Name.
     */
    private static String parseName(Enums.ItemType itemType) {
        return itemAdjectives[new Random().nextInt(itemAdjectives.length)] +
                " " + itemType.getName();
    }

    /**
     * Create an int[] array to hold the stats for the new Item.
     * Randomly choose a skill each loop iteration and increment the
     * skill bonus for the Item.
     * @param rarity Item itemRarity chosen.
     * @param p Player.
     * @return Integer Item's stat boost.
     */
    private static Integer buildItemStatBoost(Enums.ItemRarity rarity, Player p) {
        // Get the base value for building an Item's stat boost.
        int base = (p.getLevel() / 2);

        // Switch case through the rarity of the Item and build out it's total
        // stat boost based on how rare it is + the base integer value.
        switch(rarity) {
            case COMMON: return base + 2;
            case UNCOMMON: return base + 4;
            case RARE: return base + 6;
            case EPIC: return base + 8;
            case LEGENDARY: return base + 10;
            default: return 0;
        }
    }
}
