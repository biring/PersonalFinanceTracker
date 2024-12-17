package base;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * This class provides functionality to interact with a JSON-based database file.
 * It handles reading from and writing to the database, ensuring that the necessary
 * directories and files are created as needed.
 */
public class BaseDatabase {

    private final String dBName;
    private final Path dbFilePath;

    /**
     * Constructs a BaseDatabase object with a given database name and folder.
     *
     * @param dbName  The name of the database (used as the filename).
     * @param dbFolder The folder where the database file should be located.
     *                 If null or empty, the folder is read from the properties file.
     *
     * @throws RuntimeException If the folder or file cannot be created or accessed.
     */
    public BaseDatabase(String dbName, Path dbFolder){
        this.dBName = dbName;
        // get directory
        dbFolder = getDbFolder(dbFolder);
        // create database directory if missing
        try {
            ensureDirectoryExists(dbFolder);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create database folder at " + dbFolder, e);
        }
        // get file
        this.dbFilePath = getDbFilePath(dbFolder);
        // create database file if missing
        try{
            ensureFileExists(this.dbFilePath);
        } catch (IOException e){
            throw new RuntimeException("Failed to create database file: " + this.dbFilePath, e);
        }

    }

    /**
     * Reads the content of the database file and returns it as a JSON string.
     *
     * @return The content of the database file as a JSON string.
     * @throws RuntimeException If reading the file fails.
     */
    public String readFromDatabase() {
        try {
            // Read the database file content as JSON string using the private method
            return readDbFile(this.dbFilePath);
        } catch (IOException e) {
            // Wrap IOException in RuntimeException to avoid forcing callers to handle it
            throw new RuntimeException("Failed to read from the database file: " + this.dBName, e);
        }
    }

    /**
     * Writes a JSON string to the database file.
     *
     * @param jsonContent The JSON string to write to the database file.
     * @throws RuntimeException If writing to the file fails.
     */
    public void writeToDatabase(String jsonContent) {
        try {
            // Write JSON string to the database file
            writeDbFile(this.dbFilePath, jsonContent);
        } catch (IOException e) {
            // Wrap IOException in RuntimeException to avoid forcing callers to handle it
            throw new RuntimeException("Failed to write to the database file: " + this.dBName, e);
        }
    }

    /**
     * Reads the database file content as a JSON string.
     *
     * @param dBFilePath The path to the database file.
     * @return The content of the database file as a string.
     * @throws IOException If reading the file fails.
     */
    private String readDbFile(Path dBFilePath) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(dBFilePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            // Directly throw the IOException here to let the caller decide what to do with it
            throw new IOException("Error reading from the database file: " + this.dBName + "\nMessage: " + e.getMessage(), e);
        }
        return sb.toString(); // Return the JSON content as a string
    }

    /**
     * Writes the given JSON string to the database file.
     *
     * @param dBFilePath The path to the database file.
     * @param jsonContent The JSON string to write to the file.
     * @throws IOException If writing to the file fails.
     */
    private void writeDbFile(Path dBFilePath, String jsonContent) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(dBFilePath)) {
            writer.write(jsonContent); // Write the content to the file
        } catch (IOException e) {
            // Directly throw the IOException here to let the caller decide what to do with it
            throw new IOException("Error writing to the database file: " + this.dBName + "\nMessage: " + e.getMessage(), e);
        }
    }

    /**
     * Returns the database folder. If the provided folder is null or empty,
     * it loads the folder path from the configuration properties file.
     *
     * @param folder The folder path provided by the user.
     * @return The path to the database folder.
     * @throws RuntimeException If the properties file cannot be loaded.
     */
    private Path getDbFolder(Path folder) {
        if (folder != null && !folder.toString().isEmpty()) {
            return folder;  // If folder is provided and non-empty, return it. This is done for unit test
        }

        // If folder is null or empty, load folder from properties. This is used at runtime
        Properties properties = new Properties();
        Path dbFolder;

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Unable to find config.properties file");
            }
            properties.load(input);

            // Try to get the 'dbFolder' property, if it's missing throw an exception
            dbFolder = Paths.get(properties.getProperty("dbFolder"));

        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }

        return dbFolder;
    }

    /**
     * Returns the file path for the database file.
     *
     * @param dbFolder The folder where the database is stored.
     * @return The path to the database file.
     */
    private Path getDbFilePath(Path dbFolder) {
        return dbFolder.resolve(this.dBName + ".json");
    }

    /**
     * Ensures that the specified directory exists. If it does not exist,
     * the directory is created.
     *
     * @param directoryPath The path of the directory to check and create.
     * @throws IOException If an I/O error occurs while creating the directory.
     */
    private void ensureDirectoryExists(Path directoryPath) throws IOException {
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath); // Create parent directories if they don't exist
            } catch (IOException e) {
                throw new IOException("Error creating directory: " + directoryPath, e);
            }
        }
    }

    /**
     * Ensures that the specified file exists. If the file does not exist,
     * an empty file is created.
     *
     * @param filePath The path of the file to check and create.
     * @throws IOException If an I/O error occurs while creating the file.
     */
    private void ensureFileExists(Path filePath) throws IOException {
        if (Files.exists(filePath)) {
            if (Files.isDirectory(filePath)) {
                throw new IOException("A directory already exists at the path: " + filePath);
            }
            // File already exists, no action needed
        } else {
            try {
                Files.createFile(filePath); // Create the file
            } catch (IOException e) {
                throw new IOException("Error creating file: " + filePath, e);
            }
        }
    }
}
