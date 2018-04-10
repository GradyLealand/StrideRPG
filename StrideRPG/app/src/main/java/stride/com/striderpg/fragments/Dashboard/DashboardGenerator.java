package stride.com.striderpg.fragments.Dashboard;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.models.Activity.Activity;

/**
 * DashboardGenerator used to retrieve/generate the Activity ArrayList
 * for the Active Player.
 */
public class DashboardGenerator {

    /**
     * DashboardGenerator Logging tag.
     */
    private static final String TAG = "DashboardsGenerator";

    /**
     * Activity ArrayList to hold each Activity that will be inside of the
     * Dashboard.
     */
    private ArrayList<Activity> activities = new ArrayList<>();

    /**
     * Constructor that calls the buildDashboard() method to generate
     * the Dashboard activities information/data.
     */
    DashboardGenerator() {
        buildDashboard();
    }

    /**
     * Build out the activities ArrayList by adding each value from
     * the Active Players ActivityLog object.
     */
    private void buildDashboard() {
        // Adds each Activity to the activities ArrayList.
        activities.addAll(G.activePlayer.getActivityLog().getLog().values());

        // Sort the activities ArrayList.
        sortActivities();
    }

    /**
     * Sorts the activities ArrayList.
     */
    private void sortActivities() {
        Collections.sort(activities, new Comparator<Activity>() {
            @Override
            public int compare(Activity a1, Activity a2) {
                return a1.compareTime(a2);
            }
        });
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }
}
