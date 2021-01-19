package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WildcardMatchingTest {
    @Test
    void isMatch() {
        WildcardMatching.Solution solution = new WildcardMatching().new Solution();
        Assertions.assertTrue(solution.isMatch("ho", "**ho"));
    }
}