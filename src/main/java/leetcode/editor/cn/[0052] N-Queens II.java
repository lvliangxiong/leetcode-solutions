package leetcode.editor.cn;

/**
 * <pre>
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * Example:
 *
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
 * [
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
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens-ii
 * </pre>
 */
class NQueensIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int count = 0;
        int n;

        public int totalNQueens(int n) {
            this.n = n;
            helper(0, 0, 0, 0);
            return count;
        }

        /**
         * 使用 int 类型数据的二进制位保存处于 Queue 攻击范围的列。只有低 n 位是有效的。
         *
         * @param row
         * @param columns
         * @param hills
         * @param dales
         */
        public void helper(int row, int columns, int hills, int dales) {
            if (row == n) {
                count++;
            } else {
                /* columns | hills | dales 的二进制表示中，1 代表该位置（列）处于其它 Queen 的攻击范围。
                 * 取反后， 1 表示不处于其它 Queen 的攻击范围的列，是可以放置 Queen 的。
                 * (1 << n) - 1 的结果就是低位是 n 个 1，高位全是 0 的整数。
                 * 这里使用 (1 << n) - 1 与取反的结果进行按位与，可以消除取反时产生的多余的高位 1（将多余的高位置为 0）。*/
                int availablePositions = ((1 << n) - 1) & (~(columns | hills | dales));
                while (availablePositions != 0) {
                    /* x & (−x) 可以获得 x 的二进制表示中的最低位的 1 的位置，此时 position 的二进制表示中的唯一一个 1 即为
                     * Queen 应该被放置的位置。*/
                    int position = availablePositions & (-availablePositions);
                    /* x & (x−1) 可以将 x 的二进制表示中的最低位的 1 置成 0，表示该位置已经被放置了 Queue，下一次循环需要尝试
                     * 其它位置。*/
                    availablePositions = availablePositions & (availablePositions - 1);

                    // 计算被放置了 Queue 的位置的索引（这里只是给出这样一个方法可以，在这个 Problem 中并没有用到）
                    int column = Integer.bitCount(position - 1);

                    helper(row + 1/*下一行*/,
                            columns | position/*新增的 Queue 的列不能再放置 Queue*/,
                            (hills | position) << 1/*行数增 1，hills 被占据的位置整体左移 1 位，等效于 45 度斜线*/,
                            (dales | position) >> 1);
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}