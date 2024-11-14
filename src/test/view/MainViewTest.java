package view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainViewTest {

    @Test
    public void testDisplayMenu() {
        // Arrange
        Scanner mockScanner = new Scanner(System.in);
        MainView mainView = new MainView(mockScanner) {
        };  // Anonymous class to instantiate BaseView
        String[] menuOptions = {"Alpha", "Beta", "Gamma"};

        // Capture the console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        mainView.displayMenu(menuOptions);

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("[1] Alpha"));
        assertTrue(output.contains("[2] Beta"));
        assertTrue(output.contains("[3] Gamma"));

        // Restore original System.out
        System.setOut(originalSystemOut);
    }

    @Test
    public void testPromptForMenuSelection_ValidInput() {
        // Arrange
        String simulatedUserInput = "2\n";  // Simulating user input '2'
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        MainView mainView = new MainView(mockScanner) {
        };  // Anonymous class to instantiate BaseView
        String message = "Please select an option:";
        String[] menuOptions = {"Option 1", "Option 2", "Option 3"};
        mainView.displayMenu(menuOptions);

        // Act
        int selection = mainView.promptForMenuSelection(message);

        // Assert
        assertEquals(2, selection);  // Expecting '2' as the selection
    }

    @Test
    public void testPromptForMenuSelection_InvalidInput() {
        // Arrange
        String simulatedUserInput = "5\n2\n";  // Simulating invalid '5' followed by valid '2'
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        MainView mainView = new MainView(mockScanner) {
        };  // Anonymous class to instantiate BaseView
        String message = "Please select an option:";
        String[] menuOptions = {"Option 1", "Option 2", "Option 3"};
        mainView.displayMenu(menuOptions);

        // Act
        int selection = mainView.promptForMenuSelection(message);

        // Assert
        assertEquals(2, selection);  // Expecting '2' as the selection after the invalid input
    }
}
