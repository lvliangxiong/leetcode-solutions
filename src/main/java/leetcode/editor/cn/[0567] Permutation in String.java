package leetcode.editor.cn;

/**
 * <pre>
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words,
 * one of the first string's permutations is the substring of the second string.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 *
 * Example 2:
 *
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 *
 *
 *
 * Constraints:
 *
 *     The input strings only contain lower case letters.
 *     The length of both given strings is in range [1, 10,000].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * </pre>
 */
class PermutationInString {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            int[] count = new int[26];
            for (char ch : s1.toCharArray()) {
                count[ch - 'a']++;
            }
            // s2 的子串中是否存在相同的 count
            int same = 0;
            for (int i : count) {
                if (i == 0) same++;
            }
            // 当 same == 26 时，表示找到该子串
            int start = 0, end = 0, n = s2.length();
            while (end < n) {
                char ch = s2.charAt(end);
                if (count[ch - 'a'] == 0) {
                    while (true) {
                        char st = s2.charAt(start);
                        if (st == ch) break;
                        // 逐步移出左边界字符，并更新 count 数组和 same 标志
                        if (count[st - 'a'] == 0) same--;
                        count[st - 'a']++;
                        start++;
                    }
                    // navigate to the right nearest ch of start
                    start++;
                } else {
                    if (--count[ch - 'a'] == 0) same++;
                    if (same == 26) return true;
                }
                end++;
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
