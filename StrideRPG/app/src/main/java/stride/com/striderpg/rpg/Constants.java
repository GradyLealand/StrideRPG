package stride.com.striderpg.rpg;


/**
 * Constants Class to store any values that may be used within the application
 * that will not change.
 *
 * Any modifiers, default values or multipliers are put here and used mostly inside
 * of the RPG package.
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
     * Constant to hold the amount of skill points a Player earns on
     * level up.
     */
    public static final Integer SKILL_POINTS_ON_LEVEL = 5;

    /**
     * Constant to calculate an Items power level.
     */
    public static final int ITEM_POWER_LEVEL_MODIFIER = 5;

    /**
     * Constant to calculate an Enemies health property.
     */
    public static final int ENEMY_HEALTH_MODIFIER = 3;

    /**
     * Constant to calculate an enemies experience property.
     */
    public static final int ENEMY_EXPERIENCE_MODIFIER = 10;

    /**
     * Constant to calculate a bosses experience.
     */
    public static final int BOSS_EXPERIENCE_MODIFIER = 30;

    /**
     * Constant to calculate a bosses health property.
     */
    public static final int BOSS_ENCOUNTER_HEALTH_MODIFIER = 500;

    /**
     * Constant to calculate the change in boss health property.
     */
    public static final int BOSS_ENCOUNTER_HEALTH_CHANGE = 10;

    /**
     * Constant to define the minimum boss health;
     */
    public static final int BOSS_ENCOUNTER_HEALTH_MINIMUM = 100;

    /**
     * Constant to define the minimum amount of time a player can have to defeat a monster
     */
    public static final int BOSS_ENCOUNTER_TIME_MINIMUM = 10;

    /**
     * Constant to define the minimum level a player must reach before being able to encounter
     * a boss of tier one.
     */
    public static final int BOSS_ENCOUNTER_TIER_ONE_MINIMUM_LEVEL = 1;

    /**
     * Constant to define the minimum level a player must reach before being able to encounter
     * a boss of tier two.
     */
    public static final int BOSS_ENCOUNTER_TIER_TWO_MINIMUM_LEVEL = 8;

    /**
     * Constant to define the minimum level a player must reach before being able to encounter
     * a boss of tier three.
     */
    public static final int BOSS_ENCOUNTER_TIER_THREE_MINIMUM_LEVEL = 25;

    /**
     * Constant to define the minimum strength a Boss would have if it's Tier 1 Boss.
     */
    public static final int BOSS_ENCOUNTER_TIER_ONE_STRENGTH_FLOOR = 17;

    /**
     * Constant to define the minimum strength a Boss would have if it's Tier 2 Boss.
     */
    public static final int BOSS_ENCOUNTER_TIER_TWO_STRENGTH_FLOOR = 51;

    /**
     * Constant to define the minimum strength a Boss would have if it's Tier 3 Boss.
     */
    public static final int BOSS_ENCOUNTER_TIER_THREE_STRENGTH_FLOOR = 105;

    /**
     * Constant to define the minimum vitality a Boss would have if it's Tier 1 Boss.
     */
    public static final int BOSS_ENCOUNTER_TIER_ONE_VITALITY_FLOOR = 17;

    /**
     * Constant to define the minimum vitality a Boss would have if it's Tier 2 Boss.
     */
    public static final int BOSS_ENCOUNTER_TIER_TWO_VITALITY_FLOOR = 51;

    /**
     * Constant to define the minimum vitality a Boss would have if it's Tier 3 Boss.
     */
    public static final int BOSS_ENCOUNTER_TIER_THREE_VITALITY_FLOOR = 105;

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
    public static final int OFFLINE_EVENT_INCREMENT_MINUTES = 60;

    /**
     * Constant to determine the percent chance that an activity will
     * be generated while iterating through the possible activities
     * property in the OfflineGenerator.
     */
    public static final int OFFLINE_ACTIVITY_CHANCE_PERCENT = 60;

    /**
     * Constant for determining the percent chance that an ActiveEncounter
     * will be generated.
     */
    public static final int ONLINE_ACTIVE_ENCOUNTER_CHANCE_PERCENT = 75;

    /**
     * Constant for defining how many steps a player must reach
     * in game before an online Activity is generated for them.
     */
    public static final int ONLINE_ACTIVITY_STEP_THRESHOLD = 100;

    /**
     * Constant that will be used to generate a random Integer
     * added to a monsters health and players attack in an offline
     * battle encounter/activity.
     */
    public static final int OFFLINE_BATTLE_MODIFIER = 10;

    /**
     * Constant that defines the minimum level a Player or Monster can be.
     */
    public static final int MINIMUM_LEVEL = 1;

    /**
     * Constant that defines the minimum level a Boss can be.
     */
    public static final int BOSS_MINIMUM_LEVEL = 3;

    /**
     * Constant that defines the maximum amount of points a Player
     * can gain in their Vitality or Strength on level up.
     */
    public static final int LEVEL_UP_VIT_STR_MAX_AMOUNT = 3;

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the Steps property.
     */
    public static final String PROPERTY_STEPS = "STEPS";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the Level property.
     */
    public static final String PROPERTY_LEVEL = "LEVEL";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the Experience property.
     */
    public static final String PROPERTY_EXP = "EXPERIENCE";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the Username property.
     */
    public static final String PROPERTY_USERNAME = "USERNAME";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the Strength property.
     */
    public static final String PROPERTY_STRENGTH = "STRENGTH";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the Vitality property.
     */
    public static final String PROPERTY_VITALITY = "VITALITY";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the Speed property.
     */
    public static final String PROPERTY_SPEED = "SPEED";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the ActivityLog log property.
     */
    public static final String PROPERTY_ONLINE_ACTIVITY = "ONLINE_ACTIVITY";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the ActiveEncounter boss property.
     */
    public static final String PROPERTY_ACTIVE_ENCOUNTER_SET = "ACTIVE_ENCOUNTER_SET";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the ActiveEncounter boss health property.
     */
    public static final String PROPERTY_ACTIVE_ENCOUNTER_UPDATE_HEALTH = "ACTIVE_ENCOUNTER_UPDATE_HEALTH";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the ActiveEncounter on defeated.
     */
    public static final String PROPERTY_ACTIVE_ENCOUNTER_FINISH = "ACTIVE_ENCOUNTER_FINISH";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the ActiveEncounter on expiration.
     */
    public static final String PROPERTY_ACTIVE_ENCOUNTER_EXPIRES = "ACTIVE_ENCOUNTER_EXPIRES";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for new loot being equipped when an ActiveEncounter/Boss is defeated
     * and a new piece of Loot is better than what's currently equipped.
     */
    public static final String PROPERTY_ACTIVE_ENCOUNTER_NEW_LOOT = "ACTIVE_ENCOUNTER_NEW_LOOT";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the Vitality property on a Players Skills object.
     */
    public static final String PROPERTY_SKILL_VITALITY = "VITALITY_LEVELED";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the Strength property on a Players Skills object.
     */
    public static final String PROPERTY_SKILL_STRENGTH = "STRENGTH_LEVELED";

    /**
     * Constant for accessing and firing PropertyChangeEvents
     * for the Speed property on a Players Skills object.
     */
    public static final String PROPERTY_SKILL_SPEED = "SPEED_LEVELED";

    /**
     * Constant for firing and accessing PropertyChangeEvents
     * for the skill points property on a Players Skills object.
     */
    public static final String PROPERTY_SKILL_POINTS = "POINTS_EARNED";

    /**
     * Constant for firing and accessing a PropertyChangeEvents
     * for the skill points property on a Players Bestiary object.
     */
    public static final String PROPERTY_BESTIARY_UPDATED = "BESTIARY_UPDATED";

    /**
     * Constant used to determine the amount of milliseconds
     * (long seconds) in between every active player database push.
     */
    public static final long DATABASE_PUSH_RATE = 30000;

    /**
     * Constant used to determine the amount of milliseconds
     * (long seconds) in between every fitnessUtil readData call.
     */
    public static final long FITNESS_READ_RATE = 2500;

    /**
     * Constant for generic defeat enemies quest title.
     */
    public static final String QUEST_DEFEAT_ENEMIES_TITLE = "Defeat Enemies";

    /**
     * Constant for generic defeat enemies quest description.
     */
    public static final String QUEST_DEFEAT_ENEMIES_DESCRIPTION = "Defeat enemies.";

    /**
     * Constant for generic defeat bosses quest title.
     */
    public static final String QUEST_DEFEAT_BOSSES_TITLE = "Defeat Bosses";

    /**
     * Constant for generic defeat bosses quest description.
     */
    public static final String QUEST_DEFEAT_BOSSES_DESCRIPTION = "Defeat bosses.";

    /**
     * Constant for generic failed to defeat enemies quest title.
     */
    public static final String QUEST_FAIL_DEFEAT_ENEMIES_TITLE = "Failed To Defeat Enemies";

    /**
     * Constant for failed to defeat bosses in time quest title.
     */
    public static final String QUEST_FAIL_DEFEAT_BOSSES_TITLE = "Failed to Defeat Bosses in time.";

    /**
     * Constant for failed to defeat bosses in time description.
     */
    public static final String QUEST_FAIL_DEFEAT_BOSSES_DESCRIPTION = "Failed to defeat bosses.";

    /**
     * Constant for generic loot items quest title.
     */
    public static final String QUEST_LOOT_ITEMS_TITLE = "Loot Items";

    /**
     * Constant for generic loot items quest description.
     */
    public static final String QUEST_LOOT_ITEMS_DESCRIPTION = "Loot items.";

    /**
     * Constant for generic defeat enemies quest description.
     */
    public static final String QUEST_FAIL_DEFEAT_ENEMIES_DESCRIPTION = "Failed to defeat enemies.";

    /**
     * Constant for generic take steps quest title.
     */
    public static final String QUEST_TAKE_STEPS_TITLE = "Take Steps";

    /**
     * Constant for generic take steps quest description.
     */
    public static final String QUEST_TAKE_STEPS_DESCRIPTION = "Take steps.";

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