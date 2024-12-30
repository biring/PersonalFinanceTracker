package common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Singleton class responsible for loading and accessing configuration properties.
 * The configuration is loaded from a properties file, and this class provides methods
 * to retrieve values of different types (String, int, double, Path).
 */
public class Config {

    // Root directory of the project
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("user.dir"));
    // Folder names for app and test configurations
    private static final String APP_FOLDER = "app-resources";
    private static final String TEST_FOLDER = "test-resources";
    // Configuration file names
    private static final String CONFIG_FILE = "config.properties";

    // Error messages used throughout the class
    private static final String ERR_CONFIG_NOT_FOUND = "Unable to find configuration file. ";
    private static final String ERR_CONFIG_LOAD = "Failed to load configuration file. ";
    private static final String ERR_CONFIG_VALUE_PARSE = "Issue when parsing configuration key = ";
    private static final String ERR_CONFIG_KEY_INVALID = "Key cannot be null or empty. ";
    private static final String ERR_CONFIG_VALUE_INVALID = "Value cannot be null or empty. ";

    // Singleton instance of Config class
    private static Config instance;

    // Properties object to hold the configuration key-value pairs
    private final Properties properties = loadProperties();

    // Private constructor to prevent instantiation
    private Config() {}

    /**
     * Returns the singleton instance of the Config class.
     * If the instance does not exist, it creates a new one in a thread-safe manner.
     *
     * @return the singleton instance of Config
     */
    public static Config getInstance() {
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config();
                }
            }
        }
        return instance;
    }

    /**
     * Retrieves an integer value associated with the specified key from the configuration.
     * Throws a RuntimeException if the value cannot be parsed as an integer.
     *
     * @param key the key for the property
     * @return the integer value
     * @throws RuntimeException if the value cannot be parsed as an integer
     */
    public int getInt(String key) {
        int value;
        try {
            // Try to parse the value as an integer
            value = Integer.parseInt(getValue(key));
        } catch (NumberFormatException e) {
            throw new RuntimeException(ERR_CONFIG_VALUE_PARSE + key + e.getMessage());
        }
        return value;
    }

    /**
     * Retrieves a double value associated with the specified key from the configuration.
     * Throws a RuntimeException if the value cannot be parsed as a double.
     *
     * @param key the key for the property
     * @return the double value
     * @throws RuntimeException if the value cannot be parsed as a double
     */
    public double getDouble(String key) {
        double value;
        try {
            // Try to parse the value as a double
            value = Double.parseDouble(getValue(key));
        } catch (NumberFormatException | NullPointerException e) {
            throw new RuntimeException(ERR_CONFIG_VALUE_PARSE + key + e.getMessage());
        }
        return value;
    }

    /**
     * Retrieves a String value associated with the specified key from the configuration.
     *
     * @param key the key for the property
     * @return the String value
     */
    public String getString(String key) {
        return getValue(key);
    }

    /**
     * Retrieves a Path value associated with the specified key from the configuration.
     * Throws a RuntimeException if the value cannot be parsed as a valid path.
     *
     * @param key the key for the property
     * @return the Path value
     * @throws RuntimeException if the value cannot be parsed as a valid path
     */
    public Path getPath(String key) {
        Path path;
        try {
            // Try to parse the value as a Path object
            path = Paths.get(getValue(key));
        } catch (InvalidPathException e) {
            throw new RuntimeException(ERR_CONFIG_VALUE_PARSE + key + e.getMessage());
        }
        return path;
    }

    /**
     * Helper method to retrieve a property value by key and validate it.
     * Throws exceptions if the key or value is invalid.
     *
     * @param key the key for the property
     * @return the value associated with the key
     * @throws IllegalArgumentException if the key or value is invalid
     */
    private String getValue(String key) {
        validateKey(key); // Validate that key is not null or empty
        String value = properties.getProperty(key);
        validateValue(value); // Validate that the value is not null or empty
        return value;
    }

    /**
     * Loads properties from the appropriate configuration file based on the environment.
     * The default file is "config_app.properties", but if a test environment is detected,
     * it will load from "config_test.properties".
     *
     * @return the Properties object loaded with the configuration values
     * @throws RuntimeException if the configuration file cannot be found or loaded
     */
    private Properties loadProperties() {
        Properties properties = new Properties();

        Path configFilePath = getConfigFilePath();
        if(!Files.exists(configFilePath)){
            throw new RuntimeException(ERR_CONFIG_NOT_FOUND);
        }

        try (InputStream input = Files.newInputStream(configFilePath)) {
            properties.load(input); // Load properties from the file
        } catch (IOException e) {
            throw new RuntimeException(ERR_CONFIG_LOAD, e);
        }
        return properties;
    }

    /**
     * Determines the path to the configuration file based on the environment.
     * It constructs the path using the root directory, folder, and resource locations.
     *
     * @return the Path of the configuration file
     * @throws RuntimeException if the path is invalid or cannot be constructed
     */
    private Path getConfigFilePath() {
        boolean isTest = isTestEnvironment(); // Check if the environment is "test"
        String root = PROJECT_ROOT.toString();
        String source = isTest ? TEST_FOLDER : APP_FOLDER; // Select the folder based on the environment

        try {
            // Construct the file path
            return Paths.get(root, source, CONFIG_FILE);
        } catch (InvalidPathException e) {
            throw new RuntimeException(ERR_CONFIG_NOT_FOUND, e); // Handle invalid path exception
        }
    }

    /**
     * Determines if the current environment is a test environment based on a system property.
     *
     * @return true if the environment is a test environment, false otherwise
     */
    private boolean isTestEnvironment() {
        return System.getProperty("test.environment") != null; // Check if the "test.environment" property is set
    }

    /**
     * Validates that the provided key is not null or empty.
     *
     * @param key the key to validate
     * @throws IllegalArgumentException if the key is null or empty
     */
    private void validateKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException(ERR_CONFIG_KEY_INVALID); // Throw exception if key is invalid
        }
    }

    /**
     * Validates that the provided value is not null or empty.
     *
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is null or empty
     */
    private void validateValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(ERR_CONFIG_VALUE_INVALID); // Throw exception if value is invalid
        }
    }
}
