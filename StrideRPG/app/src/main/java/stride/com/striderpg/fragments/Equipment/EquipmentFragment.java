package stride.com.striderpg.fragments.Equipment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
     * RecyclerView that will hold each individual Item CardView.
     */
    RecyclerView equipmentRecyclerView;

    /**
     * EquipmentGenerator used to grab the current Items from
     * the active players Equipment slots HashMap.
     */
    EquipmentGenerator generator = new EquipmentGenerator();

    /**
     * Empty Public Constructor.
     */
    public EquipmentFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_equipment, container, false);

        // Set the RecyclerView to the inflated rootView rv (RecyclerView).
        equipmentRecyclerView = rootView.findViewById(R.id.rv);
        equipmentRecyclerView.setHasFixedSize(true);

        // Set the LayoutManager used in the Quests.
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        equipmentRecyclerView.setLayoutManager(llm);

        // Create the Adapter used to inflate each Quest CardView.
        EquipmentAdapter adapter = new EquipmentAdapter(generator.getItems());
        equipmentRecyclerView.setAdapter(adapter);
        return rootView;
    }
}
