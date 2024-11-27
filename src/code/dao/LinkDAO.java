package dao;

import model.CategoryModel;
import model.LinkModel;

import java.util.ArrayList;
import java.util.List;

public class LinkDAO extends BaseDAO<LinkModel> {

    private final List<LinkModel> links = super.items;

    public LinkDAO(){
        super(LinkModel.class);
    }

    @Override
    protected int extractID(LinkModel link) {
        return link.getID();
    }

    // Get all link IDs
    public List<Integer> getIDs() {
        List<Integer> ids = new ArrayList<>();
        for (LinkModel link : links) {
            ids.add(link.getID());
        }
        return ids;
    }

    // Create a new link
    public boolean create(int id, String name, int categoryID) {
        LinkModel link = new LinkModel(id, name, categoryID);
        return createItem(link);
    }

    // Update link name
    public boolean updateName(int id, String name) {
        for (LinkModel link : links) {
            if (link.getID() == id) {
                link.setName(name);
                return true;
            }
        }
        return false;
    }

    // Delete a link by category ID
    public boolean deleteLinksByCategoryId(int categoryId) {
        try {
            links.removeIf(link -> link.getCategoryId() == categoryId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Check if link name exists
    public boolean isLinkNameExists(String name) {
        for (LinkModel link : links) {
            if (link.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // get all names of links
    public List<String> getLinkNames() {
        return links.stream()
                .map(LinkModel::getName)
                .toList();
    }

    // get link IDs for category
    public List<Integer> getLinkIDsForCategory(int categoryId) {
        List<Integer> ids = new ArrayList<>();
        for (LinkModel link : links) {
            if (link.getCategoryId() == categoryId) {
                ids.add(link.getID());
            }
        }
        return ids;
    }
}
