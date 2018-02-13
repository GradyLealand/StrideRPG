package stride.com.striderpg.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import stride.com.striderpg.R;
import stride.com.striderpg.database.FirebaseDBUtil;
import stride.com.striderpg.global.Globals;
import stride.com.striderpg.global.PushTimer;

/**
 * Dashboard Fragment for displaying a Users recent activity log and a profile bar with information
 * about their account at the top of the screen.
 */
public class DashboardFragment extends Fragment {

    private TextView playerUsernameTextView;
    private TextView playerExperienceTextView;
    private TextView playerLevelTextView;

    private Button testLevelUpBtn;
    private Button testLevelDownBtn;
    private Button testRandomExpBtn;

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

        playerUsernameTextView = getView().findViewById(R.id.playerUsernameTextView);
        playerExperienceTextView = getView().findViewById(R.id.playerExperienceTextView);
        playerLevelTextView = getView().findViewById(R.id.playerLevelTextView);

        testLevelUpBtn = getView().findViewById(R.id.testLevelUpBtn);
        testLevelDownBtn = getView().findViewById(R.id.testLevelDownBtn);
        testRandomExpBtn = getView().findViewById(R.id.testRandomExpBtn);

        // Start PushTimer.
        new PushTimer().start();

         // [BEGIN] Testing.
        testLevelUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.activePlayer.levelUp();
            }
        });

        testLevelDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.activePlayer.setLevel(Globals.activePlayer.getLevel() - 1);
            }
        });

        testRandomExpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                Globals.activePlayer.setExperience(r.nextInt(10000));
            }
        });
        // [END] Testing.


        Globals.activePlayer.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                switch (propertyChangeEvent.getPropertyName()) {
                    case "experience":
                        playerExperienceTextView.setText(propertyChangeEvent.getNewValue().toString());
                        break;
                    case "username":
                        playerUsernameTextView.setText(propertyChangeEvent.getNewValue().toString());
                        break;
                    case "level":
                        playerLevelTextView.setText(propertyChangeEvent.getNewValue().toString());
                        break;
                    case "steps":
                        break;
                    case "inventory":
                        break;
                }
            }
        });

        Globals.activePlayer.getSkills().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                switch (propertyChangeEvent.getPropertyName()) {
                    case "strength":
                        break;
                    case "vitality":
                        break;
                    case "speed":
                        break;
                }
            }
        });
    }
}