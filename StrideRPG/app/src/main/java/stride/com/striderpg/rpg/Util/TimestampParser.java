package stride.com.striderpg.rpg.Util;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;

/**
 * Activity helper class to parse and convert Activity timestamp to and from Date objects
 * to determine how old an Activity is to deal with sorting and cleaning the HashMap.
 */
public class TimestampParser {

    /**
     * TimestampParser Logging tag.
     */
    private static final String TAG = "TimestampParser";

    /**
     * Retrieve the current Date object minus 12 hours for cleaning up a Players History log.
     * @return Date() object - 12 hours.
     */
    public static Date getCurrentTimeMinusTwelveHours() {
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Date());
        calender.add(Calendar.HOUR_OF_DAY, -12);

        Log.d(TAG, String.format(G.locale,
                "getCurrentTimeMinusTwelveHours:success:time=%s", calender.getTime().toString())
        );
        return calender.getTime();
    }

    /**
     * Parse out a Timestamp string from an Activity key into it's proper Date object.
     * @param timestamp String being converted into a Date.
     * @return new Date object from timestamp.
     */
    public static Date parseTimestamp(String timestamp) {
        SimpleDateFormat format = new SimpleDateFormat(Constants.ACTIVITY_TIMESTAMP_FORMAT, G.locale);
        Date timestampAsDate = null;

        try {
            timestampAsDate = format.parse(timestamp);
        } catch (ParseException e) {
            Log.e(TAG, "parseTimestamp:error:", e);
        }

        Log.d(TAG, String.format(G.locale, "parseTimestamp:success:timestamp=%s", timestampAsDate));
        return timestampAsDate;
    }

    /**
     * Generate a String timestamp using the constant ACTIVITY_TIMESTAMP_FORMAT.
     * @return String current Date timestamp.
     */
    public static String makeTimestamp() {
        SimpleDateFormat format = new SimpleDateFormat(Constants.ACTIVITY_TIMESTAMP_FORMAT, G.locale);
        String timestamp = format.format(new Date());

        Log.d(TAG, String.format(G.locale, "makeTimestamp:success:timestamp=%s", timestamp));
        return timestamp;
    }
}
