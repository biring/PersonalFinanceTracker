package view;

public interface Interface {
    // Method to display the main menu options
    void displayMenu(String[] menuOptions);

    // Method to prompt the user for a selection
    int promptForMenuSelection(String message);

    // Method to prompt the user for a string input
    String promptForStringInput(String message);

    // Method to prompt the user for an integer input
    int promptForIntInput(String message);

    // Method to print menu title
    void printMenuTitle(String title);

    // Method to print table title
    void printTableTitle(String title);
}
