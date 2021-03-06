package stride.com.striderpg.fragments.Dashboard;


import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
 * DashboardAdapter used to instantiate new ViewHolders that contain
 * info about different activities currently present in Active Players
 * ActivityLog.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ActivityViewHolder> {

    /**
     * DashboardAdapter Logging tag.
     */
    private static final String TAG = "DashboardAdapter";

    /**
     * Activity ArrayList used when inflating each Activity as a new
     * ActivityViewHolder.
     */
    private ArrayList<Activity> activities;

    /**
     * Constructor that sets the activities ArrayList.
     */
    DashboardAdapter(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    /**
     * Method called on each ActivityViewHolder instantiated.
     */
    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_dashboard_event, viewGroup, false);
        return new ActivityViewHolder(v);
    }

    /**
     * Method called when the ActivityViewHolder is being bound to its
     * proper Activity located in the activities ArrayList.
     */
    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder activityViewHolder, int i) {
        // Set all required UI elements for this current Activity ViewHolder.
        activityViewHolder.activityName.setText(activities.get(i).getActivityType().getName());
        activityViewHolder.activityDesc.setText(activities.get(i).getDescription());
        activityViewHolder.activityIcon.setImageResource(activities.get(i).getActivityIconId());
        activityViewHolder.activityTimestamp.setText(TimeParser.toReadable(activities.get(i).getTimestamp()));
    }

    /**
     * Add a new Activity to the activities ArrayList and notify the
     * adapter that an item has been inserted.
     */
    public void add(Activity activity) {
        activities.add(0, activity);
        notifyItemInserted(0);
    }

    /**
     * Get count of activities ArrayList.
     */
    @Override
    public int getItemCount() {
        return activities.size();
    }

    /**
     * Static ActivityViewHolder class extending the ViewHolder class
     * and used to bind and inflate new ActivityViewHolders with
     * required properties and elements.
     */
    static class ActivityViewHolder extends RecyclerView.ViewHolder {

        /**
         * CardView container that holds all ActivityView data.
         */
        CardView cv;

        /**
         * TextView to contain the Activity name.
         */
        TextView activityName;

        /**
         * TextView to contain the Activity description.
         */
        TextView activityDesc;

        /**
         * TextView to contain the Activity timestamp.
         */
        TextView activityTimestamp;

        /**
         * ImageView to hold the Activity image resource.
         */
        ImageView activityIcon;

        /**
         * ActivityViewHolder constructor to set class properties to
         * elements contained in the itemView passed.
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
