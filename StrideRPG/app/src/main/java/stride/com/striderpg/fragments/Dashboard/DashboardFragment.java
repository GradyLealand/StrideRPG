package stride.com.striderpg.fragments.Dashboard;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.generators.LevelGenerator;
import stride.com.striderpg.rpg.models.Activity.Activity;

/**
 * Dashboard Fragment for displaying a Users recent activity log and a profile bar with information
 * about their account at the top of the screen.
 */
public class DashboardFragment extends Fragment {

    /**
     * DashboardFragment Logging tag.
     */
    private static final String TAG = "DashboardFragment";


    /**
     * Instance the recycler view used to create the list of Activities
     * in the GUI dynamically.
     */
    RecyclerView dashboardRecyclerView;


    /**
     * Generator instance used to retrieve Activities. Constructor
     * call will get the current Activity HashMap from the
     * active player object.
     */
    DashboardGenerator generator = new DashboardGenerator();

    /**
     * Adapter instance global to update on Activity added.
     */
    DashboardAdapter adapter;

    /**
     * Player profile picture ImageView.
     */
    private ImageView playerProfileImage;

    /**
     * Player experience amount TextView.
     */
    private TextView playerExpCount;

    /**
     * Player experience / neededExperience ProgressBar.
     */
    private ProgressBar playerLevelProgressBar;

    /**
     * Player level amount TextView.
     */
    private TextView levelText;

    /**
     * Player steps amount TextView.
     */
    private TextView stepsText;

    /**
     * Player username value TextView.
     */
    private TextView playerUsernameText;

    /**
     * Player enemies defeated amount TextView.
     */
    private TextView enemiesText;

    /**
     * Required empty public constructor function.
     */
    public DashboardFragment() { }

    /**
     * Main DashboardFragment View creation. The RecyclerView,
     * Adapter and LayoutManager are all used to here to create the
     * layout and dynamically create the Dashboard Activity view.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dashboardRecyclerView = rootView.findViewById(R.id.rv);
        dashboardRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        dashboardRecyclerView.setLayoutManager(llm);

        adapter = new DashboardAdapter(generator.getActivities());
        dashboardRecyclerView.setAdapter(adapter);

        Log.d(TAG, "onCreateView:success");
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Call method to set each UI element on View start.
        getDashboardElements();

        // Initial value set for Player information on Dashboard.
        playerUsernameText.setText(G.activePlayer.getUsername());
        stepsText.setText(addCommasToInteger(G.activePlayer.getSteps()));
        levelText.setText(String.format(G.locale, "%d", G.activePlayer.getLevel()));
        enemiesText.setText(addCommasToInteger(G.activePlayer.getStats().getEnemiesDefeated()));

        // Initial Player level progress bar update.
        updateLevelProgressBar(G.activePlayer.getExperience());

        // Add a new PropertyChangeListener to the active Player
        // object for handling property changes.
        G.activePlayer.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                switch (propertyChangeEvent.getPropertyName()) {
                    // If Player experience property has been changed.
                    case Constants.PROPERTY_EXP:
                        updateLevelProgressBar((int)propertyChangeEvent.getNewValue());
                        break;
                    // If Player username property has been changed.
                    case Constants.PROPERTY_USERNAME:
                        break;
                    // If Player level property has been changed.
                    case Constants.PROPERTY_LEVEL:
                        updateLevelText((int)propertyChangeEvent.getNewValue());
                        updateExpOnLevelUp();
                        break;
                    // If Player steps property has been changed.
                    case Constants.PROPERTY_STEPS:
                        updateStepsTextView((int)propertyChangeEvent.getNewValue());
                        break;
                }
            }
        });

        // Add a new PropertyChangeListener to the active Players ActivityLog
        // for handling any new online activity additions.
        G.activePlayer.getActivityLog().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                switch (propertyChangeEvent.getPropertyName()) {
                    case Constants.PROPERTY_ONLINE_ACTIVITY:
                        // Grab the new Activity that has been generated.
                        Activity activityAdded = (Activity)propertyChangeEvent.getNewValue();

                        // Insert into the Dashboard Adapter and notify
                        // so it is displayed in the UI.
                        generator.insert(activityAdded);

                        adapter.notifyItemInserted(0);
                        adapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * Helper method for updating the Dashboards active Player
     * experience / experience needed TextView.
     */
    public void updateExpOnLevelUp() {
        playerExpCount.setText(parseExpAmount(
                G.activePlayer.getExperience(),
                G.activePlayer.getLevel() + 1)
        );
    }

    /**
     * Update the Dashboards steps TextView with the passed
     * parameter with commas added.
     * @param newValue New steps amount.
     */
    private void updateStepsTextView(Integer newValue) {
        stepsText.setText(String.format(G.locale, "%s", addCommasToInteger(newValue)));
    }

    /**
     * Update the Dashboards level TextView with the passed parameter.
     * @param newValue New level amount.
     */
    private void updateLevelText(Integer newValue) {
        levelText.setText(String.format(G.locale, "%d", newValue));
    }

    /**
     * Update the Dashboard playerLevelProgressBarElement with the specified amount.
     * @param amount New Player experience amount.
     */
    private void updateLevelProgressBar(Integer amount) {
        playerLevelProgressBar.setMax(
                LevelGenerator.experienceToNextLevel(G.activePlayer.getLevel())
        );
        playerLevelProgressBar.setProgress(amount);

        playerExpCount.setText(parseExpAmount(amount));
    }

    /**
     * Format a number into a String with the appropriate commas.
     * @param number Number to format.
     * @return Number with commas as a String.
     */
    private String addCommasToInteger(Integer number) {
        return NumberFormat.getIntegerInstance().format(number);
    }

    /**
     * Parse out a String representing the currentExp/expToLevel.
     * @return  Helpful String to show user exp/exp needed.
     */
    private String parseExpAmount(Integer exp) {
        return LevelGenerator.getReadableExpString(exp, G.activePlayer.getLevel());
    }

    /**
     * Parse out a String representing a specific levels required
     * experience needed to level up.
     * @param exp Experience passed used as current exp.
     * @param level Level used to determined exp needed.
     * @return String for exp/exp needed.
     */
    private String parseExpAmount(Integer exp, Integer level) {
        return exp + " / " +
                LevelGenerator.experienceToNextLevel(level);
    }

    /**
     * Set the required Dashboard UI elements using the current view
     * findViewById method.
     */
    private void getDashboardElements() {
        try {
            playerProfileImage = getView().findViewById(R.id.playerProfileImage);
            playerUsernameText = getView().findViewById(R.id.playerUsernameText);
            playerExpCount = getView().findViewById(R.id.playerExpCount);
            playerLevelProgressBar = getView().findViewById(R.id.playerLevelProgressBar);
            levelText = getView().findViewById(R.id.levelText);
            stepsText = getView().findViewById(R.id.stepsText);
            enemiesText = getView().findViewById(R.id.enemiesText);
        } catch (Exception e) {
            Log.e(TAG, "getDashboardElements:error:", e);
        }
    }
}