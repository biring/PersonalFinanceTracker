package service;

import dao.CategoryDAO;
import dao.LinkDAO;
import dao.TransactionDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinkService {

    private final CategoryDAO categoryDAO;
    private final LinkDAO linkDAO;
    private final TransactionDAO transactionDAO;


    public LinkService(CategoryDAO categoryDAO, LinkDAO linkDAO, TransactionDAO transactionDAO) {
        this.categoryDAO = categoryDAO;
        this.linkDAO = linkDAO;
        this.transactionDAO = transactionDAO;
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

    public String getLinkName(int id) {
        return linkDAO.getNameById(id);
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

    public List<String> getTransactionsMatchingLink(String linkName) {
        return transactionDAO.getTransactionsMatchingString(linkName);
    }

    // get unlinked transactions
    public List<String> getUnlinkedTransactions() {
        List<Integer> transactionIds = transactionDAO.getIDs();
        List<String> linkStrings = linkDAO.getLinkNames();
        List<Integer> unlinkedTransactions = new ArrayList<>();
        for (Integer transactionId : transactionIds) {
            boolean isUnlinked = true;
            for (String linkString : linkStrings) {
                if (transactionDAO.getNameById(transactionId).contains(linkString)) {
                    isUnlinked = false;
                    break;
                }
            }
            if (isUnlinked) {
                unlinkedTransactions.add(transactionId);
            }
        }
        return unlinkedTransactions.stream()
                .map(transactionDAO::getNameById)
                .distinct()
                .sorted()
                .toList();
    }
}
