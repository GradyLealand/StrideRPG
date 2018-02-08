package stride.com.striderpg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import stride.com.striderpg.global.Globals;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        TextView playerEmail = findViewById(R.id.playerEmail);
        TextView playerUsername = findViewById(R.id.playerUsername);
        TextView playerUniqueId = findViewById(R.id.playerUniqueId);
        TextView playerLevel = findViewById(R.id.playerLevel);
        TextView playerExp = findViewById(R.id.playerExp);

        playerEmail.setText(String.format("%s%s", getString(R.string.email_label), Globals.activePlayer.getEmail()));
        playerUsername.setText(String.format("%s%s", getString(R.string.username_label), Globals.activePlayer.getUsername()));
        playerUniqueId.setText(String.format("%s%s", getString(R.string.uid_label), Globals.activePlayer.getUniqueId()));
        playerLevel.setText(String.format("%s%s", getString(R.string.level_label), Globals.activePlayer.getLevel()));
        playerExp.setText(String.format("%s%s", getString(R.string.exp_label), Globals.activePlayer.getExperience()));

        System.out.println(Globals.activePlayer.toString());
    }
}
