package stride.com.striderpg.fragments.Leaderboards;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import stride.com.striderpg.database.DBKeys;
import stride.com.striderpg.database.FirebaseDBUtil;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * LeaderboardsGenerator used to retrieve/generate the Player ArrayList
 * containing each Player in the FirebaseDatabase.
 */
public class LeaderboardsGenerator {

    /**
     * LeaderboardsGenerator Logging tag.
     */
    private static final String TAG = "LeaderboardsGenerator";

    /**
     * Player ArrayList to hold each Player that will be inside of the
     * Leaderboards.
     */
    private ArrayList<Player> players = new ArrayList<>();

    /**
     * Constructor that calls the buildLeaderboards() method to
     * generate the Leaderboards information/data.
     */
    LeaderboardsGenerator() {
        buildLeaderboards();
    }

    /**
     * Build out the players ArrayList by making a call to the Database
     * and attaching a onDataChange callback to loop through results.
     */
    private void buildLeaderboards() {
        // Create reference to the users node in the Database.
        DatabaseReference usersRef = FirebaseDatabase.getInstance()
                .getReference()
                .child(DBKeys.USERS_KEY);

        // Add a ValueEventListener that listens for a single response.
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    try {
                        // Add Player to players ArrayList.
                        players.add(s.getValue(Player.class));
                    } catch (Exception e) {
                        Log.e(TAG, "add:error:", e);
                    }
                }
                // After the players ArrayList is created and filled with all players from
                // DataSnapshot. Sort the ArrayList.
                sort(Enums.PlayerSort.LEVEL);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "getAllPlayers:error", databaseError.toException());
            }
        });
    }

    /**
     * Custom sort method to organize the ArrayList of Players based
     * on the PlayerSort Enumeration type passed to method.
     */
    private void sort(final Enums.PlayerSort sortType) {
        Log.d(TAG, String.format(G.locale, "sort:begin:type=%s", sortType));
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                switch (sortType) {
                    case EXPERIENCE:
                        return p2.getExperience().compareTo(p1.getExperience());
                    case ENEMIES_DEFEATED:
                        return p2.getStats().getEnemiesDefeated().compareTo(p1.getStats().getEnemiesDefeated());
                    case STEPS:
                        return p2.getSteps().compareTo(p1.getSteps());
                    // LEVEL / default case are the same.
                    case LEVEL:
                        return p2.getLevel().compareTo(p1.getLevel());
                    default:
                        return p2.getLevel().compareTo(p1.getLevel());
                }
            }
        });
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
