package stride.com.striderpg.rpg;


/**
 * RPG Helper class to store and give the rpg package access to the different constants for
 * calculating game related values.
 */
public final class Constants {

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

    /**
     * Constant for generating SimpleDateFormats throughout the application.
     */
    public static final String ACTIVITY_TIMESTAMP_FORMAT = "yyyy-MM-dd hh:mm:ss";

    /**
    * Constant to calculate experience player gains from steps by dividing the current
    * total by this constant, we can only allow experience from steps when a Player
    * takes more steps in a shorter period of time.
    */
    public static final int PLAYER_STEPS_EXP_MODIFIER = 10;

    /**
     * Constant to define the time in minutes between each possible offline event.
     * If a user is offline for 90 minutes before logging in,
     * then 90 / OFFLINE_EVENT_INCREMENT_MINUTES = 2 possible offline activities.
     */
    public static final int OFFLINE_EVENT_INCREMENT_MINUTES = 30;

    /**
     * Constant to determine the percent chance that an activity will be generated while
     * iterating through the possible activities property in the OfflineGenerator.
     */
    public static final int OFFLINE_ACTIVITY_CHANCE_PERCENT = 50;

    /**
     * Constant used to determine the amount of milliseconds (long seconds) in between every
     * active player database push.
     */
    public static final long DATABASE_PUSH_RATE = 30000;

    /**
     * Constant used to determine the amount of milliseconds (long seconds) in between every
     * fitnessUtil readData call.
     */
    public static final long FITNESS_READ_RATE = 5000;

}
