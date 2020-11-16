package leetcode.editor.cn;

class RotateImage {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            // fold along the major diagonal, i.e. matrix[i][j] <==> matrix[j][i]
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    swap(matrix, i, j, j, i);
                }
            }
            // then fold along the x axes, i.e. matrix[i][j] <==> matrix[i][n-1-j]
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < (n >> 1); j++) {
                    swap(matrix, i, j, i, n - 1 - j);
                }
            }
        }

        private void swap(int[][] matrix, int i, int j, int k, int l) {
            int tmp = matrix[i][j];
            matrix[i][j] = matrix[k][l];
            matrix[k][l] = tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
