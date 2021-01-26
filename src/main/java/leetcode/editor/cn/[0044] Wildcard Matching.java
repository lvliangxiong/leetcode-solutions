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
        /**
         * @param s
         * @param p
         * @return
         * @see RegularExpressionMatching.Solution#isMatch(String, String)
         */
        public boolean isMatch(String s, String p) {
            int sLen = s.length();
            int pLen = p.length();
            // dp[i][j] 代表字符串 p 的前 j 个字符能否和字符串 s 的前 i 个字符匹配
            boolean dp[][] = new boolean[sLen + 1][pLen + 1];

            // 初始化 i = 0
            dp[0][0] = true;
            for (int j = 1; j <= pLen; j++) {
                // 只有连续的字符 * 和空串可以匹配空串
                if (p.charAt(j - 1) == '*') dp[0][j] = true;
                else break;
            }

            // i > 0
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

    /**
     * 贪婪算法：
     * 思考形如 *u1*u2*u3*u4*.....*un* 的 pattern 字符串应该如何匹配？
     * <p>
     * https://leetcode-cn.com/problems/wildcard-matching/solution/tong-pei-fu-pi-pei-by-leetcode-solution/
     */
    class GreedySolution {
        public boolean isMatch(String s, String p) {
            int sRight = s.length(), pRight = p.length();
            // 从字符串末尾开始进行匹配，直到指针 pRight 位置上的字符为 * 或者匹配完成
            while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
                if (charMatch(s.charAt(sRight - 1), p.charAt(pRight - 1))) {
                    --sRight;
                    --pRight;
                } else {
                    // 出现不匹配的字符，直接返回 false
                    return false;
                }
            }
            // 如果 pRight 为 0，表示 p 字符串中没有字符 *
            if (pRight == 0) {
                return sRight == 0;
            }

            int sIndex = 0, pIndex = 0;
            int sRecord = -1, pRecord = -1;

            // 从左向右匹配
            while (sIndex < sRight && pIndex < pRight) {
                if (p.charAt(pIndex) == '*') {
                    ++pIndex; // 使用 * 匹配空字符串
                    // 记录 sIndex 和 pIndex 的位置，用于后续匹配失败后调整 sIndex 和 pIndex
                    sRecord = sIndex;
                    pRecord = pIndex;
                } else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                    ++sIndex;
                    ++pIndex;
                } else if (sRecord != -1 /*sRecord == -1 则说明 p 的当前索引位置前面没有字符 *，也就是没有重新匹配的机会*/
                        && sRecord + 1 < sRight) {
                    // 出现不匹配的字符，需要移动 s 和 p 上的指针，重新进行匹配
                    ++sRecord;
                    sIndex = sRecord;
                    pIndex = pRecord;
                } else {
                    return false;
                }
            }
            return allStars(p, pIndex, pRight);
        }

        public boolean allStars(String str, int left, int right) {
            for (int i = left; i < right; ++i) {
                if (str.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }

        public boolean charMatch(char u, char v) {
            return u == v || v == '?';
        }
    }

}
