package leetcode.editor.cn;

/**
 * <pre>
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the
 * sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 *
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * </pre>
 */
class MinimumPathSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int m;
        int n;
        int[][] memo;

        public int minPathSum(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            memo = new int[m][n];
            return dfs(grid, 0, 0);
        }

        /**
         * Return the minimum pathSum from given start to bottom right.
         *
         * @param grid
         * @param i
         * @param j
         * @return
         */
        private int dfs(int[][] grid, int i, int j) {
            if (memo[i][j] != 0) return memo[i][j];
            if (i == m - 1 && j == n - 1) return grid[i][j];
            int sum = Integer.MAX_VALUE;
            if (i < m - 1) sum = Math.min(sum, grid[i][j] + dfs(grid, i + 1, j));
            if (j < n - 1) sum = Math.min(sum, grid[i][j] + dfs(grid, i, j + 1));
            memo[i][j] = sum;
            return sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DPSolution {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int rows = grid.length, columns = grid[0].length;
            int[][] dp = new int[rows][columns];
            dp[0][0] = grid[0][0];
            for (int i = 1; i < rows; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
            for (int j = 1; j < columns; j++) {
                dp[0][j] = dp[0][j - 1] + grid[0][j];
            }
            for (int i = 1; i < rows; i++) {
                for (int j = 1; j < columns; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
            return dp[rows - 1][columns - 1];
        }
    }

}
