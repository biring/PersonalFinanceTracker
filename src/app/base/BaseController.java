package base;


import interfaces.ControllerInterface;

public abstract class BaseController<V> implements ControllerInterface {
    protected final V view;

    public BaseController(V view) {
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