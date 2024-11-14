package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

public class BaseClassTest {

    private Scanner scanner;
    private String view;
    private BaseClass<String> baseClass;

    @BeforeEach
    void setUp() {
        // Use an anonymous subclass to instantiate BaseClass
        scanner = new Scanner(System.in);  // Just a real Scanner object
        view = "Test View";                // Simple string for the view

        // Anonymous subclass of BaseClass, do not override start, run, or stop
        baseClass = new BaseClass<String>(scanner, view) {
            // No methods are overridden, so the base class's default behavior
            // (throwing UnsupportedOperationException) will be tested.
        };
    }

    @Test
    void testStartThrowsUnsupportedOperationException() {
        // Verify that calling start() throws UnsupportedOperationException
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, baseClass::start);
        assertEquals("Not implemented", exception.getMessage());
    }

    @Test
    void testRunThrowsUnsupportedOperationException() {
        // Verify that calling run() throws UnsupportedOperationException
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, baseClass::run);
        assertEquals("Not implemented", exception.getMessage());
    }

    @Test
    void testStopThrowsUnsupportedOperationException() {
        // Verify that calling stop() throws UnsupportedOperationException
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, baseClass::stop);
        assertEquals("Not implemented", exception.getMessage());
    }
}
