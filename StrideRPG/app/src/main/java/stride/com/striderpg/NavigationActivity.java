package stride.com.striderpg;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import stride.com.striderpg.database.FirebaseDBUtil;
import stride.com.striderpg.fragments.Generator.FragmentGenerator;
import stride.com.striderpg.global.G;
import stride.com.striderpg.global.PushTimer;
import stride.com.striderpg.rpg.utils.TimestampParser;

/**
 * Main Navigation Activity in the Application. This Activity is the main route for a User to travel
 * between the different Fragments present. by clicking on a Navigation Item located in the bottom
 * of the View, a new Fragment is selected and replaced with the old fragment in the fragmentContainer.
 */
public class NavigationActivity extends AppCompatActivity {

    /**
     * NavigationActivity logging tag.
     */
    private static final String TAG = "NavigationActivity";

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
                    showDashboard();
                    return true;
                case R.id.navigation_quests:
                    showQuests();
                    return true;
                case R.id.navigation_inventory:
                    showInventory();
                    return true;
                case R.id.navigation_bestiary:
                    showBestiary();
                    return true;
                case R.id.navigation_leaderboards:
                    showLeaderboards();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Set the dashboard as the default Fragment to visit on app start up.
        navigation.setSelectedItemId(R.id.navigation_dashboard);

        // Add all Fragments to the NavigationActivity (Dashboard selected by default).
        addFragments();

        // Start background Tasks for reading users current steps and pushing active Player
        // to the database, these tasks are done at a fixed rate (Constants class).
        new PushTimer().startTimers();
    }

    /**
     * When the app is closed, ensure that the active Player is pushed to the Database.
     */
    @Override
    protected void onStop() {
        super.onStop();

        // Set the current Players last signed in date as they leave the app.
        G.activePlayer.setLastSignedIn(TimestampParser.makeTimestamp());
        // Create a database utility and push the active Player to the database.
        FirebaseDBUtil db = new FirebaseDBUtil();
        db.pushPlayer(G.activePlayer);

        Log.d(TAG, "onStop:success");
    }

    /**
     * Display the DashboardFragment and hide every other Fragment.
     */
    private void showDashboard() {
        getSupportFragmentManager().beginTransaction().show(generator.dashboardFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.questsFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.inventoryFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.bestiaryFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.leaderboardsFragment).commit();
        Log.d(TAG, "showDashboard:success");
    }

    /**
     * Display the QuestsFragment and hide every other Fragment.
     */
    private void showQuests() {
        getSupportFragmentManager().beginTransaction().show(generator.questsFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.dashboardFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.inventoryFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.bestiaryFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.leaderboardsFragment).commit();
        Log.d(TAG, "showQuests:success");
    }

    /**
     * Display the InventoryFragment and hide every other Fragment.
     */
    private void showInventory() {
        getSupportFragmentManager().beginTransaction().show(generator.inventoryFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.dashboardFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.questsFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.bestiaryFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.leaderboardsFragment).commit();
        Log.d(TAG, "showInventory:success");
    }

    /**
     * Display the BestiaryFragment and hide every other Fragment.
     */
    private void showBestiary() {
        getSupportFragmentManager().beginTransaction().show(generator.bestiaryFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.dashboardFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.questsFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.inventoryFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.leaderboardsFragment).commit();
        Log.d(TAG, "showBestiary:success");
    }

    /**
     * Display the LeaderboardsFragment and hide every other Fragment.
     */
    private void showLeaderboards() {
        getSupportFragmentManager().beginTransaction().show(generator.leaderboardsFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.dashboardFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.questsFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.bestiaryFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(generator.inventoryFragment).commit();
        Log.d(TAG, "showLeaderboards:success");
    }

    /**
     * Add all Fragments to the NavigationActivity fragmentContainer and hide all of them except
     * for the DashboardFragment.
     */
    private void addFragments() {
        // Add the Dashboard to the fragmentContainer.
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, generator.dashboardFragment)
                .commit();

        // Begin adding the rest of the fragments to the fragmentContainer
        // and hide them as they're added.
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, generator.questsFragment)
                .hide(generator.questsFragment)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, generator.inventoryFragment)
                .hide(generator.inventoryFragment)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, generator.bestiaryFragment)
                .hide(generator.bestiaryFragment)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, generator.leaderboardsFragment)
                .hide(generator.leaderboardsFragment)
                .commit();

        Log.d(TAG, "addFragments:success");
    }
}