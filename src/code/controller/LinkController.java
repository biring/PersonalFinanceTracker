package controller;

import service.CategoryService;
import service.LinkService;
import view.LinkView;

import java.io.IOException;
import java.util.List;

public class LinkController extends BaseClass<LinkView> {

    private final LinkService linkService;

    public LinkController (CategoryService categoryService) {
        super(new LinkView());
        this.linkService = new LinkService(categoryService);
    }

    public void start() {
        readFromDatabase();
    }

    public void run() {
        enumMenuOptions selectedOption;
        do {
            selectedOption = view.promptForEnumMenuSelection(enumMenuOptions.class);
            switch (selectedOption) {
                case CREATE:
                    createLink();
                    break;
                case MODIFY:
                    modifyLink();
                    break;
                case VIEW:
                    viewLink();
                    break;
                case DELETE:
                    deleteLink();
                    break;
                case EXIT:
                    writeToDatabase();
                    break;
                default:
                    throw new IllegalStateException("Invalid value for selection.");
            }
        }
        while (selectedOption != enumMenuOptions.EXIT);
    }

    public void stop() {
        writeToDatabase();
    }

    private void readFromDatabase() {
        try {
            linkService.loadFromDb();
        } catch (IOException e) {
            view.showDbReadError();
        }
    }
    private void writeToDatabase() {
        try {
            linkService.storeToDb();
        } catch (IOException e) {
            view.showDbWriteError();
        }
    }

    private void createLink() {
        if (linkService.showCategories().isEmpty()) {
            view.showNoCategoriesAvailable();
        }
        else {
            int categoryId = getCategorySelection();
            String linkName = getLinkString();
            boolean success = linkService.createLink(linkName, categoryId);
            view.showCreationResult(success);
        }
    }

    private void modifyLink() {
        if (linkService.showLinks().isEmpty()) {
            view.showNoLinksAvailable();
        }
        else {
            int linkId = getLinkSelection();
            String linkName = getLinkString();
            boolean success = linkService.updateLink(linkId, linkName);
            view.showModificationResult(success);
        }
    }

    private void viewLink() {
        List<String> links = linkService.showLinks();
        if (links.isEmpty()) {
            view.showNoLinksAvailable();
        }
        else {
            view.showLinks(links);
        }
    }

    private void deleteLink() {
        if (linkService.showLinks().isEmpty()) {
            view.showNoLinksAvailable();
        }
        else {
            int linkId = getLinkSelection();
            boolean success = linkService.deleteLink(linkId);
            view.showDeletionResult(success);
        }
    }

    private int getCategorySelection() {
        List<String> categories = linkService.showCategories();
        boolean isValidCategory;
        int categoryId;
        do {
            categoryId = view.promptForCategorySelection(categories);
            isValidCategory = linkService.isCategoryExists(categoryId);
            view.showCategorySelectionResult(isValidCategory);
        } while (!isValidCategory);
        return categoryId;
    }

    private int getLinkSelection() {
        List<String> links = linkService.showLinks();
        boolean isValidLink;
        int linkId;
        do {
            linkId = view.promptForLinkSelection(links);
            isValidLink = linkService.isLinkExists(linkId);
            view.showLinkSelectionResult(isValidLink);
        } while (!isValidLink);
        return linkId;
    }

    private String getLinkString() {
        String linkString = "";
        boolean isValidLink;
        do{
            linkString = view.promptForLinkString();
            isValidLink = linkService.isLinkStringValid(linkString);
            view.showLinkValidationResult(isValidLink);
        } while (!isValidLink);
        return linkString;
    }

    private enum enumMenuOptions implements MenuOption {
        CREATE("Create Link"),
        MODIFY("Modify Link"),
        VIEW("View Link"),
        DELETE("Delete Link"),
        EXIT("Exit");

        private final String text;

        enumMenuOptions(String text) {
            this.text = text;
        }

        @Override
        public String getText() {
            return text;
        }
    }
}