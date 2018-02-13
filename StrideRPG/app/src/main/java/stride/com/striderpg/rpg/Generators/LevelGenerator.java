package stride.com.striderpg.rpg.Generators;

import stride.com.striderpg.rpg.Constants;

/**
 * RPG Generator class for determining information about a Player level and experience,
 * calculating the different values required to level up.
 */
public class LevelGenerator {

    private static final Integer LEVEL_CONST_1 = 9;
    private static final Integer LEVEL_CONST_2 = -37;
    private static final Integer LEVEL_CONST_3 = 111;

    /**
     * Calculate the total amount of experience required for a Player to level up.
     * @param currentLevel Players current level.
     * @return Next level total experience required.
     */
    public static Integer experienceToNextLevel(Integer currentLevel) {
        return (Integer) (int)(Math.exp((currentLevel - LEVEL_CONST_2) / LEVEL_CONST_1) - LEVEL_CONST_3);
    }

    /**
     * Retrieve a Players current level based on their current experience amount.
     * @param currentExperience Players current experience property.
     * @return Players current level.
     */
    public static Integer levelFromExperience(Integer currentExperience) {
        return (Integer) (int)Math.max(Math.floor(LEVEL_CONST_1 * Math.log(currentExperience + LEVEL_CONST_3) + LEVEL_CONST_2), 1);
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
