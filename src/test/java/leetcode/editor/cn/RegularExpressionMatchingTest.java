package leetcode.editor.cn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularExpressionMatchingTest {
    @Test
    void isMatch() {
        RegularExpressionMatching.Solution solution = new RegularExpressionMatching().new Solution();
        solution.isMatch("aa", "a*");
    }
}