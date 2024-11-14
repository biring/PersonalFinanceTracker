package view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseClassTest {

    @Test
    public void testDisplayMenu() {
        // Arrange
        Scanner mockScanner = new Scanner(System.in);
        BaseClass baseView = new BaseClass(mockScanner) {
        };  // Anonymous class to instantiate BaseView
        String[] menuOptions = {"Option 1", "Option 2", "Option 3"};

        // Capture the console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        baseView.displayMenu(menuOptions);

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Option 1"));
        assertTrue(output.contains("Option 2"));
        assertTrue(output.contains("Option 3"));

        // Restore original System.out
        System.setOut(originalSystemOut);
    }

    @Test
    public void testPromptForMenuSelection_ValidInput() {
        // Arrange
        String simulatedUserInput = "2\n";  // Simulating user input '2'
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        BaseClass baseView = new BaseClass(mockScanner) {
        };  // Anonymous class to instantiate BaseView
        String message = "Please select an option:";
        String[] menuOptions = {"Option 1", "Option 2", "Option 3"};
        baseView.displayMenu(menuOptions);

        // Act
        int selection = baseView.promptForMenuSelection(message);

        // Assert
        assertEquals(2, selection);  // Expecting '2' as the selection
    }

    @Test
    public void testPromptForMenuSelection_InvalidInput() {
        // Arrange
        String simulatedUserInput = "5\n2\n";  // Simulating invalid '5' followed by valid '2'
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        BaseClass baseView = new BaseClass(mockScanner) {
        };  // Anonymous class to instantiate BaseView
        String message = "Please select an option:";
        String[] menuOptions = {"Option 1", "Option 2", "Option 3"};
        baseView.displayMenu(menuOptions);

        // Act
        int selection = baseView.promptForMenuSelection(message);

        // Assert
        assertEquals(2, selection);  // Expecting '2' as the selection after the invalid input
    }

    @Test
    public void testPrintMessage() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        BaseClass baseView = new BaseClass(new Scanner(System.in)) {
        };  // Anonymous subclass to access protected method
        String message = "Hello, World!";

        // Act
        baseView.printMessage(message);

        // Assert
        String output = outputStream.toString();
        assertEquals(message, output);

        // Restore original System.out
        System.setOut(originalSystemOut);
    }

    @Test
    public void testPrintMessageLine() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        BaseClass baseView = new BaseClass(new Scanner(System.in)) {
        };  // Anonymous subclass to access protected method
        String message = "Hello, World!";

        // Act
        baseView.printMessageLine(message);

        // Assert
        String output = outputStream.toString();
        // Allow for \r\n (Windows)
        assertEquals(output, message + "\r\n");

        // Restore original System.out
        System.setOut(originalSystemOut);
    }

    @Test
    public void testPrintMessageAndPrintMessageLine() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        BaseClass baseView = new BaseClass(new Scanner(System.in)) {
        };  // Anonymous subclass to access protected methods

        // Test printMessage
        String message1 = "Hello";
        baseView.printMessage(message1);

        // Test printMessageLine
        String message2 = "World!";
        baseView.printMessageLine(message2);

        // Act
        String output = outputStream.toString();

        // Assert
        // Verify that printMessage printed the message without a newline
        assertTrue(output.startsWith(message1));
        assertTrue(output.endsWith(message2 + "\r\n"));

        // Restore original System.out
        System.setOut(originalSystemOut);
    }

    @Test
    public void testPrintMessageEmptyString() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        BaseClass baseView = new BaseClass(new Scanner(System.in)) {
        };  // Anonymous subclass to access protected method

        // Edge case: empty string
        baseView.printMessage("");

        // Act
        String output = outputStream.toString();

        // Assert
        assertEquals("", output.trim());  // Check that empty string produces no output (ignores any newline)

        // Restore original System.out
        System.setOut(originalSystemOut);
    }

    @Test
    public void testPrintMessageSpecialCharacters() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        BaseClass baseView = new BaseClass(new Scanner(System.in)) {
        };  // Anonymous subclass to access protected method

        // Edge case: special characters
        String specialChars = "Hello\nWorld\t!";
        baseView.printMessageLine(specialChars);

        // Act
        String output = outputStream.toString();

        // Assert
        assertTrue(output.endsWith(specialChars + System.lineSeparator()));  // Special characters should be printed correctly

        // Restore original System.out
        System.setOut(originalSystemOut);
    }


}


