package leetcode.editor.cn;

/**
 * <pre>
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 *
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 *     s could be empty and contains only lowercase letters a-z.
 *     p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 *
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 *
 * Example 3:
 *
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 *
 * Example 4:
 *
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 *
 * Example 5:
 *
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wildcard-matching
 * </pre>
 */
class WildcardMatching {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isMatch(String s, String p) {
            int sLen = s.length();
            int pLen = p.length();
            // dp[i][j] 代表字符串 p 的前 j 个字符能否和字符串 s 的前 i 个字符匹配
            boolean dp[][] = new boolean[sLen + 1][pLen + 1];
            // 只有连续的字符 * 和空串可以匹配空串
            dp[0][0] = true;
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j - 1) == '*') dp[0][j] = true;
                else break;
            }
            for (int i = 1; i <= sLen; i++) {
                for (int j = 1; j <= pLen; j++) {
                    char ch = p.charAt(j - 1); // p 字符串的第 j 个字符
                    switch (ch) {
                        case '*':
                            // 如果 ch 为 *，那么有：dp[i][j] = dp[i][j-1] || dp[i-1][j]
                            dp[i][j] = dp[i][j - 1] /*使用 * 匹配空串*/ || dp[i - 1][j] /*使用 * 匹配至少 1 个字符*/;
                            break;
                        case '?':
                            dp[i][j] = dp[i - 1][j - 1];
                            break;
                        default:
                            dp[i][j] = s.charAt(i - 1) == ch && dp[i - 1][j - 1];
                    }
                }
            }
            return dp[sLen][pLen];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
