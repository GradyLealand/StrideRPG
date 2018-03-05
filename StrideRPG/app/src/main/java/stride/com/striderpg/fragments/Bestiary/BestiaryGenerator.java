package stride.com.striderpg.fragments.Bestiary;


import java.util.ArrayList;

import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Enemy.Boss;
import stride.com.striderpg.rpg.models.Enemy.Enemy;
import stride.com.striderpg.rpg.models.Enemy.Monster;

public class BestiaryGenerator {
    private static final String TAG = "BestiaryGenerator";

    private ArrayList<Enemy> enemies = new ArrayList<>();

    BestiaryGenerator() {
        buildBestiary();
    }

    private void buildBestiary() {
        for (Enums.Enemies enemy : Enums.Enemies.values()) {
            if (enemy.getType().equals(Enums.EnemyType.BOSS)) {
                enemies.add(new Boss(
                        enemy.getName(),
                        enemy,
                        enemy.getEnemyIcon())
                );
            } else if (enemy.getType().equals(Enums.EnemyType.MONSTER)) {
                enemies.add(new Monster(
                        enemy.getName(),
                        enemy,
                        enemy.getEnemyIcon())
                );
            }
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
