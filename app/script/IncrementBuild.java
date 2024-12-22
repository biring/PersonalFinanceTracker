package script;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

/**
 * The IncrementBuild class is responsible for incrementing the build number in the "Version.java" file.
 * It searches for a specific line containing the build number, increments the number, and then writes
 * the updated content back to the file.
 */
public class IncrementBuild {

    // The directory of the current working directory.
    public static final String USER_DIR = System.getProperty("user.dir");

    // The name of the project directory used to locate the project root directory.
    private static final String PROJECT_ROOT_NAME = "PersonalFinanceTracker";

    // The path to the Version.java file that contains the build number to increment.
    static Path versionFilePath;

    // The pattern used to find the line containing the build number.
    private static final String BUILD_NUMBER_LINE_SEARCH_PATTERN = "private static final int build";

    // Regular expression pattern for finding the build number in the line.
    public static final Pattern NUMBER_PATTERN = Pattern.compile("(\\d+)");

    /**
     * The main method that serves as the entry point of the program.
     * It identifies the project root, locates the "Version.java" file,
     * reads the file content, increments the build number, and writes the updated content back to the file.
     *
     * @param args Command-line arguments (not used in this implementation).
     * @throws IOException If an I/O error occurs while reading or writing to the file.
     */
    public static void main(String[] args) throws IOException {
        try {
            // Get the current working directory.
            Path currentDir = Paths.get(USER_DIR);

            // Traverse upwards in the directory structure to find the project root directory.
            Path projectRoot = findProjectRoot(currentDir);

            // Construct the path to "Version.java" relative to the project root.
            versionFilePath = projectRoot.resolve("app").resolve("common").resolve("Version.java");

            // Read all lines from the "Version.java" file.
            List<String> lines = Files.readAllLines(versionFilePath);

            // Increment build number
            List<String> updatedLines = updateBuildNumber(lines);

            // Write the updated lines back to the "Version.java" file.
            Files.write(versionFilePath, updatedLines);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1); // requited for batch file

        }
    }

    /**
     * Traverses up the directory tree from the given starting path to locate the project root directory.
     *
     * @param start The starting directory to begin the search.
     * @return The path to the project root directory.
     * @throws IllegalStateException If the project root directory is not found.
     */
    private static Path findProjectRoot(Path start) {
        // Start the search from the provided directory.
        Path currentDir = start;

        // Traverse upwards in the directory tree until the project root is found.
        while (currentDir != null && !currentDir.getFileName().toString().equals(PROJECT_ROOT_NAME)) {
            currentDir = currentDir.getParent();
        }

        // If the project root is not found, throw an exception.
        if (currentDir == null) {
            throw new IllegalStateException("Project root directory '" + PROJECT_ROOT_NAME + "' not found.");
        }

        // Return the path to the project root.
        return currentDir;
    }

    /**
     * Updates the build number in the provided list of lines by one.
     *
     * @param lines The list of strings representing the lines in a file.
     * @return A new list of strings with the build number incremented by 1 where applicable.
     */
    private static List<String> updateBuildNumber(List<String> lines) {
        boolean isBuildIncrement = false;
        // List to hold the updated lines with the incremented build number.
        List<String> updatedLines = new ArrayList<>();

        // Iterate over each line in the file.
        for (String line : lines) {
            // Check if the line contains the build number declaration.
            if (line.contains(BUILD_NUMBER_LINE_SEARCH_PATTERN)) {
                // Use a matcher to find the first number in the line.
                Matcher matcher = NUMBER_PATTERN.matcher(line);
                // If a number is found, increment it.
                if (matcher.find()) {
                    // Extract the current build number and increment it by 1.
                    int buildNo = Integer.parseInt(matcher.group());
                    int nextBuildNo = buildNo + 1;
                    System.out.println("Build number increased from " + buildNo + " to " + nextBuildNo);
                    line = line.replaceFirst("(\\d+)", String.valueOf(nextBuildNo));
                    isBuildIncrement = true;
                }
            }
            // Add the possibly modified line to the updated list.
            updatedLines.add(line);
        }
        if (!isBuildIncrement) {
            throw new IllegalStateException("Build number not found in Version.java file.");
        }
        return updatedLines;
    }
}
