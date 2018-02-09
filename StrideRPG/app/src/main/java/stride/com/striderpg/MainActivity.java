package stride.com.striderpg;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import stride.com.striderpg.database.FirebaseDBUtil;
import stride.com.striderpg.global.Globals;
import stride.com.striderpg.models.Item;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDBUtil db = new FirebaseDBUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        TextView tv = findViewById(R.id.activePlayerString);

        System.out.println(Globals.activePlayer.toString());
        tv.setText(Globals.activePlayer.toString());
    }
}
