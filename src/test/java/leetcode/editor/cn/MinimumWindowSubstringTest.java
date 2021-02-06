package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MinimumWindowSubstringTest {
    @Test
    void minWindow() {
        MinimumWindowSubstring.Solution solution = new MinimumWindowSubstring().new Solution();
        Assertions.assertEquals("BANC", solution.minWindow("ADOBECODEBANC", "ABC"));
    }
}