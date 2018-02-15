package stride.com.striderpg.global;


import java.util.Timer;
import java.util.TimerTask;

import stride.com.striderpg.database.FirebaseDBUtil;

/**
 * Class meant to control the Concurrent timer that will Asynchriously update the FirebaseDatabase
 * with any changed Data in the activePlayer Player Object.
 */
public class PushTimer {

    /**
     * Debugging Logger tag.
     */
    private static final String TAG = "PushTimer";

    /**
     * Reference to the Database Utility class for Pushing new data.
     */
    private FirebaseDBUtil db = new FirebaseDBUtil();

    /**
     * Timer to control the timerTask and it's fixed rate execution.
     */
    private Timer timer = new Timer();

    /**
     * TimerTask initialized with a run() method that executes every x seconds.
     */
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            db.pushPlayer(G.activePlayer);
            G.fitnessUtil.readData();
        }
    };

    /**
     * Function to schedule the TimerTask at a fixed rate.
     */
    public void start() {
        timer.scheduleAtFixedRate(timerTask, 0, 10000);
    }
}


