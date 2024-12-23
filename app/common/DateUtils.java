package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * The {@code DateUtils} class contains methods for working with Date objects in the system.
 * * This class is designed as a utility class and cannot be instantiated.
 */

public class DateUtils {

    /**
     * Private constructor prevents instantiation of the Console class.
     */
    private DateUtils() {
        throw new UnsupportedOperationException("Cannot instantiate Console class.");
    }
    /**
     * This method converts a given Date object into a String representation.
     *
     * @param date The Date object that is meant to be formatted.
     * @return Returns a formatted String representation of the date.
     * @throws IllegalArgumentException if the date is null.
     */
    public static String formatDateObjectToString(Date date) {
        // If the date is null, throw an IllegalArgumentException
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null");
        }
        // Define the date pattern
        String pattern = "yyyy.MM.dd";
        // Define time zone
        String timeZone = "UTC";
        // Instantiate a SimpleDateFormat object with the defined pattern
        DateFormat df = new SimpleDateFormat(pattern);
        // Set the time zone to UTC to ensure consistent results
        df.setTimeZone(TimeZone.getTimeZone(timeZone));
        // Use the SimpleDateFormat instance to format the input Date object into a String
        // This String is then returned by the method
        return df.format(date);
    }
}
