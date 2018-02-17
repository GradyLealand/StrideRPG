package stride.com.striderpg.rpg.models.Encounter;


import android.graphics.drawable.Drawable;

import stride.com.striderpg.rpg.Enums;

public class Activity {

    /**
     * Activity timestamp to track when this activity took place. Will also be used as the
     * History object's HashMap as an identifier.
     */
    private String timestamp;

    /**
     * ActivityType enum to determine what kind of event took place.
     */
    private Enums.ActivityType activityType;

    /**
     * Activity description with the information about this Activity.
     */
    private String description;

    /**
     * Drawable icon to display this Activities specific icon.
     */
    private Drawable activityIcon;

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Activity.class).
     */
    public Activity() { }

    
}
