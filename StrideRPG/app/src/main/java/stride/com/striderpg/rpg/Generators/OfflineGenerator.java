package stride.com.striderpg.rpg.Generators;

import android.util.Log;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Util.TimestampParser;

/**
 * RPG Generator class to calculate a Players progression while they are offline.
 */
public class OfflineGenerator {

    /**
     * OfflineGenerator Logging tag.
     */
    private static final String TAG = "OfflineGenerator";

    /**
     * Calculate a Players offline activities by looking at their last signed in date
     * and determining how many activities will take place for them.
     */
    public static void calculateOfflineActivities() {
        // Create date objects to hold the old date and the new current date.
        Date old = TimestampParser.parseTimestamp(G.activePlayer.getLastSignedIn());
        Date now = TimestampParser.parseTimestamp(TimestampParser.makeTimestamp());

        // Determine how many events, if any, will be generated.
        long diff = now.getTime() - old.getTime();

        // Get the difference in minutes.
        long diffMinutes = (diff / 1000) / 60;

        Log.d(TAG, String.format(G.locale, "calculateOfflineActivities:success:diffMinutes=%d", diffMinutes));
    }
}
