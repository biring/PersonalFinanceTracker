package base;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for the BaseDatabase class.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseDatabaseTest {
    private static final String DB_NAME = "testDB";
    private static final String JSON_STRING_1 = "{\"name\":\"CombineTest\",\"value\":123}";
    private static final String JSON_STRING_2 = "{\"name\":\"SeparateTest\",\"value\":999,\"flag\":0}";
    private static Path dbFolder;  // Static folder for all tests
    private BaseDatabase baseDatabase;

    /**
     * This method is executed before all test cases.
     * It ensures that the test database folder is created and clean before tests run.
     */
    @BeforeAll
    public static void setUp() {
        // Use JUnit's provided temporary directory for the test
        try {
            // Create the folder for the database before any tests are run
            dbFolder = Files.createTempDirectory("dbTestFolder");
            // Ensure the folder is empty before any tests are executed
            if (Files.exists(dbFolder)) {
                Files.walk(dbFolder)
                        .sorted((path1, path2) -> path2.compareTo(path1)) // Delete files first then directories
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                System.err.println(e);
                            }
                        });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is executed after all test cases have run.
     * It ensures that the database folder is cleaned up after the tests.
     */
    @AfterAll
    public static void tearDown() {
        try {
            // Delete the folder and all its contents after all tests
            if (Files.exists(dbFolder)) {
                Files.walk(dbFolder)
                        .sorted((path1, path2) -> path2.compareTo(path1)) // Delete files first then directories
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test: Create the initial empty database file.
     */
    @Test
    @Order(1)
    public void testCreateEmptyDatabase() {
        // Ensure the database file does not exist before the test
        Path dbFilePath = dbFolder.resolve(DB_NAME + ".json");
        assertFalse(Files.exists(dbFilePath), "Database file should not exist before creation");
        // Create a new BaseDatabase instance which should create the file if it doesn't exist
        baseDatabase = new BaseDatabase(DB_NAME, dbFolder);
        // Call readFromDatabase() which will create the file if it doesn't exist
        baseDatabase.readFromDatabase();
        // Ensure the file is created after the method call
        assertTrue(Files.exists(dbFilePath), "Database file should be created after reading");
    }

    /**
     * Test: Read from an empty database file after initialization (it should create the file if it doesn't exist).
     */
    @Test
    @Order(2)
    public void testReadEmptyDatabase() {
        // Create the baseDatabase instance, which will also create the empty file
        baseDatabase = new BaseDatabase(DB_NAME, dbFolder);
        // Read from the database (it will create the file if it doesn't exist)
        String content = baseDatabase.readFromDatabase();
        // Verify that the content is empty after reading from an uninitialized (empty) database
        assertEquals("", content, "Database file should be empty initially after creation.");
    }

    /**
     * Test: Write to the database and verify the written content.
     */
    @Test
    @Order(3)
    public void testWriteAndReadDatabase() {
        // Create the baseDatabase instance before writing
        baseDatabase = new BaseDatabase(DB_NAME, dbFolder);
        // Write JSON content to the database
        baseDatabase.writeToDatabase(JSON_STRING_1);
        // Read back from the database and verify the content
        String content = baseDatabase.readFromDatabase();
        assertEquals(JSON_STRING_1, content, "Database content should match the written JSON");
    }

    /**
     * Test: Write content to the database and check file existence.
     */
    @Test
    @Order(4)
    public void testWriteContentToDatabase() {
        // Create the baseDatabase instance before writing
        baseDatabase = new BaseDatabase(DB_NAME, dbFolder);
        // Write JSON content to the database
        baseDatabase.writeToDatabase(JSON_STRING_2);
        // Ensure the file exists after writing
        Path dbFilePath = dbFolder.resolve(DB_NAME + ".json");
        assertTrue(Files.exists(dbFilePath), "Database file should exist after writing content.");
    }

    /**
     * Test: Read from the database and verify the written content.
     */
    @Test
    @Order(5)
    public void testReadAndVerifyDatabaseContent() {
        // Create the baseDatabase instance before reading
        baseDatabase = new BaseDatabase(DB_NAME, dbFolder);
        // Read content from the database
        String content = baseDatabase.readFromDatabase();
        // Verify the content matches the previously written data
        assertEquals(JSON_STRING_2, content, "Database content should match the written JSON.");
    }
}
