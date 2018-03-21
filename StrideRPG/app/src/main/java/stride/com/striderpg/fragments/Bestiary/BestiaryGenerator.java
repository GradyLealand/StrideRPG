package stride.com.striderpg.fragments.Bestiary;


import java.util.ArrayList;

import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Enemy.Boss;
import stride.com.striderpg.rpg.models.Enemy.Enemy;
import stride.com.striderpg.rpg.models.Enemy.Monster;

/**
 * BestiaryGenerator used to retrieve/generate the Enemy ArrayList
 * for the Active Player.
 */
public class BestiaryGenerator {

    /**
     * BestiaryGenerator Logging tag.
     */
    private static final String TAG = "BestiaryGenerator";

    /**
     * Enemy ArrayList to hold each Enemy that will be inside of the
     * Bestiary.
     */
    private ArrayList<Enemy> enemies = new ArrayList<>();

    /**
     * Constructor that calls the buildBestiary() method to generate
     * the Bestiary information/data.
     */
    BestiaryGenerator() {
        buildBestiary();
    }

    /**
     * Build out the enemies ArrayList by looping through each Enemy
     * contained inside of the Enemies Enumeration.
     */
    private void buildBestiary() {
        for (Enums.Enemies enemy : Enums.Enemies.values()) {

            // BOSS Enemy Enum.
            if (enemy.getType().equals(Enums.EnemyType.BOSS)) {
                enemies.add(new Boss(
                        enemy.getName(),
                        enemy,
                        enemy.getEnemyIcon())
                );

            // MONSTER Enemy Enum.
            } else if (enemy.getType().equals(Enums.EnemyType.MONSTER)) {
                enemies.add(new Monster(
                        enemy.getName(),
                        enemy,
                        enemy.getEnemyIcon())
                );
            }
        }
    }

    /**
     * Enemy ArrayList getter.
     * @return enemies ArrayList.
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
