package stride.com.striderpg.fragments.Leaderboards;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
     * LeaderboardsFragment Logging tag.
     */
    private static final String TAG = "LeaderboardsFragment";

    /**
     * Instance the recycler view used to create the list of Players
     * in the GUI dynamically.
     */
    RecyclerView leaderboardsRecyclerView;

    /**
     * Generator instance used to retrieve Players. Constructor call will
     * start the asynchronous call to the FirebaseDatabase to create the Leaderboards UI.
     */
    LeaderboardsGenerator generator = new LeaderboardsGenerator();

    /**
     * Required empty public constructor function.
     */
    public LeaderboardsFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Main LeaderboardsFragment View creation. The RecyclerView, Adapter and LayoutManager are
     * all used here to create the Layout and dynamically create the Leaderboards View.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_leaderboards_rv, container, false);

        // Get the leaderboardsRecyclerView from the rootView that is inflated from
        // the fragment_leaderboards_rv xml layout that contains the rv RecyclerView.
        leaderboardsRecyclerView = rootView.findViewById(R.id.rv);
        leaderboardsRecyclerView.setHasFixedSize(true);

        // LinearLayoutManager used here, will layout the elements in a similar fashion to a ListView
        // would lay things out. RecyclerView.LayoutManager defines how the elements are laid out.
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        leaderboardsRecyclerView.setLayoutManager(llm);

        // Set the LeaderboardsAdapter as the adapter for this RecyclerView.
        LeaderboardsAdapter adapter = new LeaderboardsAdapter(generator.getPlayers());
        leaderboardsRecyclerView.setAdapter(adapter);

        Log.d(TAG, "onCreateView:success");
        return rootView;
    }
}