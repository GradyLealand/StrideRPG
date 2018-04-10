package stride.com.striderpg.fragments.Leaderboards;


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
 * Leaderboards Fragment that displays Players in game in a leaderboard
 * style.
 */
public class LeaderboardsFragment extends Fragment {

    /**
     * LeaderboardsFragment Logging tag.
     */
    private static final String TAG = "LeaderboardsFragment";

    /**
     * RecyclerView that will hold each individual Player CardView.
     */
    RecyclerView leaderboardsRecyclerView;

    /**
     * LeaderboardGenerator used to grab the current Players from
     * the Firebase Database when View is initially created.
     */
    LeaderboardsGenerator generator = new LeaderboardsGenerator();

    /**
     * Empty Public Constructor.
     */
    public LeaderboardsFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        View rootView = inflater.inflate(R.layout.fragment_leaderboards_rv, container, false);

        // Set the RecyclerView to the inflated rootView rv (RecyclerView).
        leaderboardsRecyclerView = rootView.findViewById(R.id.rv);
        leaderboardsRecyclerView.setHasFixedSize(true);

        // Set the LayoutManager used in the Leaderboards.
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        leaderboardsRecyclerView.setLayoutManager(llm);

        // Create the Adapter used to inflate each Player CardView.
        LeaderboardsAdapter adapter = new LeaderboardsAdapter(generator.getPlayers());
        leaderboardsRecyclerView.setAdapter(adapter);
        return rootView;
    }
}