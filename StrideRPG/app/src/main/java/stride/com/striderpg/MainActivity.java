package stride.com.striderpg;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import stride.com.striderpg.global.Globals;
import stride.com.striderpg.global.PushTimer;
import stride.com.striderpg.rpg.Generators.EnemyGenerator;
import stride.com.striderpg.rpg.Generators.ItemGenerator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.activePlayerString);
        Button skillButton1 = findViewById(R.id.testSkill1);
        Button skillButton2 = findViewById(R.id.testSkill2);
        Button testPushItem = findViewById(R.id.testPushItem);

        // Testing the PushTimer class for every x seconds, execute a command of some sort.
        // In this case, the active player is pushed to the Database every 10 seconds.
        PushTimer push = new PushTimer();
        push.start();

        // Test enemy generation.
        EnemyGenerator gen = new EnemyGenerator();
        System.out.println(gen.generate(Globals.activePlayer).toString());
        System.out.println(gen.generate(Globals.activePlayer).toString());
        System.out.println(gen.generate(Globals.activePlayer).toString());

        // Test adding skill point to strength.
        skillButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.activePlayer.getSkills().setStrength(
                        Globals.activePlayer.getSkills().getStrength() + 1
                );
            }
        });

        // Test removing skill point from strength.
        skillButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.activePlayer.getSkills().setStrength(
                        Globals.activePlayer.getSkills().getStrength() - 1
                );
            }
        });

        // Test adding new default item to inventory.
        testPushItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemGenerator gen = new ItemGenerator();
                // Put new item into players inventory with unique identifier.
                Globals.activePlayer.getInventory().getItems().put(
                        Globals.activePlayer.getInventory().makeKey(),
                        gen.generate(Globals.activePlayer)
                );
            }
        });

        System.out.println(Globals.activePlayer.toString());
        tv.setText(Globals.activePlayer.toString());
    }
}