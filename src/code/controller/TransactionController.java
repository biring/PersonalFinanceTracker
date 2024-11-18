package controller;

import service.AccountService;
import service.TransactionService;
import utility.CSVReader;
import view.TransactionView;

import java.util.ArrayList;
import java.util.List;

public class TransactionController extends BaseClass<TransactionView> {
    private final TransactionService transactionService;

    public TransactionController(AccountService accountService) {
        super(new TransactionView());
        this.transactionService = new TransactionService(accountService);
    }

    public void run() {
        enumMenuOptions selectedOption;
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
        List<String> availableFiles = CSVReader.getCSVFilesInFolder("data");
        if (availableFiles.isEmpty()) {
            view.showNoCSVFilesFound();
        } else {
            String selectedFile = getFileSelection(availableFiles);
            List<String[]> data = CSVReader.readCSV(selectedFile);
            //boolean success = transactionService.processCSVData(data);
            view.showCSVFileReadResult(!data.isEmpty());
        }
    }

    private String getFileSelection(List<String> files) {
        List<String> filesWithIndex = addIndexToCSVFiles(files);
        int fileIndex = view.promptForCSVFileSelection(filesWithIndex);
        while (true) {
            try {
                String selectedFile = files.get(fileIndex - 1);
                System.out.println("Selected file: " + selectedFile);
                return selectedFile;
            } catch (IndexOutOfBoundsException e) {
                view.showInvalidSelection();
                fileIndex = view.promptForCSVFileSelection();
            }
        }
    }

    private List<String> addIndexToCSVFiles(List<String> files) {
        List<String> filesWithIndex = new ArrayList<>(files.size());
        for (int i = 1; i <= files.size(); i++) {
            filesWithIndex.add("[" + i + "] " + files.get(i - 1));
        }
        return filesWithIndex;
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