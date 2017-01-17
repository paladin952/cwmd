package application.web.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converter that get's a String and converts it to a specific timestamp
 */
public class TimestampConverter {

    /**
     * Converting a string to a timestamp
     * @param date The time as string
     * @return A new {@link Timestamp}
     * @throws ParseException In case there are parsing error
     */
    public static Timestamp fromString(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = dateFormat.parse(date);
        return new Timestamp(parsedDate.getTime());
    }
}
