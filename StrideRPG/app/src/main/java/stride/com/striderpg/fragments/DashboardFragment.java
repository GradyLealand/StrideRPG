package stride.com.striderpg.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;

/**
 * Dashboard Fragment for displaying a Users recent activity log and a profile bar with information
 * about their account at the top of the screen.
 */
public class DashboardFragment extends Fragment {

    private TextView stepsTextView;

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

        stepsTextView = getView().findViewById(R.id.stepsText);
        stepsTextView.setText(G.activePlayer.getSteps().toString());

        // Add a new PropertyChangeListener to the active Player object for handling player property changes.
        G.activePlayer.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                switch (propertyChangeEvent.getPropertyName()) {
                    case "experience":
                        break;
                    case "username":
                        break;
                    case "level":
                        break;
                    case "steps":
                        stepsTextView.setText(propertyChangeEvent.getNewValue().toString());
                        break;
                    case "inventory":
                        break;
                }
            }
        });
    }
}