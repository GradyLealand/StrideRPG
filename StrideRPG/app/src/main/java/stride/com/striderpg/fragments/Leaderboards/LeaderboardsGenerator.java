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
import stride.com.striderpg.rpg.Enums;
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

                // Sort the players ArrayList after creating.
                sort(players, Enums.PlayerSort.LEVEL);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void sort(ArrayList<Player> collection, final Enums.PlayerSort sortType) {
        Collections.sort(collection, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                switch (sortType) {
                    case LEVEL:
                        return p2.getLevel().compareTo(p1.getLevel());
                    case EXPERIENCE:
                        return p2.getExperience().compareTo(p1.getExperience());
                    case ENEMIES_DEFEATED:
                        return p2.getEnemiesDefeated().compareTo(p1.getEnemiesDefeated());
                    case STEPS:
                        return p2.getSteps().compareTo(p1.getSteps());

                    // Sort by level if no proper sort type is given.
                    default:
                        return p2.getLevel().compareTo(p1.getLevel());
                }
            }
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
