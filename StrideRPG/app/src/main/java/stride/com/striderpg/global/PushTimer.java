package stride.com.striderpg.global;


import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import stride.com.striderpg.database.FirebaseDBUtil;
import stride.com.striderpg.rpg.Constants;

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
     * Timer to control the databasePusher and it's execution.
     */
    private Timer databasePusherTimer = new Timer();

    /**
     * Timer to control the fitnessPusher and it's rate of execution.
     */
    private Timer fitnessPusherTimer = new Timer();

    /**
     * TimerTask for pushing the active player directly to the database.
     */
    private TimerTask databasePusher = new TimerTask() {
        @Override
        public void run() {
            try {
                db.pushPlayer(G.activePlayer);
                Log.d(TAG, "pushPlayer:success");
            } catch (Exception e) {
                Log.e(TAG, "databasePusher:pushPlayer:error", e);
            }
        }
    };

    /**
     * TimerTask for polling the fitnessUtil class for current steps information.
     */
    private TimerTask fitnessPusher = new TimerTask() {
        @Override
        public void run() {
            try {
                G.fitnessUtil.readData();
                Log.d(TAG, "readData:success");
            } catch (Exception e) {
                Log.e(TAG, "fitnessPusher:readData:error", e);
            }
        }
    };

    /**
     * Begin the execution of both TimerTasks.
     */
    public void startTimers() {
        startDatabasePusher();
        startFitnessPusher();
    }

    /**
     * Schedule the databasePusher TimerTask at a fixed rate.
     */
    private void startDatabasePusher() {
        databasePusherTimer.scheduleAtFixedRate(
                databasePusher,
                0,
                Constants.DATABASE_PUSH_RATE);
    }

    /**
     * Schedule the fitnessPusher TimerTask at a fixed rate.
     */
    private void startFitnessPusher() { fitnessPusherTimer.scheduleAtFixedRate(fitnessPusher,
            0,
            Constants.FITNESS_READ_RATE);

    }
}


