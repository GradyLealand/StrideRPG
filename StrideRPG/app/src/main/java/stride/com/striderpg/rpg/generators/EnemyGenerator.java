package stride.com.striderpg.rpg.generators;


import android.util.Log;

import java.util.Random;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Enemy.Monster;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * RPG Generator class to generate in game enemies with properties
 * related to the Global Player activePlayer Object.
 */
public class EnemyGenerator {

    /**
     * EnemyGenerator Logging tag.
     */
    private static final String TAG = "EnemyGenerator";

    /**
     * Random instance available to the EnemyGenerator class for
     * creating new enemies.
     */
    private static Random r = new Random();

    /**
     * String array for choosing a random adjective for a new Monster.
     */
    private static final String[] enemyAdjectives = { "Agile", "Ever-Watchful", "Dirty", "Tricky", "Vile",
            "Profane", "Unjust", "Unfair", "Unkind", "Power-mad", "Irreverent", "Self-Centered" };

    /**
     * Generate a new enemy based off of the Player object passed
     * into the function.
     * @param p Player used to determine enemy properties.
     * @return Newly generated Monster object with random properties.
     */
    public static Monster generate(Player p) {
        // TODO: Weighted enemy types based on current player level.
        // Choose random enemy type.
        Enums.Enemies enemyType = Enums.Enemies.getRandomEnemiesType(Enums.EnemyType.MONSTER);

        // Calculate generic enemy values based on Player parameter.
        String name = parseName(enemyType);
        int level = calculateEnemyLevel(p);
        int health = calculateEnemyHealth(level);
        int experience = calculateEnemyExp(p, level - p.getLevel());
        int icon = parseIcon(enemyType);

        Monster newEnemy = new Monster(name, enemyType, level, health, icon, experience);

        Log.d(TAG, String.format(G.locale, "generate:success:enemy=%s", newEnemy));
        return newEnemy;
    }

    /**
     * Calculate the new enemies level based on the active
     * Player objects current level.
     * @param p activePlayer object.
     * @return Monster level.
     */
    private static int calculateEnemyLevel(Player p) {
        Integer diceRoll = r.nextInt(5);
        int monsterLevel  = (p.getLevel() + 2) - diceRoll;

        // Do not allow the monster to have a level < 1.
        if(monsterLevel < Constants.MINIMUM_LEVEL) {
            monsterLevel = Constants.MINIMUM_LEVEL;
        }

        Log.d(TAG, String.format(G.locale, "calculateEnemyLevel:success:monsterLevel=%d", monsterLevel));
        return monsterLevel;
    }

    /**
     * Calculate a new Enemies experience reward based on the active
     * Player objects current level.
     * @param p activePlayer object.
     * @param l the difference in level
     * @return Monster experience reward.
     */
    private static int calculateEnemyExp(Player p, Integer l) {
        int expReward = p.getLevel() * Constants.ENEMY_EXPERIENCE_MODIFIER
                + (r.nextInt(50) + (Constants.ENEMY_EXPERIENCE_MODIFIER ) * l);

        Log.d(TAG, String.format(G.locale, "calculateEnemyExp:success:expReward=%s", expReward));
        return expReward;
    }

    /**
     * Parse out the new enemies icon asset and return the integer id.
     * @param enemyType Type of Monster being generated.
     * @return Monster icon integer id.
     */
    private static int parseIcon(Enums.Enemies enemyType) {
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
     * Generate a new Monster name using the Enemies and random
     * adjective.
     * @param enemyType Type of Monster being generated.
     * @return Monster name.
     */
    private static String parseName(Enums.Enemies enemyType) {
        String name = enemyAdjectives[r.nextInt(enemyAdjectives.length)] + " " + enemyType.getName();

        Log.d(TAG, String.format(G.locale, "parseName:success:name=%s", name));
        return name;
    }

    /**
     * Calculate a new Enemies health.
     * @param level level of the current enemy
     * @return New Monster health.
     */
    private static int calculateEnemyHealth(Integer level) {
        int enemyHealth = (level * Constants.ENEMY_HEALTH_MODIFIER) + r.nextInt(10);

        Log.d(TAG, String.format(G.locale, "calculateEnemyHealth:success:enemyHealth=%d", enemyHealth));
        return  enemyHealth;
    }
}
