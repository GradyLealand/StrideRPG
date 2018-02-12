package stride.com.striderpg.rpg.Generators;


import java.util.Random;

import stride.com.striderpg.R;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Enemy.Enemy;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * RPG Generator class to generate in game enemies with properties related to the Global
 * Player activePlayer Object that each game session will have.
 */
public class EnemyGenerator {

    /**
     * Random instance available to the EnemyGenerator class for creating new enemies.
     */
    private Random r = new Random();

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

    private int calculateEnemyExp(Player p) {
        return p.getLevel() * Constants.ENEMY_EXPERIENCE_MODIFIER + r.nextInt(50);
    }

    private int parseIcon(Enums.EnemyType enemyType) {
        int iconId = 0;
        try {
            iconId = R.drawable.class.getField(enemyType.getName().toLowerCase()).getInt(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return iconId;
    }

    private String parseName(Enums.EnemyType enemyType) {
        return enemyAdjectives[r.nextInt(enemyAdjectives.length)] + " " + enemyType.getName();
    }

    private int calculateEnemyHealth(Player p) {
        return (p.getLevel() * Constants.ENEMY_HEALTH_MODIFIER) + r.nextInt(10);
    }

    private int calculateEnemyMinDamage(Player p) {
        return (p.getLevel() * Constants.ENEMY_DAMAGE_MODIFIER) + r.nextInt(10) / 2;
    }

    private int calculateEnemyMaxDamage(Player p) {
        return (p.getLevel() * Constants.ENEMY_DAMAGE_MODIFIER) + r.nextInt(10) * 2;
    }
}
