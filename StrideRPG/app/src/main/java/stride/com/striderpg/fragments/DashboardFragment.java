package stride.com.striderpg.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    private ImageView playerProfileImage;
    private TextView playerUsernameText;
    private TextView playerExpCount;
    private ProgressBar playerLevelProgressBar;

    private TextView levelText;
    private TextView stepsText;
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

        playerProfileImage = getView().findViewById(R.id.playerProfileImage);
        playerUsernameText = getView().findViewById(R.id.playerUsernameText);
        playerExpCount = getView().findViewById(R.id.playerExpCount);
        playerLevelProgressBar = getView().findViewById(R.id.playerLevelProgressBar);

        levelText = getView().findViewById(R.id.levelText);
        stepsText = getView().findViewById(R.id.stepsText);
        enemiesText = getView().findViewById(R.id.enemiesText);

        stepsText.setText(addCommasToInteger(G.activePlayer.getSteps()));
        levelText.setText(G.activePlayer.getLevel().toString());
        enemiesText.setText(addCommasToInteger(G.activePlayer.getStats().getEnemiesDefeated()));
        updateLevelProgressBar();
        parseExpAmount();

        // Add a new PropertyChangeListener to the active Player object for handling player property changes.
        G.activePlayer.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                switch (propertyChangeEvent.getPropertyName()) {
                    case "experience":
                        updateLevelProgressBar();
                        playerExpCount.setText(parseExpAmount());
                        break;
                    case "username":
                        break;
                    case "level":
                        levelText.setText(G.activePlayer.getLevel().toString());
                        break;
                    case "steps":
                        stepsText.setText(addCommasToInteger((int)propertyChangeEvent.getNewValue()));
                        break;
                    case "inventory":
                        break;
                }
            }
        });
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
    private String parseExpAmount() {
        return G.activePlayer.getExperience() + "/" + LevelGenerator.experienceToNextLevel(G.activePlayer.getLevel());
    }

    /**
     * Update the Level ProgressBar with users current exp and the exp needed to level up.
     */
    private void updateLevelProgressBar() {
        Integer expNeeded = LevelGenerator.experienceToNextLevel(G.activePlayer.getLevel());

        playerLevelProgressBar.setMax(expNeeded);
        playerLevelProgressBar.setProgress(G.activePlayer.getExperience());
    }
}