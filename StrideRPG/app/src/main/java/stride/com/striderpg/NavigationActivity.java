package stride.com.striderpg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import stride.com.striderpg.database.DBKeys;
import stride.com.striderpg.fragments.Generator.FragmentGenerator;
import stride.com.striderpg.global.Globals;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * Main Navigation Activity in the Application. This Activity is the main route for a User to travel
 * between the different Fragments present. by clicking on a Navigation Item located in the bottom
 * of the View, a new Fragment is selected and replaced with the old fragment in the fragmentContainer.
 */
public class NavigationActivity extends AppCompatActivity {

    /**
     * FragmentGenerator instance to create and initialize the five fragments in game.
     */
    private FragmentGenerator generator = new FragmentGenerator();

    /**
     * Custom Listener to change the Fragment every time a new Navigation Item is selected.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, generator.dashboardFragment).commit();
                    return true;
                case R.id.navigation_quests:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, generator.questsFragment).commit();
                    return true;
                case R.id.navigation_inventory:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, generator.inventoryFragment).commit();
                    return true;
                case R.id.navigation_bestiary:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, generator.bestiaryFragment).commit();
                    return true;
                case R.id.navigation_leaderboards:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, generator.leaderboardsFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, generator.dashboardFragment).commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);

        // Setting default stuff to appear, same as if selected in switch case
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference(DBKeys.USERS_KEY).child(Globals.activePlayer.getUniqueId());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Globals.activePlayer = dataSnapshot.getValue(Player.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}