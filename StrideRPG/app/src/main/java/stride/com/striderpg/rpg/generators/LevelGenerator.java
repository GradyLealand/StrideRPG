package stride.com.striderpg.rpg.generators;


/**
 * RPG Generator class for determining information about a Players
 * level and experience, calculating the different values required to
 * level up.
 */
public class LevelGenerator {

    /**
     * LevelGenerator Logging tag.
     */
    private static final String TAG = "LevelGenerator";

    /**
     * Constant Integer for calculating the experience required
     * for a Player to level up.
     */
    private static final Integer LEVEL_CONST_1 = 4;

    /**
     * Constant Integer for calculating the experience required
     * for a Player to level up.
     */
    private static final Integer LEVEL_CONST_2 = 5;

    /**
     * Calculate the total amount of experience required for a
     * Player to level up.
     * @param level Players current level.
     * @return Next level total experience required.
     */
    public static Integer experienceToNextLevel(Integer level) {
        return (int)Math.round((LEVEL_CONST_1 * (Math.pow(level, 3))) / LEVEL_CONST_2) + 200;
    }

    /**
     * Calculate a Players experience remaining until a level up is
     * reached.
     * @param currentLevel Players current level.
     * @param currentExp Players current experience.
     * @return Experience remaining for user to level up.
     */
    public static Integer experienceRemaining(Integer currentLevel, Integer currentExp) {
        return experienceToNextLevel(currentLevel) - currentExp;
    }

    /**
     * Return a readable string of Players Experience/ExpNeeded.
     * @param currentExp Current Player experience.
     * @param level Current Player level.
     * @return Readable exp/expneeded String.
     */
    public static String getReadableExpString(Integer currentExp, Integer level) {
        return currentExp + " / " + experienceToNextLevel(level);
    }
}
