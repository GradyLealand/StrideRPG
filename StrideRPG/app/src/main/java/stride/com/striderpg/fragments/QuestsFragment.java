package stride.com.striderpg.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stride.com.striderpg.R;

/**
 * Quests Fragment for showing a user their current progress in the game and how close or far
 * away they are from unlocking a new quest/achievement.
 */
public class QuestsFragment extends Fragment {

    /**
     * Required empty public constructor function.
     */
    public QuestsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quests, container, false);
    }
}