package stride.com.striderpg.rpg.models.Player;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Item.Item;

/**
 * A Equipment class to represent a Player's Equipment.
 */
public class Equipment {

    /**
     * PropertyChangeSupport object to deal with raising events when a Property on this object/bean
     * is changed.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /**
     * Map of type String, Item to store the different Items inside
     * of a Players Equipment and use unique a String to identify them.
     */
    private Map<String, Item> slots = new HashMap<>();

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Equipment.class).
     */
    public Equipment() { }

    /**
     * Implementation of an Inventories toString method to print out each Item
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
     * Attach a new PropertyChangeListener to this classes PropertyChangeSupport object.
     * @param listener Listener implementation.
     */
    public void addPropertychangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Replace a slot in the Equipment with a new Item.
     * @param type Item type / Equipment slot being replaced.
     * @param item Item being set to equipment slot.
     */
    public void replaceItem(Enums.ItemType type, Item item) {
        slots.put(type.name(), item);
    }

    /**
     * Set an item at a specific slot to null.
     * @param type ItemType to set to null.
     */
    public void destroyItem(Enums.ItemType type) {
        slots.put(type.name(), null);
    }

    /**
     * Get the Equipment slots HashMap.
     * @return slots HashMap.
     */
    public Map<String, Item> getSlots() {
        return slots;
    }
}
