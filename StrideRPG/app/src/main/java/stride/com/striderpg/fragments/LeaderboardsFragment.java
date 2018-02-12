package stride.com.striderpg.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stride.com.striderpg.R;

/**
 * Leaderboards Fragment for displaying different Players in the game and sorting them based on rank,
 * total steps, total experience, level, enemies defeated... etc.
 */
public class LeaderboardsFragment extends Fragment {

    /**
     * Required empty public constructor function.
     */
    public LeaderboardsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboards, container, false);
    }
}