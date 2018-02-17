package stride.com.striderpg.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stride.com.striderpg.R;

/**
 * Bestiary Fragment class for showing a User information about the enemies they've defeated
 * as well as the amount of each enemy they've defeated.
 */
public class BestiaryFragment extends Fragment {

    /**
     * BestiaryFragment Logging tag.
     */
    private static final String TAG = "BestiaryFragment";

    /**
     * Required empty public constructor function.
     */
    public BestiaryFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bestiary, container, false);
    }
}
