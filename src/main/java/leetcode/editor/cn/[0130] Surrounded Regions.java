package leetcode.editor.cn;

/**
 * <pre>
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * Explanation:
 *
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped
 * to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 *
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * </pre>
 */
class SurroundedRegions {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        boolean[][] open;
        int rows;
        int cols;

        public void solve(char[][] board) {
            if (board == null || board.length <= 1) return;
            rows = board.length;
            cols = board[0].length;
            open = new boolean[rows][cols];

            // 从矩阵的边界出发，进行 DFS，标记所有和边界相连的位置
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (row == 0 || row == rows - 1) {
                        dfs(board, row, col);
                    } else if (col == 0 || col == cols - 1) {
                        dfs(board, row, col);
                    }
                }
            }
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    // 不是 open 区域，且该位置字符为 'O'，被擦除
                    if (!open[row][col] && board[row][col] == 'O') board[row][col] = 'X';
                }
            }
        }

        private void dfs(char[][] board, int row, int col) {
            if (board[row][col] == 'X') return;
            if (open[row][col]) return; // 已被标记过，直接返回
            // 还未被标记为 open 区域，且该位置上是字符 'O'
            open[row][col] = true;
            if (row - 1 >= 0) dfs(board, row - 1, col);
            if (row + 1 < rows) dfs(board, row + 1, col);
            if (col - 1 >= 0) dfs(board, row, col - 1);
            if (col + 1 < cols) dfs(board, row, col + 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
