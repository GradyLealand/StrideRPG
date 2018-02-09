package stride.com.striderpg;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import stride.com.striderpg.database.DBKeys;
import stride.com.striderpg.database.FirebaseDBUtil;
import stride.com.striderpg.global.Globals;
import stride.com.striderpg.models.Item;

public class MainActivity extends AppCompatActivity {

    private FirebaseDBUtil db = new FirebaseDBUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.activePlayerString);
        Button skillButton1 = findViewById(R.id.testSkill1);
        Button skillButton2 = findViewById(R.id.testSkill2);
        Button testPushItem = findViewById(R.id.testPushItem);

        skillButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.activePlayer.getSkills().setStrength(
                        Globals.activePlayer.getSkills().getStrength() + 1
                );
                db.pushPlayerSkill(
                        DBKeys.SKILLS_STRENGTH, Globals.activePlayer.getSkills().getStrength()
                );
            }
        });

        skillButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.activePlayer.getSkills().setStrength(
                        Globals.activePlayer.getSkills().getStrength() - 1
                );
                db.pushPlayerSkill(
                        DBKeys.SKILLS_STRENGTH, Globals.activePlayer.getSkills().getStrength()
                );
            }
        });

        testPushItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.pushItem(new Item(
                        "test item",
                        "description test",
                        14,
                        4,
                        6
                        ,8
                ));
            }
        });

        System.out.println(Globals.activePlayer.toString());
        tv.setText(Globals.activePlayer.toString());
    }
}
