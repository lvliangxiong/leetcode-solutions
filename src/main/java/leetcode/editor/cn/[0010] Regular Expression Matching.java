package leetcode.editor.cn;

/**
 * <pre>
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 *     s could be empty and contains only lowercase letters a-z.
 *     p could be empty and contains only lowercase letters a-z, and characters like . or *.
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
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 *
 * Example 3:
 *
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 * Example 4:
 *
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 *
 * Example 5:
 *
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * </pre>
 */
class RegularExpressionMatching {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isMatch(String s, String p) {
            if (p.startsWith("*")) throw new IllegalArgumentException("Pattern should not begin with '*'");
            int m = s.length(), n = p.length();
            /* dp[i][j] 表示 s 的前 i 个字符是否和 p 的前 j 个字符匹配。p[i]，s[i] 表示 p 和 s 的第 i 个字符。
             * 1. p[j] 是一个小写字母，那么：
             *      dp[i][j] = dp[i-1][j-1] if p[j] == s[i] else false
             * 2. p[j] 是 '*'，那么：
             *      dp[i][j] = dp[i][j-2] if p[j-1] != s[i] else (dp[i][j-2] || dp[i-1][j])
             * 3. p[j] 是 '.'，那么：
             *      dp[i][j] = dp[i-1][j-1]
             */
            boolean[][] dp = new boolean[m + 1][n + 1];
            // 空串和空串是匹配的，空串还和类似于 "a*" 这样的字符串匹配
            dp[0][0] = true;
            for (int i = 0; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (p.charAt(j - 1) == '*') { // p 的第 j 个字符为 *，这里默认给出的 p 的开头第一个字符不可能是 *
                        dp[i][j] = dp[i][j - 2]; // * 重复前面一个字符 0 次
                        if (match(s, i, p, j - 1)) {
                            dp[i][j] = dp[i][j] || dp[i - 1][j];
                        }
                    } else { // p 中第 j 个字符不是 *，则比较 s 的第 i 个字符和 p 的第 j 个字符
                        if (match(s, i, p, j)) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }
                }
            }
            return dp[m][n];
        }

        private boolean match(String s, int i, String p, int j) {
            if (i == 0 || j == 0) return i == j;
            if (p.charAt(j - 1) == '.') return true;
            return s.charAt(i - 1) == p.charAt(j - 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
