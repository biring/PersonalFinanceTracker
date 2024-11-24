package service;

import dao.CategoryDAO;
import dao.LinkDAO;

import java.io.IOException;
import java.util.List;

public class LinkService {

    private final CategoryDAO categoryDAO;
    private final LinkDAO linkDAO;


    public LinkService(CategoryDAO categoryDAO, LinkDAO linkDAO) {
        this.categoryDAO = categoryDAO;
        this.linkDAO = linkDAO;
    }

    public boolean createLink(String name, int categoryId) {
        int id = linkDAO.getNextID();
        try {
            return linkDAO.create(id, name, categoryId);
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> showLinks() {
        return linkDAO.readAll().stream()
                .map(link -> {
                    String categoryName = categoryDAO.getNameById(link.getCategoryId());
                    return "["
                            + link.getID() + "] "
                            + link.getName() + " (category: "
                            + categoryName + ") ";
                })
                .toList();
    }

    public boolean updateLink(int id, String name) {
        try {
            return linkDAO.updateName(id, name);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteLink(int id) {
        try {
            return linkDAO.delete(id);
        } catch (Exception e) {
            return false;
        }
    }

    public void loadFromDb() throws IOException {
        linkDAO.deserialize();
    }

    public void storeToDb() throws IOException {
        linkDAO.serialize();
    }

    public boolean isLinkExists(int id) {
        return linkDAO.exists(id);
    }

    public List<String> showCategories() {
        return categoryDAO.readAll().stream()
                .map(category -> "[" + category.getID() + "] " + category.getName())
                .toList();
    }

    public boolean isCategoryExists(int categoryId) {
        return categoryDAO.exists(categoryId);
    }

    public boolean isLinkStringValid(String linkName) {
        return (linkName != null)
                && (!linkName.isEmpty())
                && (!linkDAO.isLinkNameExists(linkName));
    }
}
