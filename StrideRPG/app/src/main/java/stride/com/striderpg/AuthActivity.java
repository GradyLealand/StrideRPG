package stride.com.striderpg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
    // Logging tag.
    private static final String TAG = "AuthActivity";
    // Result int code.
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    private ImageView logoImageView;
    private ProgressBar authProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // Widgets
        logoImageView = findViewById(R.id.logoImageView);
        authProgressBar = findViewById(R.id.authProgressBar);
    }
}
