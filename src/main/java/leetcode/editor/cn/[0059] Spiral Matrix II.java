package leetcode.editor.cn;

class SpiralMatrixIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int num = 1;

        public int[][] generateMatrix(int n) {
            int[][] ans = new int[n][n];
            int start = 0, end = n - 1;
            while (start <= end) {
                fill(ans, start, end);
                start++;
                end--;
            }
            return ans;
        }

        private void fill(int[][] matrix, int start, int end) {
            if (start == end) {
                matrix[start][end] = num++;
                return;
            }
            for (int col = start; col < end; col++) {
                matrix[start][col] = num++;
            }
            for (int row = start; row < end; row++) {
                matrix[row][end] = num++;
            }
            for (int col = end; col > start; col--) {
                matrix[end][col] = num++;
            }
            for (int row = end; row > start; row--) {
                matrix[row][start] = num++;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class SimpleSolution {
        public int[][] generateMatrix(int n) {
            int left = 0, right = n - 1, top = 0, bottom = n - 1;
            int[][] ans = new int[n][n];
            int num = 1, max = n * n;
            while (num <= max) {
                for (int i = left; i <= right; i++) ans[top][i] = num++; // left to right.
                top++;
                for (int i = top; i <= bottom; i++) ans[i][right] = num++; // top to bottom.
                right--;
                for (int i = right; i >= left; i--) ans[bottom][i] = num++; // right to left.
                bottom--;
                for (int i = bottom; i >= top; i--) ans[i][left] = num++; // bottom to top.
                left++;
            }
            return ans;
        }
    }

}