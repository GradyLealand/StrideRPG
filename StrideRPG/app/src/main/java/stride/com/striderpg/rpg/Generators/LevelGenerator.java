package stride.com.striderpg.rpg.Generators;


import stride.com.striderpg.rpg.models.Player.Player;

/**
 * RPG Generator class for determining information about a Players level and experience,
 * calculating the different values required to level up.
 */
public class LevelGenerator {

    /**
     * Constant Integer for calculating the experience required for a Player to level up.
     */
    private static final Integer LEVEL_CONST_1 = 4;

    /**
     * Constant Integer for calculating the experience required for a Player to level up.
     */
    private static final Integer LEVEL_CONST_2 = 5;

    /**
     * Calculate the total amount of experience required for a Player to level up.
     * @param currentLevel Players current level.
     * @return Next level total experience required.
     */
    public static Integer experienceToNextLevel(Integer currentLevel) {
        return (int)Math.round((LEVEL_CONST_1 * (Math.pow(currentLevel, 3))) / LEVEL_CONST_2) + 200;
    }

    /**
     * Determines if a Player can level up or not by comparing their experience with the
     * experience required to reach their next level.
     * @param p Player object being checked.
     * @return Boolean for if Player should be levelled up.
     */
    public static boolean canLevelUp(Player p) {
        return p.getExperience() > experienceToNextLevel(p.getLevel());
    }

    /**
     * Calculate a Players experience remaining until a level up is reached.
     * @param currentLevel Players current level.
     * @param currentExp Players current experience.
     * @return Experience remaining for user to level up.
     */
    public static Integer experienceRemaining(Integer currentLevel, Integer currentExp) {
        return experienceToNextLevel(currentLevel) - currentExp;
    }
}
