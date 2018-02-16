package stride.com.striderpg.rpg.models.Activity;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Activity helper class to parse and convert Activity timestamp to and from Date objects
 * to determine how old an Activity is to deal with sorting and cleaning the HashMap.
 */
public class TimestampParser {

    public Date getCurrentTimeMinusTwelveHours() {
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Date());
        calender.add(Calendar.HOUR_OF_DAY, -12);

        return calender.getTime();
    }

    public Date parseActivityTimestamp(String timestamp) {
        SimpleDateFormat format = new SimpleDateFormat();
        Date timestampAsDate = null;

        try {
            timestampAsDate = format.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timestampAsDate;
    }



}
