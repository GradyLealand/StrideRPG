package stride.com.striderpg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import stride.com.striderpg.global.Globals;

public class NavigationActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    mTextMessage.setText(Globals.activePlayer.getUsername());
                    return true;
                case R.id.navigation_quests:
                    mTextMessage.setText(R.string.navbar_quests);
                    return true;
                case R.id.navigation_inventory:
                    mTextMessage.setText(R.string.navbar_inventory);
                    return true;
                case R.id.navigation_bestiary:
                    mTextMessage.setText(R.string.navbar_bestiary);
                    return true;
                case R.id.navigation_leaderboards:
                    mTextMessage.setText(R.string.navbar_leaderboard);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        // Setting default stuff to appear, same as if selected in switch case
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        mTextMessage.setText(Globals.activePlayer.getUsername());

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
