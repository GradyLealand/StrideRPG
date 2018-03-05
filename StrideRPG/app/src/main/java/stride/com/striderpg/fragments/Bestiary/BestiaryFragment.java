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
 * Bestiary Fragment class for showing a User information about the
 * enemies they've defeated as well as the amount of each enemy
 * they've defeated.
 */
public class BestiaryFragment extends Fragment {

    /**
     * BestiaryFragment Logging tag.
     */
    private static final String TAG = "BestiaryFragment";

    RecyclerView bestiaryRecyclerView;

    BestiaryGenerator bestiaryGenerator = new BestiaryGenerator();

    BestiaryAdapter bestiaryAdapter;

    /**
     * Required empty public constructor function.
     */
    public BestiaryFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bestiary, container, false);

        bestiaryRecyclerView = rootView.findViewById(R.id.rv);
        bestiaryRecyclerView.setHasFixedSize(true);

        GridLayoutManager glm = new GridLayoutManager(this.getContext(), 3);
        bestiaryRecyclerView.setLayoutManager(glm);

        bestiaryAdapter = new BestiaryAdapter(bestiaryGenerator.getEnemies());
        bestiaryRecyclerView.setAdapter(bestiaryAdapter);

        return rootView;
    }
}
