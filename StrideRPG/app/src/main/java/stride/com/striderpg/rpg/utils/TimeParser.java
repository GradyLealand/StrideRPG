package stride.com.striderpg.rpg.utils;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;

/**
 * Helper class used to Generate/Parse out Timestamps using the
 * JodaTime Library. All Timestamps are stored and parsed to-><-from
 * a UTC format.
 *
 * Timestamp example: "03-21-2018 02:16:31".
 */
public class TimeParser {

    /**
     * TimeParser Logging tag.
     */
    private static final String TAG = "TimeParser";

    /**
     * Create a DateTimeFormatter for formatting any input/output
     * using the correct format, UTC is format used in application
     * and the Database.
     */
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat
            .forPattern(Constants.ACTIVITY_TIMESTAMP_FORMAT).withZoneUTC();

    /**
     * Get a DateTime object from the current Timestamp minus
     * a specified amount of hours.
     */
    public static DateTime getCurrentTimeMinusHours(Integer hours) {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return now.minusHours(hours);
    }

    /**
     * Get a DateTime object from the current Timestamp plus
     * a specified amount of minutes.
     */
    public static DateTime getCurrentTimePlusMinutes(Integer minutes) {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return now.plusMinutes(minutes);
    }

    /**
     * Get a DateTime object from a specified Timestamp minus
     * a specified amount of hours.
     */
    public static DateTime getDateTimeMinusHours(DateTime date, Integer hours) {
        return date.minusHours(hours);
    }

    /**
     * Converts a String Timestamp to a DateTime object.
     */
    public static DateTime parseTimestamp(String timestamp) {
        return dateTimeFormatter.parseDateTime(timestamp);
    }

    /**
     * Create a String Timestamp for the current DateTime.
     */
    public static String makeTimestamp() {

        DateTime now = new DateTime(DateTimeZone.UTC);
        return now.toString(dateTimeFormatter);
    }

    /**
     * Create a String Timestamp from the DateTime object passed to the method.
     */
    public static String makeTimestamp(DateTime date) {
        return date.toString(dateTimeFormatter);
    }

    /**
     * Gets the difference in long milliseconds between two DateTime objects.
     */
    public static long getDifferenceInMinutes(DateTime start, DateTime end) {

        long diff = end.getMillis() - start.getMillis();
        return (diff / 1000) / 60;
    }

    /**
     * Convert a String Timestamp into a readable String representing the
     * amount of time since the Timestamp passed to the method.
     * Based on the hours/minutes/seconds of the Period between
     * the current DateTime and the Timestamp passed to method
     * DateTime representation.
     */
    public static String toReadable(String timestamp) {

        DateTime now = DateTime.now(DateTimeZone.UTC);
        DateTime activity = parseTimestamp(timestamp);

        Interval interval = new Interval(activity, now);
        Period period = interval.toPeriod();

        // Create local variables to hold each period difference.
        Integer seconds = period.getSeconds();
        Integer minutes = period.getMinutes();
        Integer hours = period.getHours();

        // If statement here to return a readable string representing
        // difference in both DateTimes.
        if (hours == 0 && minutes == 0 && seconds == 0) {
            return "just now.";
        } else if (hours == 0) {
            if (minutes == 0) {
                if (seconds == 1) {
                    return String.format(G.locale, "%d second ago.", seconds);
                } else {
                    return String.format(G.locale, "%d seconds ago.", seconds);
                }
            } else {
                if (minutes == 1) {
                    return String.format(G.locale, "%d minute ago.", minutes);
                } else {
                    return String.format(G.locale, "%d minutes ago.", minutes);
                }
            }
        } else {
            if (hours == 1) {
                return String.format(G.locale, "%d hour ago.", hours);
            } else {
                return String.format(G.locale, "%d hours ago.", hours);
            }
        }
    }
}
