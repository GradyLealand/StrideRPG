package stride.com.striderpg;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import stride.com.striderpg.fragments.BestiaryFragment;
import stride.com.striderpg.fragments.DashboardFragment;
import stride.com.striderpg.fragments.Generator.FragmentGenerator;
import stride.com.striderpg.fragments.InventoryFragment;
import stride.com.striderpg.fragments.LeaderboardsFragment;
import stride.com.striderpg.fragments.QuestsFragment;
import stride.com.striderpg.global.Globals;

public class NavigationActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FragmentGenerator generator = new FragmentGenerator();

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

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);

        // Setting default stuff to appear, same as if selected in switch case
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



}
