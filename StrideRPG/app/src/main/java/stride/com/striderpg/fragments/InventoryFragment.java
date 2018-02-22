package stride.com.striderpg.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stride.com.striderpg.R;

/**
 * Equipment Fragment for displaying a Users inventory and giving
 * them options to equip, un-equip, trash and look at stats on each
 * item in their Equipment.
 */
public class InventoryFragment extends Fragment {

    /**
     * InventoryFragment Logging tag.
     */
    private static final String TAG = "InventoryFragment";

    /**
     * Required empty public constructor function.
     */
    public InventoryFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory, container, false);
    }
}
