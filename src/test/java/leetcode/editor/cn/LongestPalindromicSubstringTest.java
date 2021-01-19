package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/17/2020
 */
class LongestPalindromicSubstringTest {
    @Test
    void longestPalindrome() {
        LongestPalindromicSubstring.ManacherSolution solution = new LongestPalindromicSubstring().new ManacherSolution();
        Assertions.assertEquals("bb", solution.longestPalindrome("cbbd"));
    }
}