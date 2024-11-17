package controller;

import view.TransactionView;

public class TransactionController extends BaseClass<TransactionView> {

    public TransactionController() {
        super(new TransactionView());
    }

    public void run() {
        TransactionController.enumMenuOptions selectedOption;
        do {
            selectedOption = view.promptForEnumMenuSelection(enumMenuOptions.class);
            switch (selectedOption) {
                case CREATE:
                    createTransaction();
                    break;
                case MODIFY:
                    modifyTransaction();
                    break;
                case VIEW:
                    viewTransaction();
                    break;
                case DELETE:
                    deleteTransaction();
                    break;
                case IMPORT:
                    importTransaction();
                    break;
                case EXIT:
                    System.out.println("Exit");
                    break;
                default:
                    throw new IllegalStateException("Invalid value for selection.");
            }
        }
        while (selectedOption != enumMenuOptions.EXIT);
    }

    private void createTransaction() {
        view.showMenuOptionNotSupported();
    }

    private void modifyTransaction() {
        view.showMenuOptionNotSupported();
    }

    private void viewTransaction() {
        view.showMenuOptionNotSupported();
    }

    private void deleteTransaction() {
        view.showMenuOptionNotSupported();
    }

    private void importTransaction() {
        view.showMenuOptionNotSupported();
    }

    private enum enumMenuOptions implements MenuOption {
        CREATE("Create Transaction"),
        MODIFY("Modify Transaction"),
        VIEW("View Transaction"),
        DELETE("Delete Transaction"),
        IMPORT("Import Transaction"),
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