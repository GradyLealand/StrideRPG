package stride.com.striderpg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import stride.com.striderpg.database.DBKeys;
import stride.com.striderpg.database.FirebaseDBUtil;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * Main Authentication activity that handles any logging in, resource gathering
 * and database retrieval before moving onto the Main/Home Activity.
 */
public class AuthActivity extends AppCompatActivity {

    /**
     * AuthActivity Logging tag.
     */
    private static final String TAG = "AuthActivity";

    /**
     * Result code for Sign-in activity.
     */
    private static final int RC_SIGN_IN = 9001;

    /**
     * FirebaseAuth Object for dealing with the Firebase Authentication functionality.
     */
    private FirebaseAuth mAuth;

    /**
     * GoogleSignInClient Object for granting a user permission to the application and allowing
     * them to sign in through the use of the Google API.
     */
    private GoogleSignInClient mGoogleSignInClient;

    // UI elements here.
    private ImageView logoImageView;
    private TextView authTask;
    private ProgressBar authProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        logoImageView = findViewById(R.id.logoImageView);
        authTask = findViewById(R.id.authTask);
        authProgressBar = findViewById(R.id.authProgressBar);

        // Set current task to auth_init. Initializing...
        authTask.setText(R.string.auth_init);

        // Configure Google Sign In Options.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Initialize the Firebase authentication api.
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();

        authProgressBar.setVisibility(View.VISIBLE);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        checkUser(currentUser);
    }

    /**
     * Override the onActivityResult function and check for a Google Sign In result using the
     * RC_SIGN_IN code.
     * @param requestCode int representing the activity completed.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase.
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In has failed, throw a null into the checkUser method.
                Log.e(TAG, "Google Sign-In has failed:", e);
                checkUser(null);
            }
        }
    }

    /**
     * Main Firebase authentication method. Showing a ProgressBar while the task is
     * taking place. A successful authentication will add the user to the Firebase console
     * for this project. @ https://console.firebase.google.com/u/0/project/stride-rpg/
     * @param account GoogleSignInAccount for current user.
     */
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        authTask.setText(R.string.auth_authenticate);

        Log.d(TAG, "firebaseAuthWithGoogle: " + account.getId());

        // Begin credential check through Firebase.
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in is successful, check user that has been signed in.
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            checkUser(user);
                        } else {
                            Log.e(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(AuthActivity.this, "Google Sign In Failed!", Toast.LENGTH_LONG).show();
                            checkUser(null);
                        }
                    }
                });
    }

    /**
     * Sign a user out of the FirebaseAuth and GoogleSignInClient.
     */
    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        checkUser(null);
                    }
                });
    }

    /**
     * Sign a user out of the FirebaseAuth and revoke access to the GoogleSignInClient.
     */
    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        checkUser(null);
                    }
                });
    }

    /**
     * Uses the GoogleSignInClient to get the default Google sign in intent for
     * choosing an account to authenticate with.
     */
    private void signIn() {
        authTask.setText(R.string.auth_signin);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /**
     * Check a FirebaseUser for a null object or not null. A non null user means the user
     * has been successfully authenticated. Otherwise attempt to sign in again.
     * @param user FirebaseUser object.
     */
    private void checkUser(FirebaseUser user) {
        if (user != null) {
            // We now know that the user at least exists as part of the Firebase application.
            // Now check if they already have an entry in the Firebase database as a user.
            // Create a reference to the DatabaseReference at the users parent node.
            final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference(DBKeys.USERS_KEY);

            // Set a listener to check if active user currently exists in the FirebaseDatabase.
            userRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        authTask.setText(R.string.auth_load);
                        G.activePlayer = dataSnapshot.getValue(Player.class);
                    } else {
                        authTask.setText(R.string.auth_gen_new);
                        G.activePlayer = new Player(mAuth.getCurrentUser());
                        FirebaseDBUtil db = new FirebaseDBUtil();
                        db.pushActivePlayer();
                    }
                    authProgressBar.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(AuthActivity.this, NavigationActivity.class));
                    finish();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            signIn();
        }
    }
}