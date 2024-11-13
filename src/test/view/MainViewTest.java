package view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainViewTest {

    @Test
    public void testMainView_InheritsBaseViewBehavior() {
        // Arrange
        String simulatedUserInput = "1\n";  // Simulating user input '1'
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        MainView mainView = new MainView(mockScanner);
        String message = "Please select an option:";
        String[] menuOptions = {"Option 1", "Option 2", "Option 3"};
        mainView.displayMenu(menuOptions);

        // Act
        int selection = mainView.promptForMenuSelection(message);

        // Assert
        assertEquals(1, selection);  // Expecting '1' as the selection
    }
}
