package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/24/2020
 */
class RepeatedSubstringPatternTest {
    @Test
    void repeatedSubstringPattern() {
        RepeatedSubstringPattern.Solution solution = new RepeatedSubstringPattern().new Solution();
        Assertions.assertTrue(solution.repeatedSubstringPattern("abcabcabc"));
    }
}