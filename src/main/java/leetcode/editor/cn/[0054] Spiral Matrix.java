package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class SpiralMatrix {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            int rowStart = 0, colStart = 0;
            int rowEnd = matrix.length - 1, colEnd = matrix[0].length - 1;
            List<Integer> ans = new ArrayList<>();
            while (rowStart <= rowEnd && colStart <= colEnd) {
                iterate(matrix, ans, rowStart, colStart, rowEnd, colEnd);
                rowStart++;
                colStart++;
                rowEnd--;
                colEnd--;
            }
            return ans;
        }

        /**
         * <pre>
         *           colStart . . . . . . . . colEnd
         * rowStart    X                         X
         * .
         * .
         * .
         * .
         * rowEnd      X                         X
         * </pre>
         *
         * @param matrix
         * @param ans
         * @param rowStart
         * @param colStart
         * @param rowEnd
         * @param colEnd
         */
        private void iterate(int[][] matrix, List<Integer> ans, int rowStart, int colStart, int rowEnd, int colEnd) {
            if (rowStart > rowEnd || colStart > colEnd) return;
            for (int col = colStart; col <= colEnd; col++)
                ans.add(matrix[rowStart][col]);
            for (int row = rowStart + 1; row <= rowEnd; row++)
                ans.add(matrix[row][colEnd]);
            if (rowStart == rowEnd || colStart == colEnd) return;
            for (int col = colEnd - 1; col >= colStart; col--)
                ans.add(matrix[rowEnd][col]);
            for (int row = rowEnd - 1; row > rowStart; row--)
                ans.add(matrix[row][colStart]);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}