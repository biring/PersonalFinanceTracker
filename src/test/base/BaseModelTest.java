package base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the BaseModel class.
 * This class contains unit tests for the functionality of the BaseModel class.
 */
public class BaseModelTest {

    private BaseModel baseModel;

    /**
     * Set up method that runs before each test.
     * It initializes the BaseModel instance with test values.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the BaseModel instance with id=1 and name="Test Name"
        baseModel = new BaseModel(1, "Test Name");
    }

    /**
     * Test the constructor and getID method to verify the ID is set correctly.
     */
    @Test
    public void testGetID() {
        // Test that the ID is set correctly and cannot be changed (final field).
        assertEquals(1, baseModel.getID(), "The ID should be 1");
    }

    /**
     * Test the getName method to verify the name is set correctly.
     */
    @Test
    public void testGetName() {
        // Test that the name is set correctly during construction.
        assertEquals("Test Name", baseModel.getName(), "The name should be 'Test Name'");
    }

    /**
     * Test the setName method to ensure the name can be updated.
     */
    @Test
    public void testSetName() {
        // Change the name using the setter method.
        baseModel.setName("Updated Name");

        // Verify the name has been updated.
        assertEquals("Updated Name", baseModel.getName(), "The name should be updated to 'Updated Name'");
    }
}
