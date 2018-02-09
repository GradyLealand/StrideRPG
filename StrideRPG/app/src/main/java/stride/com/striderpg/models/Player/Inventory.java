package stride.com.striderpg.models.Player;


import java.util.HashMap;
import java.util.Map;

import stride.com.striderpg.models.Item;

/**
 * A Inventory class to represent a Player's Inventory.
 */
public class Inventory {

    /**
     * Map of type String, Item to store the different Items inside
     * of a Players Inventory and use unique a String to identify them.
     */
    private Map<String, Item> items = new HashMap<>();

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Inventory.class).
     */
    public Inventory() { }

    /**
     * Implementation of an Inventories toString method to print out each Item
     * in the ArrayList items.
     *
     * @return Properties of each Item in Inventory ArrayList<Item>.
     */
    @Override
    public String toString() {
        return "Inventory{" +
                "items=" + items +
                '}';
    }

    /**
     * Get an Inventories items Map property.
     * @return items Map property.
     */
    public Map<String, Item> getItems() {
        return items;
    }
}
