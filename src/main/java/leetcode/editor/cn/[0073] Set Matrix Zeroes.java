package leetcode.editor.cn;

class SetMatrixZeroes {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * Idea 1: Using an auxiliary matrix to memorise places should be set to 0.
         * Idea 2: Using two auxiliary array to memorise rows and cols should be set to 0.
         * Idea 3: Using
         *
         * @param matrix
         */
        public void setZeroes(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            int fc = -1;
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    if (matrix[row][col] == 0) {
                        // 行首进行标记
                        matrix[row][0] = 0;
                        // 列首进行标记
                        if (col == 0) fc = 0;
                        else matrix[0][col] = 0;
                    }
                }
            }
            for (int row = 1; row < m; row++) {
                if (matrix[row][0] == 0) {
                    for (int col = 0; col < n; col++) {
                        matrix[row][col] = 0;
                    }
                }
            }
            for (int col = 1; col < n; col++) {
                if (matrix[0][col] == 0) {
                    for (int row = 1; row < m; row++) {
                        matrix[row][col] = 0;
                    }
                }
            }
            if (matrix[0][0] == 0) {
                for (int col = 1; col < n; col++) {
                    matrix[0][col] = 0;
                }
            }
            if (fc == 0) {
                for (int row = 0; row < m; row++) {
                    matrix[row][0] = 0;
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
