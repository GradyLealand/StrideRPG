package stride.com.striderpg.fragments.Dashboard;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import stride.com.striderpg.R;
import stride.com.striderpg.rpg.models.Activity.Activity;
import stride.com.striderpg.rpg.utils.TimeParser;

/**
 * DashboardAdapter that extends the Recycler View Adapter
 * of sub type ActivityViewHolder.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ActivityViewHolder> {

    /**
     * DashboardAdapter Logging tag.
     */
    private static final String TAG = "DashboardAdapter";

    /**
     * ArrayList of type Activity.
     */
    private ArrayList<Activity> activities;

    /**
     * Constructor method for the DashboardAdapter, sets the
     * activities ArrayList to the specified ArrayList.
     * @param activities ArrayList of type Activity.
     */
    DashboardAdapter(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_dashboard_event, viewGroup, false);
        return new ActivityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder activityViewHolder, int i) {
        // Set all required UI elements for this current Activity
        // ViewHolder.
        activityViewHolder.activityName.setText(
                activities.get(i).getActivityType().getName()
        );

        activityViewHolder.activityDesc.setText(
                activities.get(i).getDescription()
        );

        activityViewHolder.activityIcon.setImageResource(
                activities.get(i).getActivityIconId()
        );

        activityViewHolder.activityTimestamp.setText(
                TimeParser.toReadable(activities.get(i).getTimestamp())
        );
    }

    /**
     * Static sub-class for holding the ActivityViewHolder
     * that contains all relevant information about the Activity
     * object being inflated and displayed.
     */
    static class ActivityViewHolder extends RecyclerView.ViewHolder {

        /**
         * Main CardView to hold Activity information.
         */
        CardView cv;

        /**
         * Activity name TextView.
         */
        TextView activityName;

        /**
         * Activity description TextView.
         */
        TextView activityDesc;

        /**
         * Activity timestamp TextView.
         */
        TextView activityTimestamp;

        /**
         * Activity icon ImageView.
         */
        ImageView activityIcon;


        /**
         * ActivityViewHolder constructor method set the ids
         * of the views inside of the holder.
         * @param itemView This Activities itemView.
         */
        ActivityViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            activityName = itemView.findViewById(R.id.activity_name);
            activityDesc = itemView.findViewById(R.id.activity_desc);
            activityTimestamp = itemView.findViewById(R.id.activity_timestamp);
            activityIcon = itemView.findViewById(R.id.activity_image);
        }
    }
}
