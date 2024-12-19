package base;

/**
 * The BaseModel class represents a base model that contains an ID and a name.
 * This class is intended to be extended or used as a foundational model for other entities.
 * It provides methods for getting and setting the name, and accessing the ID which is immutable.
 */
public class BaseModel {
    /**
     * The unique identifier for this model instance. This field is final and cannot be modified.
     */
    protected final int id;
    /**
     * The name associated with this model. It can be modified using the setter method.
     */
    protected String name;

    /**
     * Constructs a new BaseModel with the specified ID and name.
     *
     * @param id the unique identifier for the model
     * @param name the name associated with the model
     */
    public BaseModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the ID of the model. The ID is immutable and cannot be changed.
     *
     * @return the ID of the model
     */
    public final int getID() {
        return this.id;
    }

    /**
     * Returns the name of the model.
     *
     * @return the name of the model
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets a new name for the model.
     *
     * @param name the new name to set for the model
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a database file name based on the class name.
     * The class name will be converted to lowercase and the extension .json will be added.
     *
     * @return the database file name (e.g., "account.json")
     */
    public String getDbName() {
        // Retrieve the class name and convert it to lowercase, then append ".json"
        return this.getClass().getSimpleName().toLowerCase() + ".json";
    }
}