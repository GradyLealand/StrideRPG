package stride.com.striderpg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import stride.com.striderpg.fragments.Generator.FragmentGenerator;

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
}