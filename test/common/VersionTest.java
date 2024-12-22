package common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.regex.Pattern;

/**
 * Unit tests for the {@link Version} class.
 */
public class VersionTest {

    /**
     * Test the {@link Version#getVersion()} method.
     * Verifies that the version string has the correct format.
     */
    @Test
    public void testGetVersionFormat() {
        // Regex pattern for the version string format "Version <major>.<minor>.<patch>"
        String versionPattern = "^Version \\d+\\.\\d+\\.\\d+$";

        // Get the actual version string
        String version = Version.getVersion();

        // Assert that the version matches the expected format
        assertTrue(Pattern.matches(versionPattern, version), "Version string should match the format 'Version <major>.<minor>.<patch>'.");
    }

    /**
     * Test the {@link Version#getBuild()} method.
     * Verifies that the build string has the correct format.
     */
    @Test
    public void testGetBuildFormat() {
        // Regex pattern for the build string format "Build <build>"
        String buildPattern = "^Build \\d+$";

        // Get the actual build string
        String build = Version.getBuild();

        // Assert that the build matches the expected format
        assertTrue(Pattern.matches(buildPattern, build), "Build string should match the format 'Build <build>'.");
    }
}
