package stride.com.striderpg.rpg.models.Player;


import org.joda.time.DateTime;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.models.Activity.Activity;
import stride.com.striderpg.rpg.utils.TimeParser;

/**
 * A ActivityLog class to store a Players encounters in the game using
 * a HashMap with a timestamp
 * String as the key and an Activity as the value.
 */
public class ActivityLog {

    /**
     * PropertyChangedSupport object to deal with raising events
     * when a Property on this object/bean is changed.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /**
     * HashMap to store a Players Encounters that are generated while
     * progressing through the game.
     */
    private HashMap<String, Activity> log = new HashMap<>();

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(ActivityLog.class).
     */
    public ActivityLog() { }

    /**
     * Implementation of a ActivityLog toString method to print out
     * the properties of the ActivityLog.
     */
    @Override
    public String toString() {

        return "ActivityLog{" +
                "log=" + log +
                '}';
    }

    /**
     * Attach a new PropertyChangeListener to this classes
     * PropertyChangeSupport object.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Add a new Activity to the ActivityLog log.
     */
    public void addActivity(Activity activity) {
        log.put(activity.getTimestamp(), activity);
    }

    /**
     * Add a new Activity to the ActivityLog log.
     * Also fire a property changed event for dealing with ui.
     */
    public void addOnlineActivity(Activity activity) {
        changes.firePropertyChange(Constants.PROPERTY_ONLINE_ACTIVITY, null, activity);
        log.put(activity.getTimestamp(), activity);
    }

    /**
     * Clean the Histories log HashMap by removing every key that is older
     * then the threshold defined in the global constants class.
     */
    public void cleanHistory() {
        // Create a Date for the current time - hours constant.
        DateTime twelveHoursAgo =  TimeParser.getCurrentTimeMinusHours(Constants.ACTIVITY_CLEANUP_THRESHOLD_HOURS);

        // Create an iterator and begin iterating through Players current log to search
        // for any entries older than 12 hours.
        Iterator it = log.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            DateTime keyTimestamp = TimeParser.parseTimestamp(pair.getKey().toString());
            if (keyTimestamp.isBefore(twelveHoursAgo)) {
                it.remove();
            }
        }
    }

    public HashMap<String, Activity> getLog() {
        return log;
    }
}
