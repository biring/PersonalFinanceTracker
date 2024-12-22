package category.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The CategoryModelTest class is responsible for testing the functionalities of the CategoryModel class.
 * It includes test methods for the constructor, getters, setters, and the string representation of the CategoryModel.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryModelTest {

    /**
     * Represents a unique identifier for a certain entity.
     */
    private static final int id = 231;
    /**
     * Represents the name associated with a certain entity or category.
     */
    private static final String name = "Travel";
    /**
     * Represents the updated name for a certain entity.
     */
    private static final String name_updated = "Fly";
    /**
     * Represents the updated budget amount for tracking purposes.
     */
    private static final int budget_updated = 500;
    /**
     * Represents a static CategoryModel that holds information about a category for budget tracking.
     */
    private static CategoryModel categoryModel;

    /**
     * Initializes the setup for the testing environment before running any tests.
     * It creates an instance of CategoryModel with initial values to be used in tests.
     */
    @BeforeAll
    public static void setUp() {
        // Initialize the CategoryModel with initial values.
        categoryModel = new CategoryModel(id, name);
    }

    /**
     * Test method for CategoryModel constructor and getters.
     * The test verifies that the CategoryModel constructor correctly initializes the ID, name, and budget fields,
     * and that the getters return the expected values.
     * No need to take additional actions, as the account setup is done in @BeforeAll.
     * Asserts the initial values of ID, name, and budget against the expected values.
     */
    @Test
    @Order(1)
    public void testCategoryModelConstructorAndGetters() {
        // Act: No need to act again as account is set up in @BeforeAll.

        // Assert: Verify the initial values.
        assertEquals(id, categoryModel.getID(), "Category ID should match");
        assertEquals(name, categoryModel.getName(), "Category name should match");
        assertEquals(CategoryModel.NO_BUDGET, categoryModel.getCategoryBudget(), "Initial category budget should be equal to NO_BUDGET constant");
    }

    /**
     * Test method for updating the category name in the CategoryModel instance.
     * <p>
     * This test method is responsible for verifying that the {@link CategoryModel} instance can have its name updated successfully.
     * It first sets a new name for the category, then checks whether the name was updated correctly.
     * </p>
     */
    @Test
    @Order(2)
    public void testUpdateCategoryName() {
        // Act: Update the category name.
        categoryModel.setName(name_updated);

        // Assert: Verify that the name was updated.
        assertEquals(name_updated, categoryModel.getName(), "Category name should be updated");
    }

    /**
     * Test method for updating the category budget in the CategoryModel instance.
     * <p>
     * This test method is responsible for verifying that the budget of a {@link CategoryModel} instance can be updated successfully.
     * It updates the category budget to a new value and then checks if the update was applied correctly by retrieving the updated budget.
     * </p>
     */
    @Test
    @Order(3)
    public void testUpdateCategoryBudget() {
        // Update the category budget.
        categoryModel.setCategoryBudget(budget_updated);

        // Verify that the budget was updated.
        assertEquals(budget_updated, categoryModel.getCategoryBudget(), "Category budget should be updated");
    }

    /**
     * Test case for verifying the toString() method behavior in CategoryModelTest.
     * Checks if the generated string representation matches the expected format containing category ID, name, and budget.
     * The test ensures that the toString() method returns the correct string representation.
     */
    @Test
    @Order(4)
    public void testToString() {
        String result = categoryModel.toString();
        String expectedString = String.format(categoryModel.toStringFormat, id, name_updated, budget_updated);
        assertEquals(expectedString, result, "toString() should return correct string representation");
    }
}