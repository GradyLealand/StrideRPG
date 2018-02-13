package stride.com.striderpg.database;

/**
 * Final class to hold all FirebaseDatabase node tokens/keys.
 */
public final class DBKeys {

    /**
     * Token for retrieving or reaching the base users node in FirebaseDatabase.
     */
    public static final String USERS_KEY = "users";

    /**
     * Token for retrieving or reaching a users username information node in FirebaseDatabase.
     */
    public static final String USERNAME_KEY = "username";

    /**
     * Token for retrieving or reaching a users steps node in FirebaseDatabase.
     */
    public static final String STEPS_KEY = "steps";

    /**
     * Token for retrieving or reaching a users experience node in FirebaseDatabase.
     */
    public static final String EXPERIENCE_KEY = "experience";

    /**
     * Token for retrieving or reaching a users level node in FirebaseDatabase.
     */
    public static final String LEVEL_KEY = "level";

    /**
     * Token for retrieving or reaching a users skills information node in FirebaseDatabase.
     */
    public static final String SKILLS_KEY = "skills";
    /**
     * Token for the skill property vitality.
     */
    public static final String SKILLS_VITALITY = "vitality";
    /**
     * Token for the skill property strength.
     */
    public static final String SKILLS_STRENGTH = "strength";
    /**
     * Token for the skill property speed.
     */
    public static final String SKILLS_SPEED = "speed";

    /**
     * Token for retrieving or reaching a users inventory information node in FirebaseDatabase.
     */
    public static final String INVENTORY_KEY = "inventory";
    /**
     * Token for retrieving or reaching a users inventory items information node in FirebaseDatabase.
     */
    public static final String INVENTORY_ITEMS_KEY = "items";
}
