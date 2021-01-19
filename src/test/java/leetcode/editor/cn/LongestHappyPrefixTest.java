package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/25/2020
 */
class LongestHappyPrefixTest {
    @Test
    void kmp() {
        LongestHappyPrefix.KMPSolution solution = new LongestHappyPrefix().new KMPSolution();
        Assertions.assertEquals("ab", solution.longestPrefix("abab"));
    }
}