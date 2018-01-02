import org.junit.jupiter.api.Test;
import strings.PatternMatch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPatternMatch {

    @Test
    public void patternMatchTrueABinABCE() {
        runTest("abce", "ab", true);
    }

    @Test
    public void patternMatchTrueBCinABCE() {
        runTest("abce", "bc", true);
    }

    @Test
    public void patternMatchFalse() {
        runTest("abce", "cb", false);
    }

    private void runTest(String str, String pattern, boolean assertTrue) {
        boolean match = PatternMatch.matchFound(str, pattern);
        String msg = "Found pattern " + pattern + " in " + str + ": " + match;
        System.out.println(msg);
        System.out.println();

        if (assertTrue)
            assertTrue(match, msg);
        else
            assertFalse(match, msg);
    }
}
