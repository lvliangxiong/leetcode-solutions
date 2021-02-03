package leetcode.editor.cn;

class UniquePathsIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length, n = obstacleGrid[0].length;
            int[] dp = new int[n];

            for (int col = n - 1; col >= 0; col--) {
                if (obstacleGrid[m - 1][col] == 1) break;
                dp[col] = 1;
            }
            for (int row = m - 2; row >= 0; row--) {
                if (obstacleGrid[row][n - 1] == 1) dp[n - 1] = 0;
                for (int col = n - 2; col >= 0; col--) {
                    if (obstacleGrid[row][col] == 1) dp[col] = 0;
                    else dp[col] += dp[col + 1];
                }
            }
            return dp[0];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}