package leetcode.editor.cn;

/**
 * <pre>
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * </pre>
 */
class LongestPalindromicSubstring {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            int len = s.length(), max = 0, center = 0;
            if (len <= 1) return s;
            char[] chars = s.toCharArray();
            int count = (len << 1) + 1;
            for (int i = 0; i < count; i++) {
                int length;
                if ((i & 1) == 0) {
                    length = expand(chars, (i >> 1) - 1, (i >> 1) + 1);
                } else {
                    length = expand(chars, i - 1 >> 1, i + 1 >> 1);
                }
                if (max < length) {
                    max = length;
                    center = i;
                }
            }
            if ((center & 1) == 0) {
                return s.substring((center >> 1) - (max - 1 >> 1), (center >> 1) + (max + 1 >> 1));
            } else {
                return s.substring((center + 1 >> 1) - (max >> 1), (center + 1 >> 1) + (max >> 1));
            }
        }

        private int expand(char[] chars, int left, int right) {
            while (left >= 0 && right < chars.length) {
                if (chars[left] != chars[right]) break;
                left--;
                right++;
            }
            return (right - left) - 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
