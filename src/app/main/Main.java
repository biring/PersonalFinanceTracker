package main;

import common.Version;
import main.controller.MainController;

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
        System.out.println(Version.getBuild());

        try {
            MainController mainController = new MainController();
            mainController.start();
            mainController.run();
            mainController.stop();
        } catch (Exception e) {
            System.err.println("*** ERROR *** ");
            // Print the stack trace for detailed module, method, and line number information
            String format = "%s > %s @ %d\n";
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (int i = stackTrace.length - 1; i >= 0; i--) {
                StackTraceElement element = stackTrace[i];
                String module = element.getClassName();
                String method = element.getMethodName();
                int line = element.getLineNumber();
                System.err.printf(format, module, method, line);
            }
            System.err.println(e.getMessage());
        }
    }
}