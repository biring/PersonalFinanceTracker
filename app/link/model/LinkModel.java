package link.model;

import base.BaseModel;

/**
 * This is a model class that represents a link sting (name) to transaction and category.
 * <p>
 * This class extends {@link base.BaseModel} and adds functionality
 * for holding link of name used to link category budget amount, providing access to it, and
 * generating a string representation of the account.
 * </p>
 */

public class LinkModel extends BaseModel {

    /**
     * This variable stores the format template for generating a descriptive string representation
     * of a LinkModel instance. The format includes placeholders for the ID, Name, and CategoryId.
     * The placeholders are:
     * - %d for the ID (integer)
     * - %s for the Name (string)
     * - %d for the CategoryId (integer)
     */
    protected final String toStringFormat = "Link: Id=%d, Name=%s, CategoryId=%d";
    /**
     * Represents the category ID associated with a link model.
     * This field is immutable and accessible only within the same package.
     */
    private final int categoryId;

    /**
     * Constructs a new LinkModel with the specified ID, name, and category ID.
     *
     * @param id         the unique identifier for the LinkModel
     * @param name       the name associated with the LinkModel. Name is used to store the lookup string
     * @param categoryId the category ID associated with the LinkModel
     */
    public LinkModel(int id, String name, int categoryId) {
        super(id, name);
        this.categoryId = categoryId;
    }

    /**
     * Retrieves the category ID associated with the LinkModel instance.
     *
     * @return the category ID of the LinkModel
     */
    public int getCategoryId() {
        return this.categoryId;
    }

    /**
     * Returns a string representation of the LinkModel instance.
     * The format includes placeholders for the ID, Name, and CategoryId.
     *
     * @return a formatted string representing the LinkModel instance
     */
    @Override
    public String toString() {
        return String.format(toStringFormat, getID(), getName(), getCategoryId());
    }
}