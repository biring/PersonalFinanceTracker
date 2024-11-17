package dao;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {
    protected final List<T> items = new ArrayList<>();
    protected int nextID = 0;

    // Get the next available ID
    public int getNextID() {
        if (this.nextID == 0) {
            for (T item : items) {
                int currentID = extractID(item);
                if (this.nextID <= currentID) {
                    this.nextID = currentID;
                }
            }
            this.nextID++;
        }
        return this.nextID;
    }

    // Create an item
    public boolean createItem(T item) {
        items.add(item);
        this.nextID++;
        return items.contains(item);
    }

    // Read an item by ID
    public T read(int id) {
        for (T item : items) {
            if (extractID(item) == id) {
                return item;
            }
        }
        return null;
    }

    // Read all items
    public List<T> readAll() {
        return items;
    }

    // Delete an item by ID
    public boolean delete(int id) {
        return items.removeIf(item -> extractID(item) == id);
    }

    // Abstract method to extract ID from an item
    protected abstract int extractID(T item);
}
