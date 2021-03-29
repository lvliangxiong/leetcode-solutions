package leetcode.editor.cn;

class SudokuSolver {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 类似于 N-Queens 问题的解法，也就是回溯！
     *
     * @see NQueens.Solution#solveNQueens(int)
     */
    class Solution {
        // 这里使用整形数的低 9 位作为一个 HashSet，快速判断某一行或者某一列或者某一 box 是否已经包含了某个数（0-9）。
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];

        boolean[][] filled = new boolean[9][9]; // 记录已经填充的位置

        public void solveSudoku(char[][] board) {
            init(board);
            backtrack(board, 0, 0);
        }

        private void init(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') continue;
                    else fill(board, i, j, board[i][j]);
                }
            }
        }

        private void fill(char[][] board, int i, int j, char ch) {
            board[i][j] = ch;
            filled[i][j] = true;
            // 使用 int 类型的低 9 位记录该 row，或者 col，或者 box 内已经包含的数字
            int mask = 1 << (ch - '1');
            rows[i] |= mask;
            cols[j] |= mask;
            boxes[(i / 3) * 3 + j / 3] |= mask;
        }

        private boolean backtrack(char[][] board, int row, int col) {
            // successfully stop condition
            if (row == 9 && col == 0) return true;
            // search for an unvisited place to place numbers
            while (row < 9 && col < 9) {
                if (!filled[row][col]) break;
                else {
                    if (col == 8) {
                        row++;
                        col = 0;
                    } else col++;
                }
            }
            // trial and error
            if (row < 9 && col < 9) {
                for (int num = 1; num <= 9; num++) {
                    int mask = 1 << (num - 1);
                    if ((rows[row] & mask) == 0 /*第 row 行还不包含 num*/
                            && (cols[col] & mask) == 0 /*第 col 列还不包含 num*/
                            && (boxes[(row / 3) * 3 + col / 3] & mask) == 0) {
                        fill(board, row, col, (char) (num + '0')); // 尝试在 [row,col] 位置填入 num
                        if (col == 8) {
                            if (backtrack(board, row + 1, 0)) return true;
                        } else {
                            if (backtrack(board, row, col + 1)) return true;
                        }
                        clear(board, row, col); // 回溯，清空刚才填入的数字，尝试下一个可能的数字
                    }
                }
                return false;
            } else return true;
        }

        private void clear(char[][] board, int i, int j) {
            filled[i][j] = false;
            int mask = 1 << (board[i][j] - '1');
            rows[i] ^= mask;
            cols[j] ^= mask;
            boxes[(i / 3) * 3 + j / 3] ^= mask;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
