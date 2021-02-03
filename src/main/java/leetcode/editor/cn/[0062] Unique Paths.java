package leetcode.editor.cn;

class UniquePaths {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            // 初始化
            for (int col = 0; col < n; col++) {
                dp[m - 1][col] = 1;
            }
            for (int row = 0; row < m; row++) {
                dp[row][n - 1] = 1;
            }
            // 递推
            for (int row = m - 2; row >= 0; row--) {
                for (int col = n - 2; col >= 0; col--) {
                    dp[row][col] = dp[row][col + 1] + dp[row + 1][col];
                }
            }
            return dp[0][0];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 空间优化版本
     */
    class OptimisedSolution {
        public int uniquePaths(int m, int n) {
            int[] dp = new int[n];
            for (int col = 0; col < n; col++) {
                dp[col] = 1;
            }
            for (int row = m - 2; row >= 0; row--) {
                for (int col = n - 2; col >= 0; col--) {
                    dp[col] += dp[col + 1];
                }
            }
            return dp[0];
        }
    }

}
