package stride.com.striderpg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This onCreate will simply use the splash_background.xml drawable
        // file as the background of the activity as a way to present
        // a screen other then blank white space while the app is starting.
        // From this point, the app will move onto authentication the current user.
        startActivity(new Intent(SplashActivity.this, AuthActivity.class));
        finish();
    }
}
