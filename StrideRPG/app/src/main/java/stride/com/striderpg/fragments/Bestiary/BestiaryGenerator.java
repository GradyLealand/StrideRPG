package stride.com.striderpg.fragments.Bestiary;


import java.util.ArrayList;

import stride.com.striderpg.global.G;
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
    private ArrayList<BestiaryAdapter.EnemyDataHolder> enemies = new ArrayList<>();

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

            // Get the initial amount of this enemy defeated when building Bestiary.
            Integer initialAmount = G.activePlayer.getBestiary().getEnemies().get(enemy.name());

            // BOSS Enemy Enum.
            if (enemy.getType().equals(Enums.EnemyType.BOSS)) {
                enemies.add(new BestiaryAdapter.EnemyDataHolder(
                        new Boss(enemy.getName(), enemy, enemy.getEnemyIcon()), initialAmount));

            // MONSTER Enemy Enum.
            } else if (enemy.getType().equals(Enums.EnemyType.MONSTER)) {
                enemies.add(new BestiaryAdapter.EnemyDataHolder(
                        new Monster(enemy.getName(), enemy, enemy.getEnemyIcon()), initialAmount));
            }
        }
    }

    /**
     * Enemy ArrayList getter.
     * @return enemies ArrayList.
     */
    public ArrayList<BestiaryAdapter.EnemyDataHolder> getEnemies() {
        return enemies;
    }
}
