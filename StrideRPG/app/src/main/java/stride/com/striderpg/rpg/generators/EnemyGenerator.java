package stride.com.striderpg.rpg.generators;


import android.util.Log;

import java.util.Random;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
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
     * EnemyGenerator Logging tag.
     */
    private static final String TAG = "EnemyGenerator";

    /**
     * Random instance available to the EnemyGenerator class for creating new enemies.
     */
    private static Random r = new Random();

    /**
     * String array for choosing a random adjective for a new Enemy.
     */
    private static final String[] enemyAdjectives = { "Agile", "Ever-Watchful", "Dirty", "Tricky", "Vile",
            "Profane", "Unjust", "Unfair", "Unkind", "Power-mad", "Irreverent", "Self-Centered" };

    /**
     * Generate a new enemy based off of the Player object passed into the function.
     * @param p Player used to determine enemy properties.
     * @return Newly generated Enemy object with random properties.
     */
    public static Enemy generate(Player p) {

        // TODO: Weighted enemy types based on current player level.
        // Choose random enemy type.
        Enums.EnemyType enemyType = Enums.random(Enums.EnemyType.class);

        // Calculate generic enemy values based on Player parameter.
        String name = parseName(enemyType);
        int health = calculateEnemyHealth(p);
        int minDamage = calculateEnemyMinDamage(p);
        int maxDamage = calculateEnemyMaxDamage(p);
        int experience = calculateEnemyExp(p);
        int icon = parseIcon(enemyType);

        Enemy newEnemy = new Enemy(name, enemyType, health, minDamage, maxDamage, icon, experience);

        Log.d(TAG, String.format(G.locale, "generate:success:enemy=%s", newEnemy));
        return newEnemy;
    }

    /**
     * Calculate a new Enemies experience reward based on the active Player objects current level.
     * @param p activePlayer object.
     * @return Enemy experience reward.
     */
    private static int calculateEnemyExp(Player p) {
        int expReward = p.getLevel() * Constants.ENEMY_EXPERIENCE_MODIFIER + r.nextInt(50);

        Log.d(TAG, String.format(G.locale, "calculateEnemyExp:success:expReward=%s", expReward));
        return expReward;
    }

    /**
     * Parse out the new enemies icon asset and return the integer id.
     * @param enemyType Type of Enemy being generated.
     * @return Enemy icon integer id.
     */
    private static int parseIcon(Enums.EnemyType enemyType) {
        int iconId = 0;
        try {
            iconId = R.drawable.class.getField(enemyType.getName().toLowerCase()).getInt(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Log.e(TAG, "parseIcon:error:", e);
        }

        Log.d(TAG, String.format(G.locale, "parseIcon:success:icon=%d", iconId));
        return iconId;
    }

    /**
     * Generate a new Enemy name using the EnemyType and random adjective.
     * @param enemyType Type of Enemy being generated.
     * @return Enemy name.
     */
    private static String parseName(Enums.EnemyType enemyType) {
        String name = enemyAdjectives[r.nextInt(enemyAdjectives.length)] + " " + enemyType.getName();

        Log.d(TAG, String.format(G.locale, "parseName:success:name=%s", name));
        return name;
    }

    /**
     * Calculate a new Enemies health.
     * @param p Player object for determining Enemies health.
     * @return New Enemy health.
     */
    private static int calculateEnemyHealth(Player p) {
        int enemyHealth = (p.getLevel() * Constants.ENEMY_HEALTH_MODIFIER) + r.nextInt(10);

        Log.d(TAG, String.format(G.locale, "calculateEnemyHealth:success:enemyHealth=%d", enemyHealth));
        return  enemyHealth;
    }

    /**
     * Calculate a new Enemies minimum damage.
     * @param p Player object for determining Enemies minimum damage.
     * @return New Enemy minimum damage.
     */
    private static int calculateEnemyMinDamage(Player p) {
        int minDamage = (p.getLevel() * Constants.ENEMY_DAMAGE_MODIFIER) + r.nextInt(10) / 2;

        Log.d(TAG, String.format(G.locale, "calculateEnemyMinDamage:success:minDamage=%d", minDamage));
        return minDamage;
    }

    /**
     * Calculate a new Enemies maximum damage.
     * @param p Player object for determining Enemies maximum damage.
     * @return New Enemy maximum damage.
     */
    private static int calculateEnemyMaxDamage(Player p) {
        int maxDamage = (p.getLevel() * Constants.ENEMY_DAMAGE_MODIFIER) + r.nextInt(10) * 2;

        Log.d(TAG, String.format(G.locale, "calculateEnemyMaxDamage:success:maxDamage=%d", maxDamage));
        return maxDamage;
    }
}
