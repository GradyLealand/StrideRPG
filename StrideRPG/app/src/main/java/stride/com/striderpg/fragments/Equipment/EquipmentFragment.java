package stride.com.striderpg.fragments.Equipment;


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
public class EquipmentFragment extends Fragment {

    /**
     * EquipmentFragment Logging tag.
     */
    private static final String TAG = "EquipmentFragment";

    /**
     * Empty Public Constructor.
     */
    public EquipmentFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipment, container, false);
    }
}
