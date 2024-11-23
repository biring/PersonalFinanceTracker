package dao;

import model.LinkModel;

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

    // Check if link name exists
    public boolean isLinkNameExists(String name) {
        for (LinkModel link : links) {
            if (link.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
