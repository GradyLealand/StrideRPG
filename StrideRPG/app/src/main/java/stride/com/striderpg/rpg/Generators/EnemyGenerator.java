package stride.com.striderpg.rpg.Generators;


import java.util.Random;

import stride.com.striderpg.R;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Enemy.Enemy;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * RPG Generator class to generate in game enemies with properties related to the Global
 * Player activePlayer Object.
 */
public class EnemyGenerator {

    /**
     * Random instance available to the EnemyGenerator class for creating new enemies.
     */
    private Random r = new Random();

    /**
     * String array for choosing a random adjective for a new Enemy.
     */
    private final String[] enemyAdjectives = { "Agile", "Ever-Watchful", "Dirty", "Tricky", "Vile",
            "Profane", "Unjust", "Unfair", "Unkind", "Power-mad", "Irreverent", "Self-Centered" };

    /**
     * Generate a new enemy based off of the Player object passed into the function.
     * @param p Player used to determine enemy properties.
     * @return Newly generated Enemy object with random properties.
     */
    public Enemy generate(Player p) {
        Enums.EnemyType enemyType = Enums.random(Enums.EnemyType.class);

        int health = calculateEnemyHealth(p);
        int minDamage = calculateEnemyMinDamage(p);
        int maxDamage = calculateEnemyMaxDamage(p);
        int experience = calculateEnemyExp(p);

        String name = parseName(enemyType);
        int icon = parseIcon(enemyType);

        return new Enemy(name, enemyType, health, minDamage, maxDamage, icon, experience);
    }

    /**
     * Calculate a new Enemies experience reward based on the active Player objects current level.
     * @param p activePlayer object.
     * @return Enemy experience reward.
     */
    private int calculateEnemyExp(Player p) {
        return p.getLevel() * Constants.ENEMY_EXPERIENCE_MODIFIER + r.nextInt(50);
    }

    /**
     * Parse out the new enemies icon asset and return the integer id.
     * @param enemyType Type of Enemy being generated.
     * @return Enemy icon integer id.
     */
    private int parseIcon(Enums.EnemyType enemyType) {
        int iconId = 0;
        try {
            iconId = R.drawable.class.getField(enemyType.getName().toLowerCase()).getInt(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return iconId;
    }

    /**
     * Generate a new Enemy name using the EnemyType and random adjective.
     * @param enemyType Type of Enemy being generated.
     * @return Enemy name.
     */
    private String parseName(Enums.EnemyType enemyType) {
        return enemyAdjectives[r.nextInt(enemyAdjectives.length)] + " " + enemyType.getName();
    }

    /**
     * Calculate a new Enemies health.
     * @param p Player object for determining Enemies health.
     * @return New Enemy health.
     */
    private int calculateEnemyHealth(Player p) {
        return (p.getLevel() * Constants.ENEMY_HEALTH_MODIFIER) + r.nextInt(10);
    }

    /**
     * Calculate a new Enemies minimum damage.
     * @param p Player object for determining Enemies minimum damage.
     * @return New Enemy minimum damage.
     */
    private int calculateEnemyMinDamage(Player p) {
        return (p.getLevel() * Constants.ENEMY_DAMAGE_MODIFIER) + r.nextInt(10) / 2;
    }

    /**
     * Calculate a new Enemies maximum damage.
     * @param p Player object for determining Enemies maximum damage.
     * @return New Enemy maximum damage.
     */
    private int calculateEnemyMaxDamage(Player p) {
        return (p.getLevel() * Constants.ENEMY_DAMAGE_MODIFIER) + r.nextInt(10) * 2;
    }
}
