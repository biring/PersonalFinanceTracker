package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class CSVReader {

    // Private constructor to prevent instantiation
    private CSVReader() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static List<String> getCSVFilesInFolder(String folderPath) {
        List<String> csvFiles = new ArrayList<>();
        File folder = new File(folderPath);

        // Check if the folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

            if (files != null) {
                for (File file : files) {
                    csvFiles.add(file.getAbsolutePath());
                }
            }
        } else {
            System.out.println("Invalid folder path: " + folderPath);
        }

        return csvFiles;
    }

    public static List<String[]> readCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                // Split line by commas and add to list
                String[] values = line.split(",");
                records.add(values);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + e.getMessage());
        }

        return records;
    }

    public static void main(String[] args) {

        List<String> csvFiles = getCSVFilesInFolder("data");
        String filePath = csvFiles.get(1);
        List<String[]> data = readCSV(filePath);

        // Print each record
        for (String[] record : data) {
            for (String field : record) {
                System.out.print(field);
            }
            System.out.println();
        }
    }
}
