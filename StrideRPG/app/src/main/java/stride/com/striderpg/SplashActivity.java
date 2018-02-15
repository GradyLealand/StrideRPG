package stride.com.striderpg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataType;

/**
 * SplashActivity used to request permissions from the User.
 * The application needs the Google Fit information request
 * before beginning the authentication activity.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * SplashActivity Logging tag.
     */
    private static final String TAG = "SplashActivity";

    /**
     * Result code for Fitness api activity.
     */
    private static final int GOOGLE_FITNESS_PERMISSIONS_REQUEST = 0x1001;

    /**
     * This onCreate will simply use the splash_background.xml drawable
     * file as the background of the activity as a way to present
     * a screen other then blank white space while the app is starting.
     * From this point, the app will move onto authentication the current user.
     * using Firebase.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the FitnessOptions Object to hold all relevant information for using
        // the fitness api and what data types we want to retrieve.
        FitnessOptions fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
                .build();

        // Check on runtime if the current user has granted permissions for Google Fitness api.
        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this),
                fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    this,
                    GOOGLE_FITNESS_PERMISSIONS_REQUEST,
                    GoogleSignIn.getLastSignedInAccount(this),
                    fitnessOptions
            );
        } else {
            goToAuth();
        }
    }

    /**
     * Override the onActivityResult callback to deal with the users
     * selection on permission request.
     * @param requestCode Request code from permission request.
     * @param resultCode Result of permission request.
     * @param data Intent data coming from request.
     */
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GOOGLE_FITNESS_PERMISSIONS_REQUEST) {
                goToAuth();
            }
        } else {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * Send the user to the Authentication activity.
     */
    private void goToAuth() {
        startActivity(new Intent(SplashActivity.this, AuthActivity.class));
        finish();
    }
}
