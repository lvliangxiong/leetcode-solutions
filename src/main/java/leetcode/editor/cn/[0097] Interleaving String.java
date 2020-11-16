package leetcode.editor.cn;

/**
 * <pre>
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * Example 1:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 *
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 * </pre>
 */
class InterleavingString {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 备忘录，存放 dfs 计算的中间结果，避免重复计算。
         */
        Integer[][] memo;

        public boolean isInterleave(String s1, String s2, String s3) {
            int n = s3.length();
            int n1 = s1.length();
            int n2 = s2.length();
            if (n1 + n2 != n) return false;
            memo = new Integer[s1.length() + 1][s2.length() + 1];
            return dfs(s1, 0, s2, 0, s3, 0);
        }

        private boolean dfs(String s1, int i, String s2, int j, String s3, int k) {
            if (memo[i][j] != null) return memo[i][j] == 1;
            boolean ret = false;
            if (s3.length() == k) {
                memo[i][j] = 1;
                return true;
            }
            if (s1.length() > i) {
                // 选择 s1
                ret = (s1.charAt(i) == s3.charAt(k) && dfs(s1, i + 1, s2, j, s3, k + 1));
            }
            if (s2.length() > j) {
                // 选择 s2
                ret = ret || (s2.charAt(j) == s3.charAt(k) && dfs(s1, i, s2, j + 1, s3, k + 1));
            }
            memo[i][j] = ret ? 1 : 0;
            return ret;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DpSolution {
        /**
         * <pre>
         * dp[i][j] 表示 s3 的前 i + j 个字符是否能被 s1 的前 i 个字符和 s2 的前 j 个字符交错组成。
         * 有：dp[i][j] = s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j] ||
         *               s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1]
         * </pre>
         *
         * @param s1
         * @param s2
         * @param s3
         * @return
         */
        public boolean isInterleave(String s1, String s2, String s3) {
            int n = s3.length();
            int n1 = s1.length();
            int n2 = s2.length();
            if (n != n1 + n2) return false;
            boolean[][] dp = new boolean[n1 + 1][n2 + 1];
            dp[0][0] = true;
            for (int i = 0; i <= n1; i++) {
                for (int j = 0; j <= n2; j++) {
                    if (i > 0) {
                        dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    }
                    if (j > 0) {
                        dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                    }
                }
            }
            return dp[n1][n2];
        }

        /**
         * 使用滚动数组优化空间
         *
         * @param s1
         * @param s2
         * @param s3
         * @return
         */
        public boolean isInterleaveOptimized(String s1, String s2, String s3) {
            int n = s3.length();
            int n1 = s1.length();
            int n2 = s2.length();
            if (n != n1 + n2) return false;
            boolean[] dp = new boolean[n2 + 1];
            dp[0] = true;
            for (int i = 0; i <= n1; i++) {
                for (int j = 0; j <= n2; j++) {
                    if (i > 0) {
                        dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                    }
                    if (j > 0) {
                        dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                    }
                }
            }
            return dp[n2];
        }
    }
}
