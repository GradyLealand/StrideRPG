package stride.com.striderpg.rpg.models.Player;


import java.util.HashMap;

import stride.com.striderpg.rpg.models.Activity.Activity;

/**
 * A History class to store a Players encounters in the game using a HashMap with a timestamp
 * String as the key and an Activity as the value.
 */
public class History {

    /**
     * HashMap to store a Players Encounters that are generated while progressing
     * through the game.
     */
    private HashMap<String, Activity> log;

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(History.class).
     */
    public History() { }

    /**
     * Implementation of a History toString method to print out the properties of the History.
     * @return Properties of the History object.
     */
    @Override
    public String toString() {
        return "History{" +
                "log=" + log +
                '}';
    }

    /**
     * Add a new Activity to the History log.
     * @param activity Activity being added to log.
     */
    public void addActivity(Activity activity) {
        log.put(activity.getTimestamp(), activity);
    }

    /**
     * Clean the Histories log HashMap by removing every key that's date is 12 hours older than
     * the current timestamp.
     */
    public void cleanHistory() {

    }

    /**
     * Retrieve the Players log HashMap.
     * @return log HashMap.
     */
    public HashMap<String, Activity> getLog() {
        return log;
    }
}
