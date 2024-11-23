package service;

import dao.LinkDAO;

import java.io.IOException;
import java.util.List;

public class LinkService {

    private final LinkDAO linkDAO = new LinkDAO();
    private final CategoryService categoryService;

    public LinkService(CategoryService categoryService) {
        this.categoryService = categoryService;
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
                    String categoryName = categoryService.getCategoryNameById(link.getCategoryId());
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
        return categoryService.showCategories();
    }

    public boolean isCategoryExists(int categoryId) {
        return categoryService.isCategoryExists(categoryId);
    }

    public boolean isLinkStringValid(String linkName) {
        return (linkName != null)
                && (!linkName.isEmpty())
                && (!linkDAO.isLinkNameExists(linkName));
    }
}
