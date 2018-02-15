package stride.com.striderpg;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import stride.com.striderpg.fragments.Generator.FragmentGenerator;
import stride.com.striderpg.global.G;

/**
 * Main Navigation Activity in the Application. This Activity is the main route for a User to travel
 * between the different Fragments present. by clicking on a Navigation Item located in the bottom
 * of the View, a new Fragment is selected and replaced with the old fragment in the fragmentContainer.
 */
public class NavigationActivity extends AppCompatActivity {

    /**
     * NavigationActivity logging tag.
     */
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
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Add all Fragments to the NavigationActivity (Dashboard selected by default).
        addFragments();

        G.fitnessUtil.readData();
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
    }
}