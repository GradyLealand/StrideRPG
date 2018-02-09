package stride.com.striderpg.models.Player;


import java.util.ArrayList;

import stride.com.striderpg.models.Item;

/**
 * A Inventory class to represent a Player's Inventory.
 */
public class Inventory {

    /**
     * ArrayList of type Item to store the different Items inside
     * of a Players Inventory.
     */
    private ArrayList<Item> items = new ArrayList<>();

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
     * Get a Inventories items ArrayList property.
     * @return items ArrayList property.
     */
    public ArrayList<Item> getItems() {
        return items;
    }
}
