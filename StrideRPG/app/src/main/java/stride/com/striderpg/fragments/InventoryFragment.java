package stride.com.striderpg.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stride.com.striderpg.R;

/**
 * Inventory Fragment that displays a Players current equipment
 * and what they have equipped as well as their statistics gained
 * from the equipment.
 */
public class InventoryFragment extends Fragment {

    /**
     * InventoryFragment Logging tag.
     */
    private static final String TAG = "InventoryFragment";

    /**
     * Empty Public Constructor.
     */
    public InventoryFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory, container, false);
    }
}
