package stride.com.striderpg.fragments.Dashboard;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.generators.LevelGenerator;
import stride.com.striderpg.rpg.models.Activity.Activity;

/**
 * Dashboard Fragment that acts as a main hub for a Player on
 * application startup. Used to Display the Active Player's basic
 * stats, such as Username, Level, Steps, Exp...
 *
 * An Adapter is located below the Players information that contains
 * all of their Activities on startup, as well as dynamically
 * populated as new activities are generated as the application is
 * running.
 */
public class DashboardFragment extends Fragment {

    /**
     * DashboardFragment Logging tag.
     */
    private static final String TAG = "DashboardFragment";

    /**
     * RecyclerView that will hold each individual Activity CardView.
     */
    RecyclerView dashboardRecyclerView;

    /**
     * DashboardGenerator used to grab the Active Players current
     * activities when the View is initially created.
     */
    DashboardGenerator activityGenerator = new DashboardGenerator();

    /**
     * DashboardAdapter for inflating and instantiating each CardView
     * with Active Players current activities log.
     */
    DashboardAdapter activityAdapter;

    /**
     * Player profile picture ImageView.
     */
    private ImageView playerProfileImage;

    /**
     * Player experience amount TextView.
     */
    private TextView playerExpCount;

    /**
     * Player (experience/neededExperience) ProgressBar.
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
     * Player ActiveEncounter ImageView.
     */
    private ImageView activeEncounterImage;

    /**
     * Player ActiveEncounter Boss name TextView.
     */
    private TextView activeEncounterName;

    /**
     * Player ActiveEncounter Boss health TextView.
     */
    private TextView activeEncounterHealthText;

    /**
     * Player ActiveEncounter Boss health ProgressBar.
     */
    private ProgressBar activeEncounterHealthProgress;

    /**
     * Player ActiveEncounter CardView Container.
     */
    private CardView activeEncounterCard;

    /**
     * Empty Public Constructor.
     */
    public DashboardFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Set the RecyclerView to the inflated rootView rv (RecyclerView).
        dashboardRecyclerView = rootView.findViewById(R.id.rv);
        dashboardRecyclerView.setHasFixedSize(true);
        dashboardRecyclerView.setItemAnimator(new SlideInLeftAnimator());

        // Set the LayoutManager used in the Dashboard.
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        dashboardRecyclerView.setLayoutManager(llm);

        // Create the Adapter used to inflate each Activity CardView.
        activityAdapter = new DashboardAdapter(activityGenerator.getActivities());
        dashboardRecyclerView.setAdapter(activityAdapter);

        // Build out the PropertyChangeListeners for the Objects changed/modified through Dashboard.
        buildPropertyChangeListeners();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Set each element present on the Dashboard.
        getDashboardElements();

        // Initial Player Stats CardView setup.
        buildPlayerStatsCard();

        // OnStart if for determining if the ActiveEncounter card should be
        // built and displayed when the Dashboard is loaded.
        if (G.activePlayer.getActiveEncounter().isActive())
            if (!G.activePlayer.getActiveEncounter().checkExpired())
                buildActiveEncounterCard();
            else
                G.activePlayer.getActiveEncounter().expire();

        // Initial Level ProgressBar update.
        updateLevelProgressBar(G.activePlayer.getExperience());
    }

    /**
     * Update the ActiveEncounter's Health ProgressBar and TextView.
     */
    private void updateEncounterHealth() {

        // Update Boss Health ProgressBar with Bosses current health property.
        activeEncounterHealthProgress.setProgress(
                G.activePlayer.getActiveEncounter().getBoss().getHealth()
        );

        // Set Health remaining Text to "health / maxHealth".
        activeEncounterHealthText.setText(String.format(G.locale, "%d / %d",
                G.activePlayer.getActiveEncounter().getBoss().getHealth(),
                G.activePlayer.getActiveEncounter().getBoss().getMaxHealth()
        ));
    }

    /**
     * Build the current ActiveEncounter CardView with the information
     * about the ActiveEncounter Boss.
     */
    private void buildActiveEncounterCard() {

        // Make the Encounter Card Visible.
        activeEncounterCard.setVisibility(View.VISIBLE);

        // Set the ActiveEncounter name TextView and health ProgressBar.
        activeEncounterName.setText(G.activePlayer.getActiveEncounter().getBoss().getName());
        activeEncounterHealthProgress.setMax(G.activePlayer.getActiveEncounter().getBoss().getMaxHealth());
        updateEncounterHealth();
    }

    /**
     * Finish the ActiveEncounter with the specified Activity.
     * Either from defeating the Boss, or the Boss expiring.
     * @param activity Activity to append to Dashboard Activities.
     */
    private void finishActiveEncounter(Activity activity) {
        activeEncounterCard.setVisibility(View.GONE);
        activityAdapter.add(activity);
    }

    /**
     * Insert a new generic Activity directly into the ActivityGenerator
     * ArrayList and notify the Adapter.
     * @param activity Activity to append to the Dashboard Activities.
     */
    private void addDashboardActivity(Activity activity) {
        activityAdapter.add(activity);

        // TODO: Remove? Should dashboard scroll to top when a new activity is appended?
        dashboardRecyclerView.getLayoutManager().scrollToPosition(0);
    }

    /**
     * Helper method for updating the Dashboards active Player
     * experience / experience needed TextView.
     */
    private void buildPlayerStatsCard() {
        playerUsernameText.setText(G.activePlayer.getUsername());
        stepsText.setText(addCommasToInteger(G.activePlayer.getSteps()));
        levelText.setText(String.format(G.locale, "%d", G.activePlayer.getLevel()));
        enemiesText.setText(addCommasToInteger(G.activePlayer.getStats().getEnemiesDefeated()));
    }

    /**
     * Update a Players Experience TextViews with active Players current properties.
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
        return LevelGenerator.getReadableExpString(exp, level);
    }

    /**
     * Set the required Dashboard UI elements using the current View
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

            activeEncounterName = getView().findViewById(R.id.activeEncounterName);
            activeEncounterImage = getView().findViewById(R.id.activeEncounterImage);
            activeEncounterHealthText = getView().findViewById(R.id.activeEncounterHealthTextView);
            activeEncounterHealthProgress = getView().findViewById(R.id.activeEncounterHealthProgressBar);
            activeEncounterCard = getView().findViewById(R.id.activeEncounterCard);
        } catch (Exception e) {
            Log.e(TAG, "getDashboardElements:error:", e);
        }
    }

    /**
     * Build out the PropertyChangeListener implementations for each required
     * DashboardFragment UI Element.
     */
    private void buildPropertyChangeListeners() {

        // Add a new PropertyChangeListener implementation that controls
        // the ui elements being changed when their internal value changes.
        G.activePlayer.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                switch (propertyChangeEvent.getPropertyName()) {
                    case Constants.PROPERTY_EXP:
                        updateLevelProgressBar((int)propertyChangeEvent.getNewValue());
                        break;

                    case Constants.PROPERTY_USERNAME:
                        break;

                    case Constants.PROPERTY_LEVEL:
                        updateLevelText((int)propertyChangeEvent.getNewValue());
                        updateExpOnLevelUp();
                        break;

                    case Constants.PROPERTY_STEPS:
                        updateStepsTextView((int)propertyChangeEvent.getNewValue());
                        break;
                }
            }
        });

        // Add a new PropertyChangeListener implementation that controls
        // the activity log elements being changed when their internal
        // value changes.
        G.activePlayer.getActivityLog().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                switch (propertyChangeEvent.getPropertyName()) {
                    case Constants.PROPERTY_ONLINE_ACTIVITY:
                        addDashboardActivity((Activity)propertyChangeEvent.getNewValue());
                }
            }
        });

        // Add a new PropertyChangeListener implementation that controls
        // the active encounter elements being changed when their
        // internal value changes.
        G.activePlayer.getActiveEncounter().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                switch(propertyChangeEvent.getPropertyName()) {
                    case Constants.PROPERTY_ACTIVE_ENCOUNTER_SET:
                        buildActiveEncounterCard();
                        break;

                    case Constants.PROPERTY_ACTIVE_ENCOUNTER_UPDATE_HEALTH:
                        updateEncounterHealth();
                        break;

                    case Constants.PROPERTY_ACTIVE_ENCOUNTER_EXPIRES:
                        finishActiveEncounter((Activity)propertyChangeEvent.getNewValue());
                        break;

                    case Constants.PROPERTY_ACTIVE_ENCOUNTER_FINISH:
                        finishActiveEncounter((Activity)propertyChangeEvent.getNewValue());
                        break;
                }
            }
        });
    }
}