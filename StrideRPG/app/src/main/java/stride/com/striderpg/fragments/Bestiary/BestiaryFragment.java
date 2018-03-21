package stride.com.striderpg.fragments.Bestiary;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stride.com.striderpg.R;

/**
 * Bestiary Fragment that displays information about the Players
 * current enemies defeated information and updated in real time.
 */
public class BestiaryFragment extends Fragment {

    /**
     * BestiaryFragment Logging tag.
     */
    private static final String TAG = "BestiaryFragment";

    /**
     * RecyclerView that will hold each individual Enemy CardView.
     */
    RecyclerView bestiaryRecyclerView;

    /**
     * BestiaryGenerator used to grab the Active Players current
     * information about enemies defeated when View is initially
     * created.
     */
    BestiaryGenerator bestiaryGenerator = new BestiaryGenerator();

    /**
     * BestiaryAdapter for inflating and instantiating each CardView
     * with Active Players enemies defeated information.
     */
    BestiaryAdapter bestiaryAdapter;

    /**
     * Empty Public Constructor.
     */
    public BestiaryFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bestiary, container, false);

        // Set the RecyclerView to the inflated rootView rv (RecyclerView).
        bestiaryRecyclerView = rootView.findViewById(R.id.rv);
        bestiaryRecyclerView.setHasFixedSize(true);

        // Set the LayoutManager used in the Bestiary.
        GridLayoutManager glm = new GridLayoutManager(this.getContext(), 3);
        bestiaryRecyclerView.setLayoutManager(glm);

        // Create the Adapter used to inflate each Enemy CardView.
        bestiaryAdapter = new BestiaryAdapter(bestiaryGenerator.getEnemies());
        bestiaryRecyclerView.setAdapter(bestiaryAdapter);
        return rootView;
    }
}
