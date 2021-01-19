package leetcode.editor.cn;

/**
 * <pre>
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid
 * are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * </pre>
 */
class NumberOfIslands {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int count = 0;
        int m, n;

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
            m = grid.length;
            n = grid[0].length;
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    if (grid[row][col] == '1') {
                        dfs(grid, row, col);
                        count++;
                    }
                }
            }
            return count;
        }

        private void dfs(char[][] grid, int row, int col) {
            if (grid[row][col] == '1') {
                grid[row][col] = '*';
                if (row > 0) dfs(grid, row - 1, col);
                if (row < m - 1) dfs(grid, row + 1, col);
                if (col > 0) dfs(grid, row, col - 1);
                if (col < n - 1) dfs(grid, row, col + 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}