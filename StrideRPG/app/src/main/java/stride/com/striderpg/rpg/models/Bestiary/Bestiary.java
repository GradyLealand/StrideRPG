package stride.com.striderpg.rpg.models.Bestiary;


import java.util.HashMap;

import stride.com.striderpg.rpg.Enums;

/**
 * A Bestiary class to store a Players enemy encounters in game and the amount of an enemy they
 * have defeated.
 */
public class Bestiary {

    /**
     * HashMap to store each type of Enemy as the key and an Integer value for the amount of times
     * a Player has defeated this enemy type.
     */
    private HashMap<String, Integer> enemies;

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Bestiary.class).
     */
    public Bestiary() {
        // Loop through each enemy type available and put a default value of 0 into the HashMap
        // with the EnemyType as the key.
        for (Enums.EnemyType enemyType : Enums.EnemyType.values()) {
            enemies.put(enemyType.name(), 0);
        }
    }

    /**
     * Implementation of a Bestiary toString method to print out the Properties of a Bestiary.
     * @return Properties of Bestiary object.
     */
    @Override
    public String toString() {
        return "Bestiary{" +
                "enemies=" + enemies +
                '}';
    }

    /**
     * Update an EnemyType in the Bestiary by 1.
     * @param type EnemyType being updated.
     */
    public void update(Enums.EnemyType type) {
        enemies.put(type.name(), enemies.get(type.name()) + 1);
    }

    /**
     * Retrieve the Bestiaries enemies HashMap.
     * @return enemies HashMap.
     */
    public HashMap<String, Integer> getEnemies() {
        return enemies;
    }


}
