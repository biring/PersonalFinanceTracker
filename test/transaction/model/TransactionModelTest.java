package transaction.model;

import common.DateUtils;
import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Defines the test cases for {@link TransactionModel} class.
 * The test methods validate the constructor, getters, update operation for the entity's name,
 * and the toString behavior of this class.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionModelTest {

    // Unique identifier used for testing.
    private static final int id = 8957;

    // Name associated with the entity for testing.
    private static final String name = "AirBNB";

    // Updated name for the entity in testing.
    private static final String name_updated = "Hyatt";

    // Date associated with the transaction for testing.
    private static final long fixedTime = 1633046400000L; // time representing 1st October 2021
    private static final Date fixedDate = new Date(fixedTime);

    // Amount associated with the transaction for testing.
    private static final double amount = 2356.78;

    // Account identifier used for testing.
    private static final int accountId = 256;

    // TransactionModel instance used in tests.
    // This instance will be initialized once before all test cases and used in each of them.
    private static TransactionModel transactionModel;

    /**
     * Sets up the testing environment before executing any test cases.
     * An instance of {@link TransactionModel} class is initialized here for further usage in test cases.
     */
    @BeforeAll
    public static void setUp() {
        transactionModel = new TransactionModel(id, fixedDate, name, amount, accountId);
    }

    /**
     * Test case for {@link TransactionModel} constructor & getter methods.
     * This test verifies that the constructor and the getters are properly implemented.
     * This is done by comparing the values retrieved from getters and the values set initially.
     */
    @Test
    @Order(1)
    public void testTransactionModelConstructorAndGetters() {
        assertEquals(id, transactionModel.getID(), "Transaction ID should match");
        assertEquals(name, transactionModel.getName(), "Transaction name should match");
        assertEquals(fixedDate, transactionModel.getDate(), "Transaction date should match");
        assertEquals(amount, transactionModel.getAmount(), "Transaction amount should match");
        assertEquals(accountId, transactionModel.getAccountId(), "Transaction account id should match");
    }

    /**
     * Test case to update the name of the entity in the {@link TransactionModel} instance.
     * This test verifies if the setName method properly updates the name of the entity.
     * This is done by setting a new name using setName and then fetching the updated name using getName method.
     */
    @Test
    @Order(2)
    public void testUpdateTransactionName() {
        transactionModel.setName(name_updated);
        assertEquals(name_updated, transactionModel.getName(), "Transaction name should be updated");
    }

    /**
     * Test case for the toString method of the {@link TransactionModel} class.
     * This test verifies if the toString method correctly generates a string representation of the instance.
     */
    @Test
    @Order(3)
    public void testToString() {
        // Generate the string representation for transaction model
        String result = transactionModel.toString();
        // Format the date similar to how it's formatted in the toString() method
        String formattedFixedDate = DateUtils.formatDateObjectToString(fixedDate);
        // Define the expected string format
        String expectedString = String.format(transactionModel.toStringFormat, id, formattedFixedDate, name_updated, amount, accountId);
        // Assert that the generated string matches the expected string
        assertEquals(expectedString, result, "toString() should return correct string representation");
    }
}