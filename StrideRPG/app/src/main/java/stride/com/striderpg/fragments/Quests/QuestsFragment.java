package stride.com.striderpg.fragments.Quests;


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
 * Quests Fragment that displays information about a players current
 * quests and the progress between each.
 */
public class QuestsFragment extends Fragment {

    /**
     * QuestsFragment Logging tag.
     */
    private static final String TAG = "QuestsFragment";

    /**
     * RecyclerView that will hold each individual Quest CardView.
     */
    RecyclerView questsRecyclerView;

    /**
     * QuestsGenerator used to grab the current Quests from
     * the active players QuestLog.
     */
    QuestsGenerator generator = new QuestsGenerator();

    /**
     * Empty Public Constructor.
     */
    public QuestsFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_quests, container, false);

        // Set the RecyclerView to the inflated rootView rv (RecyclerView).
        questsRecyclerView = rootView.findViewById(R.id.rv);
        questsRecyclerView.setHasFixedSize(true);

        // Set the LayoutManager used in the Quests.
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        questsRecyclerView.setLayoutManager(llm);

        // Create the Adapter used to inflate each Quest CardView.
        QuestsAdapter adapter = new QuestsAdapter(generator.getQuests());
        questsRecyclerView.setAdapter(adapter);
        return rootView;
    }
}