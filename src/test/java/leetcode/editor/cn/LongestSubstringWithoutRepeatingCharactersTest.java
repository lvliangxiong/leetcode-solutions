package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/9/2020
 */
class LongestSubstringWithoutRepeatingCharactersTest {
    @Test
    void lengthOfLongestSubstring() {
        LongestSubstringWithoutRepeatingCharacters.Solution solution = new LongestSubstringWithoutRepeatingCharacters().
                new Solution();
        Assertions.assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
        Assertions.assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"));
        Assertions.assertEquals(1, solution.lengthOfLongestSubstring("qqqqqqqqqqqq"));
    }
}