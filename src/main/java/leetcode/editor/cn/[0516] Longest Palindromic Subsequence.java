package leetcode.editor.cn;

/**
 * <pre>
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of
 * s is 1000.
 *
 * Example 1:
 * Input:
 *
 * "bbbab"
 *
 * Output:
 *
 * 4
 *
 * One possible longest palindromic subsequence is "bbbb".
 *
 *
 *
 * Example 2:
 * Input:
 *
 * "cbbd"
 *
 * Output:
 *
 * 2
 *
 * One possible longest palindromic subsequence is "bb".
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 1000
 *     s consists only of lowercase English letters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 * </pre>
 */
class LongestPalindromicSubsequence {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @param s
         * @return
         * @see LongestPalindromicSubstring.DpSolution#longestPalindrome(String)
         */
        public int longestPalindromeSubseq(String s) {
            int n = s.length();
            if (n <= 1) return n;
            /* dp[i][j] 表示 s 在 [i, j] 范围内的子串中，最长的回文序列长度。
             * 递推公式：
             * dp[i][j] = dp[i+1][j-1] + 2 if s[i] == s[j] else max(dp[i][j-1], dp[i+1][j])
             * 边界条件：
             * dp[i][i] = 1 and dp[i][i+1] = 2 if s[i] == s[j] else 1
             *
             * ...  dp[i-1][j-1]   dp[i-1][j]   dp[i-1][j+1]
             * ...  dp[i][j-1]     dp[i][j]     dp[i][j+1]
             * ...  dp[i+1][j-1]   dp[i+1][j]   dp[i+1][j+1]
             *
             * */
            int[][] dp = new int[n][n];
            char[] chs = s.toCharArray();
            int ans = -1;
            for (int len = 1; len <= n; len++) {
                for (int start = 0; start + len <= n; start++) {
                    int end = start + len - 1;
                    if (len == 1) {
                        dp[start][end] = 1;
                    } else {
                        boolean flag = chs[start] == chs[end];
                        if (len == 2) {
                            dp[start][end] = flag ? 2 : 1;
                        } else {
                            // len >= 3
                            dp[start][end] = flag ? dp[start + 1][end - 1] + 2 :
                                    Math.max(dp[start + 1][end], dp[start][end - 1]);
                        }
                    }
                }
            }
            return dp[0][n - 1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class OptimisedSolution {
        /**
         * @param s
         * @return
         * @see LongestPalindromicSubstring.OptimisedDpSolution#longestPalindrome(String)
         */
        public int longestPalindromeSubseq(String s) {
            int n = s.length();
            int[] dp = new int[n];
            char[] chs = s.toCharArray();
            for (int i = n - 1; i >= 0; i--) {
                dp[i] = 1; // dp[i][i]
                int bottomLeft = 0; // 对于 dp[i][i+1] 来说，其左下方是 dp[i+1][i] = 0，因此这里初始值为 0
                for (int j = i + 1; j < n; j++) {
                    int temp = dp[j]; // 保存这个位置的原始值，其实就是 dp[i+1][j]
                    if (chs[i] == chs[j]) {
                        dp[j] = bottomLeft + 2; // 这里 bottomLeft 相当于 dp[i+1][j-1]
                    } else {
                        // 这里的 dp[j]相当于 dp[i+1][j]，dp[j-1] 就是 dp[i][j-1]
                        dp[j] = Math.max(dp[j], dp[j - 1]);
                    }
                    bottomLeft = temp; // 更新 bottomLeft，用于下一轮求解 dp
                }
            }
            return dp[n - 1];
        }
    }

}