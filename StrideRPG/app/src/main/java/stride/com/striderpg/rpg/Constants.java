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
    public static final int ENEMY_HEALTH_MODIFIER = 3;

    /**
     * Constant to calculate an enemies min and max damage.
     */
    public static final int ENEMY_DAMAGE_MODIFIER = 5;

    /**
     * Constant to calculate an enemies experience property.
     */
    public static final int ENEMY_EXPERIENCE_MODIFIER = 10;

    /**
     * Constant for generating SimpleDateFormats throughout the
     * application.
     */
    public static final String ACTIVITY_TIMESTAMP_FORMAT = "MM-dd-yyyy HH:mm:ss";

    /**
     * Constant for parsing Timestamps going back in time in hours
     * to check if an Activity is old enough to be removed from the
     * Players log.
     */
    public static final Integer ACTIVITY_CLEANUP_THRESHOLD_HOURS = 12;

    /**
     * Constant to calculate experience player gains from steps by
     * dividing the current total by this constant, we can only allow
     * experience from steps when a Player
     * takes more steps in a shorter period of time.
     */
    public static final int PLAYER_STEPS_EXP_MODIFIER = 10;

    /**
     * Constant to define the time in minutes between each possible
     * offline event.
     */
    public static final int OFFLINE_EVENT_INCREMENT_MINUTES = 30;

    /**
     * Constant to determine the percent chance that an activity will
     * be generated while iterating through the possible activities
     * property in the OfflineGenerator.
     */
    public static final int OFFLINE_ACTIVITY_CHANCE_PERCENT = 50;

    /**
     * Constant that will be user to generate a random Integer to be
     * added to a monsters health and players attack in an offline
     * battle event.
     */
    public static final int OFFLINE_BATTLE_MODIFIER = 10;

    /**
     * Constant used to determine the amount of milliseconds
     * (long seconds) in between every active player database push.
     */
    public static final long DATABASE_PUSH_RATE = 30000;

    /**
     * Constant used to determine the amount of milliseconds
     * (long seconds) in between every fitnessUtil readData call.
     */
    public static final long FITNESS_READ_RATE = 5000;

    /**
     * Constant for generic defeat enemies quest title.
     */
    public static final String QUEST_DEFEAT_ENEMIES_TITLE = "Defeat Enemies";

    /**
     * Constant for generic defeat enemies quest description.
     */
    public static final String QUEST_DEFEAT_ENEMIES_DESCRIPTION = "Defeat enemies throughout your travels.";

    /**
     * Constant for generic loot items quest title.
     */
    public static final String QUEST_LOOT_ITEMS_TITLE = "Loot Items";

    /**
     * Constant for generic loot items quest description.
     */
    public static final String QUEST_LOOT_ITEMS_DESCRIPTION = "Loot items throughout your travels.";

    /**
     * Constant for generic take steps quest title.
     */
    public static final String QUEST_TAKE_STEPS_TITLE = "Take Steps";

    /**
     * Constant for generic take steps quest description.
     */
    public static final String QUEST_TAKE_STEPS_DESCRIPTION = "Take steps throughout your travels.";


    // TODO: These goals should be dependant on the Quest itself, some goals need higher thresholds, etc.

    /**
     * Quest Level One progress goal.
     */
    public static final Integer QUEST_LEVEL_ONE_GOAL = 25;

    /**
     * Quest Level Two progress goal.
     */
    public static final Integer QUEST_LEVEL_TWO_GOAL = 50;

    /**
     * Quest Level Three progress goal.
     */
    public static final Integer QUEST_LEVEL_THREE_GOAL = 100;

    /**
     * Quest Level Four progress goal.
     */
    public static final Integer QUEST_LEVEL_FOUR_GOAL = 200;

    /**
     * Quest Level Five progress goal.
     */
    public static final Integer QUEST_LEVEL_FIVE_GOAL = 500;

    /**
     * Quest Level Six progress goal.
     */
    public static final Integer QUEST_LEVEL_SIX_GOAL = 1000;

    /**
     * Quest Level Seven progress goal.
     */
    public static final Integer QUEST_LEVEL_SEVEN_GOAL = 2000;

    /**
     * Quest Level Eight progress goal.
     */
    public static final Integer QUEST_LEVEL_EIGHT_GOAL = 5000;

    /**
     * Quest Level Nine progress goal.
     */
    public static final Integer QUEST_LEVEL_NINE_GOAL = 10000;

    /**
     * Quest Level Ten progress goal.
     */
    public static final Integer QUEST_LEVEL_TEN_GOAL = 50000;

}
