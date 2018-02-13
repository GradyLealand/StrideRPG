package stride.com.striderpg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import stride.com.striderpg.database.DBKeys;
import stride.com.striderpg.fragments.Generator.FragmentGenerator;
import stride.com.striderpg.global.Globals;
import stride.com.striderpg.rpg.Generators.LevelGenerator;
import stride.com.striderpg.rpg.models.Player.Inventory;
import stride.com.striderpg.rpg.models.Player.Skills;

/**
 * Main Navigation Activity in the Application. This Activity is the main route for a User to travel
 * between the different Fragments present. by clicking on a Navigation Item located in the bottom
 * of the View, a new Fragment is selected and replaced with the old fragment in the fragmentContainer.
 */
public class NavigationActivity extends AppCompatActivity {

    public static final String TAG = "NavigationActivity";

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
        buildDatabaseListeners();

        for (int i = 1; i <= 100; i++) {
            Integer testExp = LevelGenerator.experienceToNextLevel(i);
            System.out.println(i + ": " + testExp);
        }

    }

    /**
     * Method to define the DatabaseReferences and ValueEventListeners from the FirebaseDatabase
     * so that the Globals.activePlayer updates and PropertyChangeListeners can be fired when
     * a players data is changed in the database.
     */
    private void buildDatabaseListeners() {
        DatabaseReference userRef = FirebaseDatabase.getInstance()
                .getReference(DBKeys.USERS_KEY)
                .child(Globals.activePlayer.getUniqueId());

        // Create database references for each player information node.
        DatabaseReference expRef = userRef.child(DBKeys.EXPERIENCE_KEY);
        DatabaseReference invRef = userRef.child(DBKeys.INVENTORY_KEY);
        DatabaseReference lvlRef = userRef.child(DBKeys.LEVEL_KEY);
        DatabaseReference skillRef = userRef.child(DBKeys.SKILLS_KEY);
        DatabaseReference stepsRef = userRef.child(DBKeys.STEPS_KEY);
        DatabaseReference usernameRef = userRef.child(DBKeys.USERNAME_KEY);

        // Create the value event listeners for each database reference.
        expRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer exp = dataSnapshot.getValue(Integer.class);
                if (!Objects.equals(exp, Globals.activePlayer.getExperience()))
                    Globals.activePlayer.setExperience(exp);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, String.format("key: '%s' read failed.", DBKeys.EXPERIENCE_KEY));
            }
        });

        invRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Inventory inventory = dataSnapshot.getValue(Inventory.class);

                assert inventory != null;
                if (!Objects.equals(inventory.getItems().keySet(), Globals.activePlayer.getInventory().getItems().keySet()))
                    Globals.activePlayer.setInventory(inventory);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, String.format("key: '%s' read failed.", DBKeys.INVENTORY_KEY));
            }
        });

        lvlRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer level = dataSnapshot.getValue(Integer.class);
                if (!Objects.equals(level, Globals.activePlayer.getLevel()))
                    Globals.activePlayer.setLevel(level);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, String.format("key: '%s' read failed.", DBKeys.LEVEL_KEY));
            }
        });

        skillRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Skills skills = dataSnapshot.getValue(Skills.class);
                assert skills != null;
                if (!Objects.equals(skills.getSpeed(), Globals.activePlayer.getSkills().getSpeed()))
                    Globals.activePlayer.getSkills().setSpeed(skills.getSpeed());
                if (!Objects.equals(skills.getStrength(), Globals.activePlayer.getSkills().getStrength()))
                    Globals.activePlayer.getSkills().setStrength(skills.getStrength());
                if (!Objects.equals(skills.getVitality(), Globals.activePlayer.getSkills().getVitality()))
                    Globals.activePlayer.getSkills().setVitality(skills.getVitality());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, String.format("key: '%s' read failed.", DBKeys.SKILLS_KEY));
            }
        });

        stepsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer steps = dataSnapshot.getValue(Integer.class);
                if (!Objects.equals(steps, Globals.activePlayer.getSteps()))
                    Globals.activePlayer.setSteps(steps);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, String.format("key: '%s' read failed.", DBKeys.STEPS_KEY));
            }
        });

        usernameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.getValue(String.class);
                if (!Objects.equals(username, Globals.activePlayer.getUsername()))
                    Globals.activePlayer.setUsername(username);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, String.format("key: '%s' read failed.", DBKeys.USERNAME_KEY));
            }
        });
    }
}