package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;

class ValidSudoku {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 一次性建立多个 HashSet，使用空间换时间。类似的还可以使用 boolean[][] 数组来标记元素是否已经出现过。
         *
         * @param board
         * @return
         */
        public boolean isValidSudoku(char[][] board) {
            HashSet<Integer>[] rows = new HashSet[9];
            Arrays.setAll(rows, i -> new HashSet<>());

            HashSet<Integer>[] columns = new HashSet[9];
            Arrays.setAll(columns, i -> new HashSet<>());

            HashSet<Integer>[] boxes = new HashSet[9];
            Arrays.setAll(boxes, i -> new HashSet<>());

            // validate a board
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    char ch = board[i][j];
                    if (ch != '.') {
                        int num = ch - '0';
                        int boxIndex = (i / 3) * 3 + j / 3; // 计算该位置 [i, j] 应该在哪个 3x3 的 box 中
                        if (rows[i].contains(num) || columns[j].contains(num) || boxes[boxIndex].contains(num))
                            return false;
                        rows[i].add(num);
                        columns[j].add(num);
                        boxes[boxIndex].add(num);
                    }
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class BitSolution {
        /**
         * 在一些情况下，可以使用 int 类型的数据的位运算代替 HashSet 表示某个数据是否存在。
         * <p>
         * 一个 int 数据有 32 位，可以代表 32 个数字是否出现，可以当作 HashSet 来使用。
         *
         * @param board
         * @return
         */
        public boolean isValidSudoku(char[][] board) {
            int[] rows = new int[9];
            int[] columns = new int[9];
            int[] boxes = new int[9];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    char ch = board[i][j];
                    if (ch != '.') {
                        int num = ch - '0';
                        int boxIndex = (i / 3) * 3 + j / 3;

                        // mask 的第 num-1 位为 1，其它所有位为 0
                        int mask = 1 << num - 1;
                        if ((rows[i] & mask) != 0 || (columns[j] & mask) != 0 || (boxes[boxIndex] & mask) != 0)
                            return false;

                        rows[i] |= mask;
                        columns[j] |= mask;
                        boxes[boxIndex] |= mask;
                    }
                }
            }
            return true;
        }
    }

}
