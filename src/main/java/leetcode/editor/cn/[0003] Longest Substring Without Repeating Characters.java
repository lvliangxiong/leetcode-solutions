package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * </pre>
 */
class LongestSubstringWithoutRepeatingCharacters {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 使用 HashMap 记录遍历过的字符的索引位置。
         * 使用 数组 进行记录当然会更快，但是由于题目没有给出字符串的字符范围，所以不太好进行数组大小的声明。
         *
         * @param s
         * @return
         */
        public int lengthOfLongestSubstring(String s) {
            if (s == null) return 0;
            int n = s.length();
            if (n <= 1) return n;
            Map<Character, Integer> position = new HashMap<>();
            // 维护快慢指针之间的子字符串不包含重复字符
            int current = 0, start = 0, max = 0;
            while (current < n) {
                char ch = s.charAt(current);
                if (position.containsKey(ch)) {
                    start = Math.max(start, position.get(ch) + 1);
                }
                max = Math.max(max, current - start + 1);
                position.put(ch, current++);
            }
            return max;
        }

        /**
         * 直接对需要查重的子串进行遍历，由于字符数量较少，其速度比 HashMap 的实现还要快。
         *
         * @param s
         * @return
         */
        public int lengthOfLongestSubstringBrutally(String s) {
            if (s == null) return 0;
            int n = s.length();
            if (n <= 1) return n;
            char[] chars = s.toCharArray();
            int start = 0, end = 0, current = 1, max = 1;
            while (current < n) {
                char ch = chars[current];
                for (int i = start; i <= end; i++) {
                    if (chars[i] == ch) {
                        start = i + 1; // 更新 start
                        break;
                    }
                }
                end = current;
                current++; // 更新 current
                max = Math.max(max, end - start + 1);
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
