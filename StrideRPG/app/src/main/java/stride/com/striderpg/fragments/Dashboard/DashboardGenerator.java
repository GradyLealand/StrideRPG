package stride.com.striderpg.fragments.Dashboard;


import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.models.Activity.Activity;

/**
 * Dashboard Generator for creating the ArrayList of a Players
 * Activities that is displayed inside of the Dashboards Fragment
 * using the RecyclerView / CardView
 */
public class DashboardGenerator {

    /**
     * DashboardGenerator Logging tag.
     */
    private static final String TAG = "DashboardsGenerator";

    /**
     * ArrayList of type Activity to store all the Activities
     * from Global activePlayer.
     */
    private ArrayList<Activity> activities = new ArrayList<>();

    /**
     * Simple DashboardGenerator Constructor to call build
     * and sort the current ActivityLog.
     */
    DashboardGenerator() {
        updateDashboard();
    }

    /**
     * Update the Dashboard by adding all of the current Global Players
     * ActivityLog HashMap and sorting it.
     */
    private void updateDashboard() {
        activities.addAll(G.activePlayer.getActivityLog().getLog().values());
        try {
            sort(activities);
        } catch (Exception e) {
            Log.e(TAG, "sort:error:", e);
        }
    }

    /**
     * Sort method for placing older activities at the back of
     * the ArrayList of Activities.
     * @param collection Collection being sorted.
     */
    public void sort(ArrayList<Activity> collection) {
        Collections.sort(collection, new Comparator<Activity>() {
            @Override
            public int compare(Activity a1, Activity a2) {
                return a1.compareTime(a2);
            }
        });
    }

    /**
     * Retrieve the Activities ArrayList.
     * @return Activities property.
     */
    public ArrayList<Activity> getActivities() {
        return activities;
    }
}
