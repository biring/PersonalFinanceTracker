package dao;

import utility.Json;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public abstract class BaseDAO<T> {
    protected final List<T> items = new ArrayList<>();
    protected int nextID = 0;

    // Store the class type for serialization/deserialization
    private final Class<T> type;

    public BaseDAO(Class<T> type) {
        this.type = type;
    }

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

    // Get name by ID
    public String getNameById(int id) {
        for (T item : items) {
            if (extractID(item) == id) {
                // Assuming the item has a getName() method
                try {
                    return (String) item.getClass().getMethod("getName").invoke(item);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to get name by ID", e);
                }
            }
        }
        return null;
    }

    // Abstract method to extract ID from an item
    protected abstract int extractID(T item);


    // Serialize the items list to a file
    public void serialize() throws IOException {
        Json.writeToFile(type.getSimpleName(), items); // Use the class type for the file name
    }

    // Deserialize the items list from a file
    public void deserialize() throws IOException {
        Type typeOfT = TypeToken.getParameterized(List.class, this.type).getType();  // Use the correct type for List<TransactionModel>
        List<T> deserializedItems = Json.readFromFile(this.getClass().getSimpleName(), typeOfT);

        if (deserializedItems != null) {
            items.clear();
            items.addAll(deserializedItems);

            // Update nextID based on deserialized items
            this.nextID = deserializedItems.stream()
                    .mapToInt(this::extractID)
                    .max()
                    .orElse(0) + 1;
        }
    }
}
