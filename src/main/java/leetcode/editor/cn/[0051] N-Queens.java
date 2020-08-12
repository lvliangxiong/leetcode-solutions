package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack
 * each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both
 * indicate a queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * </pre>
 */
class NQueens {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int n;

        String[] boards; // 预先构造好 solution 中 String 的枚举，减少运算量

        List<List<String>> solutions = new ArrayList<>();

        int[] queens; // Queen 的放置位置

        boolean[] attackableCols; // 已处于其它 Queen 攻击范围的 col

        boolean[] attackableHills; // 已处于其它 Queen 攻击范围的 45 度斜线

        boolean[] attackableDales; // 已处于其它 Queen 攻击范围的 135 度斜线

        /**
         * Queen 是国际象棋中实力最强的一种棋子，它可以在横、竖、斜方向上，走 1 ~ 7 步，并吃掉该位置上的棋子。
         * 这个 n 皇后问题来源于一个很有名的问题，叫『八皇后问题』。在『八皇后问题』中，任意两个皇后不能在同一
         * 行、同一列和同一斜线上。而这个『n 皇后』问题中的皇后，也做了推广，她应该是可以走 1 ~ n-1 步，也就是
         * 说此问题中，仍然要求任意两个皇后不能在同一行、同一列和同一斜线上。
         * <p>
         * 1. 枚举法，复杂度为 n^n
         * 2. 排列组合，复杂度为 n!
         * 3. 递归回溯，复杂度为 n!
         *
         * @param n
         * @return
         */
        public List<List<String>> solveNQueens(int n) {
            this.n = n;
            this.boards = new String[n];
            this.queens = new int[n];
            this.attackableCols = new boolean[n];
            // 同一条 45 度斜线上 row + col 为定值，范围 [0, 2n-2]
            this.attackableHills = new boolean[n << 1];
            // 同一条 135 度斜线上，row - col 为定值，范围 [-(n-1), n-1]，整体偏移 n，则为 [1, 2n-1]
            this.attackableDales = new boolean[n << 1];

            this.solutions.clear();

            /* boards stores n strings, whose char at index is 'Q, while others are '.'
             * for example, for n = 4, boards is a list of following strings:
             * "Q..."
             * ".Q.."
             * "..Q."
             * "...Q"
             * */
            for (int i = 0; i < n; i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == i) row.append('Q');
                    else row.append('.');
                }
                boards[i] = row.toString();
            }
            place(0);
            return solutions;
        }

        private void place(int row) {
            // 对于第 row 行，有 n 种下子法
            for (int col = 0; col < n; col++) {
                if (isPlaceable(row, col)) {
                    placeQueen(row, col);
                    if (row == n - 1) addSolution();
                    else place(row + 1); // 继续下子
                    removeQueen(row, col); // 回溯
                }
            }
        }

        private void placeQueen(int row, int col) {
            queens[row] = col;
            attackableCols[col] = true;
            attackableHills[col + row] = true;
            attackableDales[col - row + n] = true;
        }

        private void removeQueen(int row, int col) {
            queens[row] = 0;
            attackableCols[col] = false;
            attackableHills[col + row] = false;
            attackableDales[col - row + n] = false;
        }

        private void addSolution() {
            List<String> solution = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                // Queens[i] 表示第 i 行上 Queen 在哪一个位置上
                solution.add(boards[queens[i]]);
            }
            solutions.add(solution);
        }

        /**
         * 每次下子只针对某行下一个 Queen，因此不会出现两个 Queen 在同一行内的情况，只需要考虑两个 Queen 是否
         * 在同一列，同一斜线上即可。
         *
         * @param row
         * @param col
         * @return
         */
        private boolean isPlaceable(int row, int col) {
            if (attackableCols[col] || attackableHills[row + col] || attackableDales[col - row + n]) {
                return false;
            } else {
                return true;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
