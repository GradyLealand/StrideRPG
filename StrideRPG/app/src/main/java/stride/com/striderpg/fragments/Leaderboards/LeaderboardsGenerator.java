package stride.com.striderpg.fragments.Leaderboards;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import stride.com.striderpg.database.DBKeys;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * Leaderboards Generator class for creating thr ArrayList of Players that
 * will be sorted and displayed inside of the Leaderboards Fragment using
 * the RecyclerView / CardView and Adapter.
 */
public class LeaderboardsGenerator {

    /**
     * ArrayList of type Player to store all the Players in the database.
     */
    private ArrayList<Player> players = new ArrayList<>();

    /**
     * LeaderboardsGenerator constructor that will call the updateLeaderboards()
     * method to asynchronously retrieve and sort the Players in the FirebaseDatabase.
     */
    public LeaderboardsGenerator() {
        updateLeaderboards();
    }

    /**
     * UpdateLeaderboards method to retrieve the Players from the Database and sort them by
     * total amount of experience.
     */
    public void updateLeaderboards() {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child(DBKeys.USERS_KEY);
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                players.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    players.add(s.getValue(Player.class));
                }

                // Sort the new List of Players by amount of experience.
                Collections.sort(players, new Comparator<Player>() {
                    @Override
                    public int compare(Player player, Player player2) {
                        return player2.getExperience().compareTo(player.getExperience());
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    /**
     * Retrieve the ArrayList of type Player.
     * @return players ArrayList
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Set the ArrayList of type Player.
     * @param players ArrayList being set to players.
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
