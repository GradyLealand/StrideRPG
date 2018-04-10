package stride.com.striderpg.rpg.models.Player;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Item.Item;

/**
 * A Equipment class to represent a Player's Equipment.
 */
public class Equipment {

    /**
     * PropertyChangeSupport object to deal with raising events
     * when a Property on this object/bean
     * is changed.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /**
     * Map of type String, Item to store the different Items inside
     * of a Players Equipment and use unique a String to identify
     * them.
     */
    private HashMap<String, Item> slots = new HashMap<>();

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Equipment.class).
     */
    public Equipment() { }

    /**
     * Implementation of an Inventories toString method to print out
     * each Item
     * in the ArrayList slots.
     * @return Properties of each Item in Equipment ArrayList<Item>.
     */
    @Override
    public String toString() {

        return "Equipment{" +
                "slots=" + slots +
                '}';
    }

    /**
     * Attach a new PropertyChangeListener to this classes
     * PropertyChangeSupport object.
     */
    public void addPropertychangeListener(PropertyChangeListener listener) {

        changes.addPropertyChangeListener(listener);
    }

    /**
     * Replace a slot in the Equipment with a new Item.
     */
    public void replaceItem(Enums.ItemType type, Item item) {

        slots.put(type.name(), item);
    }

    /**
     * Retrieve a specific item by ItemType from slots HashMap.
     */
    public Item getItem(Enums.ItemType type) {

        return slots.get(type.name());
    }

    /**
     * Set an item at a specific slot to null.
     */
    public void destroyItem(Enums.ItemType type) {
        slots.put(type.name(), null);
    }

    public HashMap<String, Item> getSlots() {
        return slots;
    }
}
