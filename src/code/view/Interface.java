package view;

public interface ViewInterface {
    // Method to display the main menu options
    void displayMenu(String[] menuOptions);

    // Method to prompt the user for a selection
    int promptForMenuSelection(String message);
}
