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
 * Activity helper class to parse and convert Activity timestamp to and from Date objects
 * to determine how old an Activity is to deal with sorting and cleaning the HashMap.
 */
public class TimeParser {

    /**
     * TimeParser Logging tag.
     */
    private static final String TAG = "TimeParser";

    /**
     * Create a DateTimeFormatter for parsing DateTimes into proper Strings.
     */
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat
            .forPattern(Constants.ACTIVITY_TIMESTAMP_FORMAT).withZoneUTC();


    /**
     * Retrieve the current DateTime and subtract specified hours.
     * @return DateTime - threshold constant.
     */
    public static DateTime getCurrentTimeMinusHours(Integer hours) {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return now.minusHours(hours);
    }

    /**
     * Take the given DateTime and subtract specified hours.
     * @param date DateTime object.
     * @param hours Hours to subtract.
     * @return DateTime object.
     */
    public static DateTime getDateTimeMinusHours(DateTime date, Integer hours) {
        return date.minusHours(hours);
    }

    /**
     * Parse out a Timestamp string from an Activity key into it's proper Date object.
     * @param timestamp String being converted into a Date.
     * @return new Date object from timestamp.
     */
    public static DateTime parseTimestamp(String timestamp) {
        return dateTimeFormatter.parseDateTime(timestamp);
    }

    /**
     * Generate a String timestamp using the constant ACTIVITY_TIMESTAMP_FORMAT.
     * @return String current Date timestamp.
     */
    public static String makeTimestamp() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return now.toString(dateTimeFormatter);
    }

    /**
     * Generate a String timestamp using the constant ACTIVITY_TIMESTAMP_FORMAT.
     * using a Date object as the Date being converted into a Timestamp string.
     * @param date Date being converted to Timestamp.
     * @return String Timestamp.
     */
    public static String makeTimestamp(DateTime date) {
        return date.toString(dateTimeFormatter);
    }

    /**
     * Get a long representing minutes difference
     * between two different DateTimes.
     * @param start Start DateTime.
     * @param end End DateTime.
     * @return Difference in minutes between two DateTimes.
     */
    public static long getDifferenceInMinutes(DateTime start, DateTime end) {
        long diff = end.getMillis() - start.getMillis();
        return (diff / 1000) / 60;
    }

    /**
     * Convert a Timestamp into a readable human friendly
     * @param timestamp Timestamp being converted to readable string.
     * @return Human friendly timestamp.
     */
    public static String toReadable(String timestamp) {
        DateTime now = DateTime.now(DateTimeZone.UTC);
        DateTime activity = parseTimestamp(timestamp);

        Interval interval = new Interval(activity, now);
        Period period = interval.toPeriod();

        Integer seconds = period.getSeconds();
        Integer minutes = period.getMinutes();
        Integer hours = period.getHours();

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
