package common;

/**
 * The {@code Version} class represents the version information of this software application.
 * It provides static methods to retrieve the version details, which are represented as static constants.
 * This class is designed as a utility class and cannot be instantiated.
 * <p>
 * Note: The version numbers should only be incremented using the designated build and commit scripts.
 * Manual changes to the version numbers are not allowed.
 * </p>
 */
public class Version {

    // Major version number
    private static final int major = 0;

    // Minor version number
    private static final int minor = 1;

    // Patch version number
    private static final int patch = 0;

    // Build version number
    private static final int build = 11;

    // Format string for version
    private static final String versionFormat = "Version %d.%d.%d";

    // Format string for build number
    private static final String buildFormat = "Build %d";

    // Private constructor prevents instantiation of the Version class.
    private Version() {
        throw new UnsupportedOperationException("Cannot instantiate Version class.");
    }

    /**
     * Returns the version string in the format "Version <major>.<minor>.<patch>".
     *
     * @return a string representation of the version in the format "Version <major>.<minor>.<patch>".
     */
    public static String getVersion() {
        return String.format(versionFormat, major, minor, patch);
    }

    /**
     * Returns the build information string in the format "Build <build>".
     *
     * @return a string representation of the build number in the format "Build <build>".
     */
    public static String getBuild() {
        return String.format(buildFormat, build);
    }
}
