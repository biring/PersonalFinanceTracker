package common;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Json {
    private static final Gson gson = new Gson();

    // Ensure the directory exists
    private static void ensureDirectoryExists(String dirPath) {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    // Get the file path for the given class
    public static String getFilePath(String className) {
        String dirPath = "data";
        ensureDirectoryExists(dirPath);
        return dirPath + "/" + className + ".json";
    }

    // Write data to a file
    public static <T> void writeToFile(String className, T data) throws IOException {
        String filePath = getFilePath(className);
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        }
    }

    // Read data from a file
    public static <T> T readFromFile(String className, Type typeOfT) throws IOException {
        String filePath = getFilePath(className);
        File file = new File(filePath);

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                return gson.fromJson(reader, typeOfT);
            }
        } else {
            // Create an empty file and return null
            Files.createFile(Paths.get(filePath));
            return null;
        }
    }
}
