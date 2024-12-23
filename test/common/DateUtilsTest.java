package common;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the DateUtils class containing various test cases to ensure the functionality of date formatting methods.
 */
class DateUtilsTest {

    /**
     * Test case to check if the method correctly formats a valid Date object.
     */
    @Test
    void testFormatDateObjectToString_validDate() {
        // Arrange
        Date date = new Date(1234567890123L); // Example timestamp (random timestamp for testing)
        String expected = "2009.02.13"; // This is the expected formatted date for the above timestamp

        // Act
        String actual = DateUtils.formatDateObjectToString(date);

        // Assert
        assertEquals(expected, actual, "The date was not formatted correctly.");
    }

    /**
     * Test case to check if the method handles null input properly.
     */
    @Test
    void testFormatDateObjectToString_nullDate() {
        // Act & Assert: Expecting IllegalArgumentException when the date is null
        assertThrows(IllegalArgumentException.class, () -> {
            DateUtils.formatDateObjectToString(null);
        });
    }

    /**
     * Test case to check the method when the date is at the Unix epoch.
     */
    @Test
    void testFormatDateObjectToString_epochDate() {
        // Arrange
        Date date = new Date(0L); // Unix epoch time (1970-01-01 00:00:00 UTC)
        String expected = "1970.01.01"; // The expected formatted date for the Unix epoch time

        // Act
        String actual = DateUtils.formatDateObjectToString(date);

        // Assert
        assertEquals(expected, actual, "The Unix epoch date was not formatted correctly.");
    }

    /**
     * Test case to check if the method handles edge case for February 29th on a leap year.
     */
    @Test
    void testFormatDateObjectToString_leapYear() {
        // Arrange
        Date date = new Date(1582934400000L); // February 29, 2020 (Leap Year)
        String expected = "2020.02.29"; // Expected formatted date

        // Act
        String actual = DateUtils.formatDateObjectToString(date);

        // Assert
        assertEquals(expected, actual, "The leap year date was not formatted correctly.");
    }

    /**
     * Test case to check if the method handles a very large timestamp.
     */
    @Test
    void testFormatDateObjectToString_largeTimestamp() {
        // Arrange
        long timestamp = 4096358400000L;  // Timestamp for October 22, 2099
        Date date = new Date(timestamp);  // Create a Date object from the timestamp
        String expected = "2099.10.22";   // Expected formatted date

        // Act
        String actual = DateUtils.formatDateObjectToString(date);

        // Assert
        assertEquals(expected, actual, "The large timestamp date was not formatted correctly.");
    }
}
