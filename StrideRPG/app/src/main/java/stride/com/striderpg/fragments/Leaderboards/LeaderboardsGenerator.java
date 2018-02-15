package stride.com.striderpg.fragments.Leaderboards;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import stride.com.striderpg.database.DBKeys;
import stride.com.striderpg.rpg.models.Player.Player;

public class LeaderboardsGenerator {

    private ArrayList<Player> players = new ArrayList<>();

    public LeaderboardsGenerator() {
        updateLeaderboards();
    }

    public void updateLeaderboards() {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child(DBKeys.USERS_KEY);

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                players.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    players.add(s.getValue(Player.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
