package leetcode.editor.cn;

class SudokuSolver {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];
        boolean[][] visited = new boolean[9][9];

        public void solveSudoku(char[][] board) {
            init(board);
            backtrack(board, 0, 0);
        }

        private void init(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') continue;
                    else place(board, i, j, board[i][j]);
                }
            }
        }

        private void place(char[][] board, int i, int j, char ch) {
            visited[i][j] = true;
            board[i][j] = ch;
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
                if (!visited[row][col]) break;
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
                    if ((rows[row] & mask) == 0 && (cols[col] & mask) == 0
                            && (boxes[(row / 3) * 3 + col / 3] & mask) == 0) {
                        place(board, row, col, (char) (num + '0'));
                        if (col == 8) {
                            if (backtrack(board, row + 1, 0)) return true;
                        } else {
                            if (backtrack(board, row, col + 1)) return true;
                        }
                        unplace(board, row, col);
                    }
                }
            } else return true;
            return false;
        }

        private void unplace(char[][] board, int i, int j) {
            visited[i][j] = false;
            int mask = 1 << (board[i][j] - '1');
            rows[i] ^= mask;
            cols[j] ^= mask;
            boxes[(i / 3) * 3 + j / 3] ^= mask;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
