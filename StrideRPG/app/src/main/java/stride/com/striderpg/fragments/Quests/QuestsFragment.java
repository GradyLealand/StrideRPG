package stride.com.striderpg.fragments.Quests;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;

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
     * QuestsAdapter for inflating and instantiating each
     * CardView with Active Players current quest log.
     */
    QuestsAdapter adapter;

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
        adapter = new QuestsAdapter(generator.getQuests());
        questsRecyclerView.setAdapter(adapter);

        // Build out PropertyChangeListener.
        buildPropertyChangeListeners();
        return rootView;
    }

    /**
     * Build out the PropertyChangeListener implementation
     * for dealing with QuestLog updates.
     */
    private void buildPropertyChangeListeners() {
        // Add a new PropertyChangeListener implementation that controls
        // the ui elements being changed when their internal value changes.
        G.activePlayer.getQuestLog().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                switch (propertyChangeEvent.getPropertyName()) {
                    case Constants.PROPERTY_QUEST_LOG_UPDATED:
                        adapter.update();
                        break;
                }
            }
        });
    }
}