package leetcode.editor.cn;

import java.util.Arrays;

/**
 * <pre>
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 * Example:
 *
 * Input:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * Output: 6
 * </pre>
 */
class MaximalRectangle {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 事实上，此解法的第三层循环，是在『纵向』上进行了一次『直方图求最大矩形』的操作，因此可以使用
         * {@link LargestRectangleInHistogram.Solution#largestRectangleAreaByStack(int[])} 对此实现进行简化。
         *
         * @param matrix
         * @return
         */
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;
            if (m == 0) return 0;
            int n = matrix[m - 1].length;
            int[][] dp = new int[m][n];

            int max = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        // compute the maximum width and update dp with it
                        dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
                        int width = dp[i][j];
                        // compute the maximum area rectangle with a lower right corner at [i, j]
                        for (int k = i; k >= 0; k--) {
                            width = Math.min(width, dp[k][j]);
                            max = Math.max(max, width * (i - k + 1));
                        }
                    }
                }
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DpSolution {
        public int maximalRectangle(char[][] matrix) {
            if (matrix.length == 0) return 0;
            int m = matrix.length;
            int n = matrix[0].length;

            // initialize left as the leftmost boundary possible, i.e. 0
            int[] left = new int[n];
            int[] right = new int[n];
            int[] height = new int[n];

            // initialize right as the rightmost boundary possible, i.e. n
            Arrays.fill(right, n);

            int max = 0;
            // iterate row
            for (int i = 0; i < m; i++) {
                int cur_left = 0, cur_right = n;
                // update height
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') height[j]++;
                    else height[j] = 0;
                }
                // update left
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') left[j] = Math.max(left[j], cur_left);
                    else {
                        // 当为字符 0 时，height 为 0，因此 left 和 right 可以随便取值，并不影响最后计算的面积。
                        // 但是为了下次遍历，这里取了 left 的初始值，也就是 0
                        left[j] = 0;
                        cur_left = j + 1;
                    }
                }
                // update right
                for (int j = n - 1; j >= 0; j--) {
                    if (matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
                    else {
                        right[j] = n;
                        cur_right = j;
                    }
                }
                // update area
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, (right[j] - left[j]) * height[j]);
                }
            }
            return max;
        }
    }
}