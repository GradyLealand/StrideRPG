package stride.com.striderpg.rpg;


/**
 * RPG Helper class to store and give the rpg package access to the different constants for
 * calculating game related values.
 */
public final class Constants {

    /**
     * Constant to hold the base string that is used when generating an Item.
     */
    public static final String ITEM_MAKE_BASE = "abcde12345";

    /**
     * Constant to hold a Players default vitality.
     */
    public static final Integer PLAYER_DEFAULT_VITALITY = 5;

    /**
     * Constant to hold a Players default strength.
     */
    public static final Integer PLAYER_DEFAULT_STRENGTH = 5;

    /**
     * Constant to hold a Players default speed.
     */
    public static final Integer PLAYER_DEFAULT_SPEED = 5;

    /**
     * Constant to calculate an Items power level.
     */
    public static final int ITEM_POWER_LEVEL_MODIFIER = 5;

    /**
     * Constant to calculate an Enemies health property.
     */
    public static final int ENEMY_HEALTH_MODIFIER = 30;

    /**
     * Constant to calculate an enemies min and max damage.
     */
    public static final int ENEMY_DAMAGE_MODIFIER = 5;

    /**
     * Constant to calculate an enemies experience property.
     */
    public static final int ENEMY_EXPERIENCE_MODIFIER = 10;
}
