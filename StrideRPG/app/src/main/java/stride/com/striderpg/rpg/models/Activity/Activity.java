package stride.com.striderpg.rpg.models.Activity;


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
    private Integer activityIconId;

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Activity.class).
     */
    public Activity() { }

    /**
     * Custom constructor to build an Activity with the parameters passed to constructor.
     * @param timestamp Activity timestamp.
     * @param activityType ActivityType enum.
     * @param description Activity description.
     * @param activityIconId Drawable activity icon id.
     */
    public Activity(String timestamp, Enums.ActivityType activityType, String description,
                    Integer activityIconId) {
        this.timestamp = timestamp;
        this.activityType = activityType;
        this.description = description;
        this.activityIconId = activityIconId;
    }

    /**
     * Implementation of an Activities toString method to print out the properties of the Activity.
     * @return Properties of the Activity object.
     */
    @Override
    public String toString() {
        return "Activity{" +
                "timestamp='" + timestamp + '\'' +
                ", activityType=" + activityType +
                ", description='" + description + '\'' +
                ", activityIconId=" + activityIconId +
                '}';
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Enums.ActivityType getActivityType() {
        return activityType;
    }

    public String getDescription() {
        return description;
    }

    public Integer getActivityIconId() {
        return activityIconId;
    }
}
