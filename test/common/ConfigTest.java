package common;

import org.junit.jupiter.api.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Config class.
 * These tests ensure that the Config class correctly loads properties from the config_test.properties file.
 */
class ConfigTest {

    /**
     * This method is executed before all tests.
     * It sets the system property to simulate a test environment.
     */
    @BeforeAll
    static void setUpBeforeClass() {
        // Set the system property for the test environment
        System.setProperty("test.environment", "true");
    }

    /**
     * This method is executed after all tests.
     * It removes the system property to clean up.
     */
    @AfterAll
    static void tearDownAfterClass() {
        // Remove the system property to clean up
        System.clearProperty("test.environment");
    }

    /**
     * Test that the getInt() method returns the correct integer value.
     *
     * Arrange: Initialize the system property to simulate a test environment.
     * Act: Retrieve the integer value from the properties file using the Config class.
     * Assert: Check that the retrieved value matches the expected value.
     */
    @Test
    void testGetInt() {
        // Arrange
        Config config = Config.getInstance();
        String key = "test.intValue";
        int expectedValue = 10;

        // Act
        int actualValue = config.getInt(key);

        // Assert
        assertEquals(expectedValue, actualValue, "The integer value should be 10.");
    }

    /**
     * Test that the getDouble() method returns the correct double value.
     *
     * Arrange: Initialize the system property to simulate a test environment.
     * Act: Retrieve the double value from the properties file using the Config class.
     * Assert: Check that the retrieved value matches the expected value.
     */
    @Test
    void testGetDouble() {
        // Arrange
        Config config = Config.getInstance();
        String key = "test.doubleValue";
        double expectedValue = 3.14;

        // Act
        double actualValue = config.getDouble(key);

        // Assert
        assertEquals(expectedValue, actualValue, "The double value should be 3.14.");
    }

    /**
     * Test that the getString() method returns the correct String value.
     *
     * Arrange: Initialize the system property to simulate a test environment.
     * Act: Retrieve the string value from the properties file using the Config class.
     * Assert: Check that the retrieved value matches the expected value.
     */
    @Test
    void testGetString() {
        // Arrange
        Config config = Config.getInstance();
        String key = "test.stringValue";
        String expectedValue = "Test String";

        // Act
        String actualValue = config.getString(key);

        // Assert
        assertEquals(expectedValue, actualValue, "The string value should be 'Test String'.");
    }

    /**
     * Test that the getPath() method returns the correct Path value.
     *
     * Arrange: Initialize the system property to simulate a test environment.
     * Act: Retrieve the path value from the properties file using the Config class.
     * Assert: Check that the retrieved value matches the expected path.
     */
    @Test
    void testGetPath() {
        // Arrange
        Config config = Config.getInstance();
        String key = "test.pathValue";
        Path expectedPath = Paths.get("src/test/resources");

        // Act
        Path actualPath = config.getPath(key);

        // Assert
        assertEquals(expectedPath, actualPath, "The path should be 'src/test/resources'.");
    }

    /**
     * Test that getValue() throws an exception if the key does not exist.
     *
     * Arrange: Prepare a non-existent key.
     * Act: Attempt to retrieve a value using a non-existent key.
     * Assert: Ensure that an exception is thrown.
     */
    @Test
    void testGetValueInvalidKey() {
        // Arrange
        Config config = Config.getInstance();
        String invalidKey = "non.existent.key";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> config.getString(invalidKey),
                "An exception should be thrown for invalid keys.");
    }

    /**
     * Test that getValue() throws an exception if the value is empty.
     *
     * Arrange: Prepare a key with an empty value in the properties file.
     * Act: Attempt to retrieve a value for the key with an empty value.
     * Assert: Ensure that an exception is thrown.
     */
    @Test
    void testGetValueEmpty() {
        // Arrange
        Config config = Config.getInstance();
        String key = "test.emptyValue";  // This key has an empty value in config_test.properties

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> config.getString(key),
                "An exception should be thrown for empty values.");
    }

}
