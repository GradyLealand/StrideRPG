package stride.com.striderpg.rpg.models.Activity;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import stride.com.striderpg.rpg.Constants;

/**
 * Activity helper class to parse and convert Activity timestamp to and from Date objects
 * to determine how old an Activity is to deal with sorting and cleaning the HashMap.
 */
public class TimestampParser {

    /**
     * Retrieve the current Date object minus 12 hours for cleaning up a Players History log.
     * @return Date() object - 12 hours.
     */
    public static Date getCurrentTimeMinusTwelveHours() {
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Date());
        calender.add(Calendar.HOUR_OF_DAY, -12);

        return calender.getTime();
    }

    /**
     * Parse out a Timestamp string from an Activity key into it's proper Date object.
     * @param timestamp String being converted into a Date.
     * @return new Date object from timestamp.
     */
    public static Date parseActivityTimestamp(String timestamp) {
        SimpleDateFormat format = new SimpleDateFormat(Constants.ACTIVITY_TIMESTAMP_FORMAT);
        Date timestampAsDate = null;

        try {
            timestampAsDate = format.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timestampAsDate;
    }

    /**
     * Generate a String timestamp using the constant ACTIVITY_TIMESTAMP_FORMAT.
     * @return String current Date timestamp.
     */
    public static String makeTimestamp() {
        SimpleDateFormat format = new SimpleDateFormat(Constants.ACTIVITY_TIMESTAMP_FORMAT);
        return format.format(new Date());
    }

    /**
     * Generate a String timestamp using the constant ACTIVITY_TIMESTAMP_FORMAT.
     * @return String current Date timestamp.
     */
    public static String make12HourOldTimestamp() {
        SimpleDateFormat format = new SimpleDateFormat(Constants.ACTIVITY_TIMESTAMP_FORMAT);
        return format.format(getCurrentTimeMinusTwelveHours());
    }



}
