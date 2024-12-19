package account.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for {@link AccountModel} class.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountModelTest {

    private static final int number = 9;
    private static final String name = "Original Account Name";
    private static final String name_updated = "Updated Account Name";
    private static final AccountType type = AccountType.CREDIT; // After the type update in previous test


    private static AccountModel account;

    /**
     * Set up the AccountModel before all tests.
     */
    @BeforeAll
    public static void setup() {
        // Arrange: Initialize the AccountModel with initial values.
        account = new AccountModel(number, name, type);
    }

    @Test
    @Order(1)
    public void testAccountModelConstructorAndGetters() {
        // Act: No need to act again as account is set up in @BeforeAll.

        // Assert: Verify the initial values.
        assertEquals(number, account.getID(), "Account ID should match");
        assertEquals(name, account.getName(), "Account name should match");
        assertEquals(type, account.getAccountType(), "Account type should match");
    }

    @Test
    @Order(2)
    public void testUpdateAccountName() {
        // Act: Update the account name.
        account.setName(name_updated);

        // Assert: Verify that the name was updated.
        assertEquals(name_updated, account.getName(), "Account name should be updated");
    }

    @Test
    @Order(3)
    public void testToString() {
        // Act
        String result = account.toString();

        // Assert: Check the string representation.
        String expectedString = String.format(account.toStringFormat, number, name_updated, type);
        assertEquals(expectedString, result, "toString() should return correct string representation");
    }

    /**
     * Test the getDbName method to ensure the correct file name is generated for AccountModel.
     */
    @Test
    @Order(4)
    public void testGetDbName() {
        // Act: Get the database file name from the AccountModel instance
        String dbName = account.getDbName();

        // Assert: Check that the database name matches the expected format.
        assertEquals("accountmodel.json", dbName, "The DB name for AccountModel should be 'account.json'");
    }
}
