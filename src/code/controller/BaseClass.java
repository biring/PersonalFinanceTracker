package controller;

import java.util.Scanner;

public abstract class BaseClass<V> implements Interface {
    protected final Scanner scanner;
    protected final V view;

    public BaseClass(Scanner scanner, V view) {
        this.scanner = scanner;
        this.view = view;
    }

    @Override
    public void start() {
        // Default implementation (can be overridden)
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void run() {
        // Default implementation (can be overridden)
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void stop() {
        // Default implementation (can be overridden)
        throw new UnsupportedOperationException("Not implemented");
    }
}