package link.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Defines the test cases for {@link LinkModel} class.
 * The test methods include for constructor and getters, updating entity name and the toString behavior.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LinkModelTest {
    // Unique identifier used for testing.
    private static final int id = 326;

    // Name associated with the entity for testing.
    private static final String name = "Walmart";

    // Updated name for the entity in testing.
    private static final String name_updated = "Target";

    // Category identifier used for testing.
    private static final int category_id = 256;

    // LinkModel instance used in tests.
    private static LinkModel linkModel;

    /**
     * Sets up the testing environment before executing any test cases.
     * An instance of {@link LinkModel} class is initialized here for further usage in test cases.
     */
    @BeforeAll
    public static void setUp() {
        // Initialize LinkModel with predefined constants
        linkModel = new LinkModel(id, name, category_id);
    }

    /**
     * Test case for {@link LinkModel} constructor and getter functions.
     * This test verifies that both the constructor and the getters have been properly implemented.
     */
    @Test
    @Order(1)
    public void testLinkModelConstructorAndGetters() {
        // Assert that constructor correctly initializes and getters return expected values
        assertEquals(id, linkModel.getID(), "Link ID should match");
        assertEquals(name, linkModel.getName(), "Link name should match");
        assertEquals(category_id, linkModel.getCategoryId(), "Initial category id should match");
    }

    /**
     * Test case for updating the name of the entity in the {@link LinkModel} instance.
     * This test verifies that the setName method has been properly implemented.
     */
    @Test
    @Order(2)
    public void testUpdateLinkName() {
        // Update the link name
        linkModel.setName(name_updated);
        // Assert that the new name matches with the updated name
        assertEquals(name_updated, linkModel.getName(), "Link name should be updated");
    }

    /**
     * Test case for the toString method of the {@link LinkModel} class.
     * This test verifies if the toString method has been properly implemented.
     */
    @Test
    @Order(3)
    public void testToString() {
        // Generate the string representation of LinkModel
        String result = linkModel.toString();
        // Define the expected string format
        String expectedString = String.format(linkModel.toStringFormat, id, name_updated, category_id);
        // Assert that the generated string matches the expected one
        assertEquals(expectedString, result, "toString() should return correct string representation");
    }
}