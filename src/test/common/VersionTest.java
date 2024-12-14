package common;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class VersionTest {

    @Test
    public void testGetVersion() {
        String version = Version.getVersion();

        // Regex to match the format "Version %d.%d.%d Build %d"
        String regex = "^Version \\d+\\.\\d+\\.\\d+ Build \\d+$";

        assertTrue(version.matches(regex), "Version string does not match the expected format");
    }
}