import controller.MainController;
import utility.Version;

import java.util.Scanner;

/**
 * The {@code Main} class serves as the entry point for the Application.
 * This class contains the {@code main} method, which is the first method
 * invoked when the program is executed.
 *
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Application started...");
        System.out.println("Personal Finance Tracker");
        System.out.println(Version.getVersion());

        // Create a shared Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        // Create an instance of MainController
        MainController mainController = new MainController(scanner);

        // Call a method on the controller to kick off the application logic
        mainController.start();  // Example: Starts the application logic
    }

}