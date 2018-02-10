package stride.com.striderpg.models.Player;


import java.util.HashMap;
import java.util.Map;

import stride.com.striderpg.global.Globals;
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

    /**
     * Function to build a unique key for this Item in the Map<String, Item> collection.
     * @return Unique item id built from activePlayers unique identifier.
     */
    public String makeKey() {
        String base = Globals.activePlayer.getUniqueId().substring(0, 8);
        StringBuilder sb = new StringBuilder(base.length());
        double rand;

        for (char c : base.toCharArray()) {
            rand = Math.random();
            if (rand < 0.34)
                sb.append(c);
            else if (rand < 0.67)
                sb.insert(sb.length() / 2, c);
            else
                sb.insert(0, c);
        }

        // If the case comes up where a generated key is equal to a key already present in this
        // users inventory, generate a new key.
        for (String s : items.keySet()) {
            if (s.equals(sb.toString())) {
                makeKey();
            }
        }
        return sb.toString();
    }
}
