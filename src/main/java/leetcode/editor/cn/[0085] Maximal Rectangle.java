package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

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
            // 矩阵第 i 行第 j 列元素的左边连续字符 1 的数量
            int[][] leftOneCount = new int[m][n];

            int max = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        // compute the maximum width and update leftOneCount with it
                        leftOneCount[i][j] = j == 0 ? 1 : leftOneCount[i][j - 1] + 1;
                        int width = leftOneCount[i][j];
                        // compute the maximum area rectangle with a lower right corner at [i, j]
                        for (int k = i; k >= 0 && matrix[k][j] == '1'; k--) {
                            width = Math.min(width, leftOneCount[k][j]);
                            max = Math.max(max, width * (i - k + 1));
                        }
                    }
                }
            }
            return max;
        }

        /**
         * @param matrix
         * @return
         */
        public int maximalRectangleByStack(char[][] matrix) {
            int m = matrix.length;
            if (m == 0) {
                return 0;
            }
            int n = matrix[0].length;
            int[][] leftOneCount = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        leftOneCount[i][j] = (j == 0 ? 0 : leftOneCount[i][j - 1]) + 1;
                    }
                }
            }

            int ret = 0;
            for (int j = 0; j < n; j++) {
                // 对于每一列，使用基于柱状图的方法。
                // leftOneCount[.][j] 相当于一个旋转了 90 度的柱状图。
                int[] up = new int[m];
                int[] down = new int[m];

                // 利用单调栈结构，获取上边界（向上查找第一个柱高小于当前柱高的位置）
                Deque<Integer> stack = new LinkedList<>();
                for (int i = 0; i < m; i++) {
                    while (!stack.isEmpty() && leftOneCount[stack.peek()][j] >= leftOneCount[i][j]) {
                        stack.pop();
                    }
                    up[i] = stack.isEmpty() ? -1 : stack.peek();
                    stack.push(i);
                }
                stack.clear();
                // 相同的方式，反向遍历一次
                for (int i = m - 1; i >= 0; i--) {
                    while (!stack.isEmpty() && leftOneCount[stack.peek()][j] >= leftOneCount[i][j]) {
                        stack.pop();
                    }
                    down[i] = stack.isEmpty() ? m : stack.peek();
                    stack.push(i);
                }

                for (int i = 0; i < m; i++) {
                    int width = down[i] - up[i] - 1;
                    int area = width * leftOneCount[i][j] /*柱高*/;
                    ret = Math.max(ret, area);
                }
            }
            return ret;
        }
        //leetcode submit region end(Prohibit modification and deletion)

        class DpSolution {
            /**
             * 类似于 {@link LargestRectangleInHistogram.Solution#largestRectangleArea(int[])} 这种遍历柱体高的做法。
             *
             * @param matrix
             * @return
             */
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
                    int curLeft = 0, curRight = n;
                    // update height
                    for (int j = 0; j < n; j++) {
                        if (matrix[i][j] == '1') height[j]++;
                        else height[j] = 0;
                    }
                    // 对于当前柱高，找到其可能的左边界，左边界最多在 left[j]，和 curLeft 取较近的一个（也就是较大的一个）
                    for (int j = 0; j < n; j++) {
                        if (matrix[i][j] == '1') left[j] = Math.max(left[j], curLeft);
                        else {
                            // 当为字符 0 时，height 为 0，因此 left 和 right 可以随便取值，并不影响最后计算的面积。
                            // 但是为了方便下次遍历，这里取了 left 的初始值，也就是 0
                            left[j] = 0;
                            curLeft = j + 1;
                        }
                    }
                    // 对于当前柱高，找到其可能的右边界，右边界最多在 right[j]，和 curRight 取较近的一个（也就是较小的一个）
                    for (int j = n - 1; j >= 0; j--) {
                        if (matrix[i][j] == '1') right[j] = Math.min(right[j], curRight);
                        else {
                            right[j] = n;
                            curRight = j;
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
}