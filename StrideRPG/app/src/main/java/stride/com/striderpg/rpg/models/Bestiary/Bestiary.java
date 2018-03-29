package stride.com.striderpg.rpg.models.Bestiary;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;

/**
 * A Bestiary class to store a Players enemy encounters in game and
 * the amount of an enemy they have defeated.
 */
public class Bestiary {

    /**
     * PropertyChangeSupport object to deal with raising events when
     * a property on this object/bean is changed.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /**
     * HashMap to store each type of Monster as the key and an Integer
     * value for the amount of times a Player has defeated this enemy
     * type.
     */
    private HashMap<String, Integer> enemies = new HashMap<>();

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Bestiary.class).
     */
    public Bestiary() {
        // Loop through each enemy type available and put a default value of 0 into the HashMap
        // with the Enemies as the key.
        for (Enums.Enemies enemyType : Enums.Enemies.values()) {
            enemies.put(enemyType.name(), 0);
        }
    }

    /**
     * Implementation of a Bestiary toString method to print out the
     * Properties of a Bestiary.
     * @return Properties of Bestiary object.
     */
    @Override
    public String toString() {
        return "Bestiary{" +
                "enemies=" + enemies +
                '}';
    }

    /**
     * Attach a new PropertyChangeListener to this classes
     * PropertyChangeSupport object.
     * @param listener PropertyChangeSupport object.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Update an Enemies in the Bestiary by 1.
     * @param type Enemies being updated.
     */
    public void update(Enums.Enemies type) {
        // Update Bestiary value with given EnemyType by 1.
        enemies.put(type.name(), enemies.get(type.name()) + 1);
        changes.firePropertyChange(
                Constants.PROPERTY_BESTIARY_UPDATED,
                type, enemies.get(type.name())
        );
    }

    /**
     * Retrieve the Bestiaries enemies HashMap.
     * @return enemies HashMap.
     */
    public HashMap<String, Integer> getEnemies() {
        return enemies;
    }
}
