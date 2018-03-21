package stride.com.striderpg.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stride.com.striderpg.R;

/**
 * Quests Fragment that displays information about a players current
 * quests and the progress between each.
 */
public class QuestsFragment extends Fragment {

    /**
     * QuestsFragment Logging tag.
     */
    private static final String TAG = "QuestsFragment";

    /**
     * Empty Public Constructor.
     */
    public QuestsFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quests, container, false);
    }
}