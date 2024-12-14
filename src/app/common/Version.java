package common;

/**
 * The {@code util.Version} class represents the version information of this software application.
 * It provides static methods to retrieve the version details which are represented as static constants.
 * This class is designed as a utility class and cannot be instantiated.
 * <p>
 * Note: The version should only be incremented using the designated build and commit script.
 * Manual changes to the version numbers are not allowed.
 * </p>
 */
public class Version {

    private static final int major = 0;
    private static final int minor = 1;
    private static final int patch = 0;
    private static final int build = 0;
    private static final String versionFormat = "Version %d.%d.%d Build %d";

    // Constructor prevents instantiation of the Version class.
    private Version() {
        throw new UnsupportedOperationException("Cannot instantiate Version class.");
    }

    /**
     * Returns the version string in the format "Version <major>.<minor>.<patch> Build <build>".
     *
     * @return a string representation of the version.
     */
    public static String getVersion() {
        return String.format(versionFormat, major, minor, patch, build);
    }
}

