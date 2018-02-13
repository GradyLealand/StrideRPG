package stride.com.striderpg.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import stride.com.striderpg.R;
import stride.com.striderpg.global.Globals;
import stride.com.striderpg.global.PushTimer;

/**
 * Dashboard Fragment for displaying a Users recent activity log and a profile bar with information
 * about their account at the top of the screen.
 */
public class DashboardFragment extends Fragment {

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

        // Active player property change listener implementation.
        Globals.activePlayer.addPropertyChangeListener(new PropertyChangeListener() {
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
                        break;
                    case "inventory":
                        break;
                }
            }
        });

        // Skills property change listener implementation.
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