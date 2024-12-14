package link.view;

import base.BaseView;
import interfaces.MenuInterface;
import common.Console;

import java.util.List;

import static link.message.LinkMessage.*;

public class LinkView extends BaseView {

    public LinkView() {
        super();
    }

    public <T extends Enum<T> & MenuInterface> T promptForEnumMenuSelection(Class<T> enumType) {
        return super.getEnumMenuSelection(enumType, TITLE_MENU_TABLE, PROMPT_FOR_MENU_SELECTION);
    }

    public int promptForCategorySelection(List<String> categories) {
        return super.getTableSelection(categories, TITLE_CATEGORY_TABLE, PROMPT_FOR_CATEGORY_SELECTION);
    }

    public int promptForCategorySelection() {
        return super.promptForIntInput(PROMPT_FOR_CATEGORY_SELECTION);
    }

    public String promptForLinkString() {
        return super.promptForStringInput(PROMPT_FOR_LINK_STRING);
    }

    public int promptForLinkSelection(List<String> links) {
        return super.getTableSelection(links, TITLE_LINK_TABLE, PROMPT_FOR_LINK_SELECTION);
    }

    public int promptForLinkSelection() {
        return super.promptForIntInput(PROMPT_FOR_LINK_SELECTION);
    }

    public void showLinks(List<String> links) {
        super.displayTableData(links, TITLE_LINK_TABLE);
    }

    public void showNoCategoriesAvailable() {
        Console.printMessage(INFO_NO_CATEGORY_FOUND);
    }

    public void showInvalidSelection() {
        Console.printMessage(WARNING_INVALID_SELECTION);
    }

    public void showNoLinksAvailable() {
        Console.printMessage(INFO_NO_LINKS_FOUND);
    }

    public void showDbReadError() {
        Console.printMessage(ERROR_DB_READ_FAILED);
    }

    public void showDbWriteError() {
        Console.printMessage(ERROR_DB_WRITE_FAILED);
    }

    public void showCategorySelectionResult(boolean success) {
        super.displaySuccessFailureMessage(success, "", WARNING_INVALID_SELECTION);
    }

    public void showLinkSelectionResult(boolean success) {
        super.displaySuccessFailureMessage(success, "", WARNING_INVALID_SELECTION);
    }

    public void showLinkValidationResult(boolean success) {
        super.displaySuccessFailureMessage(success, "", WARNING_INVALID_LINK_STRING);
    }

    public void showCreationResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_CREATION_SUCCESS, WARNING_CREATION_FAILED);
    }

    public void showModificationResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_MODIFICATION_SUCCESS, WARNING_MODIFICATION_FAILED);
    }

    public void showDeletionResult(boolean success) {
        super.displaySuccessFailureMessage(success, INFO_DELETION_SUCCESS, WARNING_DELETION_FAILED);
    }
}