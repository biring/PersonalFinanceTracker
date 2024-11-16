package controller;


public abstract class BaseClass<V> implements Interface {
    protected final V view;

    public BaseClass(V view) {
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