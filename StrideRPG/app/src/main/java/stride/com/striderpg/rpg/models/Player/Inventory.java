package stride.com.striderpg.rpg.models.Player;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.models.Item.Item;

/**
 * A Inventory class to represent a Player's Inventory.
 */
public class Inventory {

    /**
     * PropertyChangeSupport object to deal with raising events when a Property on this object/bean
     * is changed.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

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
     *  Construct an Inventory with a populated HashMap based on the Items passed into the constructor.
     * @param items Items to add to inventory.
     */
    public Inventory(Item... items) {
        for (Item i : items) {
            addItem(i);
        }
    }

    /**
     * Implementation of an Inventories toString method to print out each Item
     * in the ArrayList items.
     * @return Properties of each Item in Inventory ArrayList<Item>.
     */
    @Override
    public String toString() {
        return "Inventory{" +
                "items=" + items +
                '}';
    }

    /**
     * Attach a new PropertyChangeListener to this classes PropertyChangeSupport object.
     * @param listener Listener implementation.
     */
    public void addPropertychangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Add an fragment_leaderboards_item directly to the items HashMap with a generated unique id.
     * @param item Item to add to items.
     */
    public void addItem(Item item) {
        items.put(makeKey(), item);
    }

    /**
     * Remove an fragment_leaderboards_item from items HashMap by unique key.
     * @param key Unique key to find fragment_leaderboards_item in HashMap.
     */
    public void removeItem(String key) {
        items.remove(key);
    }

    /**
     * Function to build a unique key for this Item in the Map<String, Item> collection.
     * @return Unique fragment_leaderboards_item id built from activePlayers unique identifier.
     */
    private String makeKey() {
        String base = Constants.ITEM_MAKE_BASE;
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
        // users inventory, generate a new key...
        for (String s : items.keySet()) {
            if (s.equals(sb.toString())) {
                makeKey();
            }
        }
        return sb.toString();
    }

    /**
     * Get an Inventories items Map property.
     * @return items Map property.
     */
    public Map<String, Item> getItems() {
        return items;
    }
}
