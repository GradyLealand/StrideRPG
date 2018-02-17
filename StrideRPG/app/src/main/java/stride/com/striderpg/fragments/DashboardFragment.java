package stride.com.striderpg.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import stride.com.striderpg.rpg.Generators.LevelGenerator;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
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

        // Add a new PropertyChangeListener to the active Player object for handling property changes.
        // Whenever the Global Player (G.activePlayer) has a property changed with a value that isn't
        // the same as the current value. This property change event will fire with the changed property
        // as the propertyChangeEvent.getPropertyName(). switch through and change UI based on changes.
        G.activePlayer.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                Log.d(TAG, String.format(
                        G.locale,
                        "propertyChange:%s has been fired", propertyChangeEvent.getPropertyName())
                );

                switch (propertyChangeEvent.getPropertyName()) {
                    // If Player experience property has been changed.
                    case "experience":
                        updateLevelProgressBar((int)propertyChangeEvent.getNewValue());
                        break;
                    // If Player username property has been changed.
                    case "username":
                        break;
                    // If Player level property has been changed.
                    case "level":
                        updateLevelText((int)propertyChangeEvent.getNewValue());
                        updateLevelProgressBar(G.activePlayer.getExperience());
                        break;
                    // If Player steps property has been changed.
                    case "steps":
                        updateStepsTextView((int)propertyChangeEvent.getNewValue());
                        break;
                }
            }
        });
    }

    /**
     * Update the Dashboards steps TextView with the passed paremeter with commas added.
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
        playerLevelProgressBar.setMax(LevelGenerator.experienceToNextLevel(G.activePlayer.getLevel()));
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
     * Parse a String representing the users currentExp/expToLevel.
     * @return  Helpful String to show user exp/exp needed.
     */
    private String parseExpAmount(Integer exp) {
        return exp + " / " + LevelGenerator.experienceToNextLevel(G.activePlayer.getLevel());
    }

    /**
     * Set the required Dashboard UI elements using the current view findViewById method.
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