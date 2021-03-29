package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

class LargestRectangleInHistogram {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 枚举柱高，找到其左右边界，计算最大值
         *
         * @param heights
         * @return
         */
        public int largestRectangleArea(int[] heights) {
            int len = heights.length;
            if (len == 0) return 0;
            int ret = 0;
            for (int i = 0; i < len; i++) {
                int h = heights[i];
                int left = i, right = i;
                while (left >= 0 && heights[left] >= h) {
                    left--;
                }
                while (right < len && heights[right] >= h) {
                    right++;
                }
                ret = Math.max(ret, (right - left - 1) * h);
            }
            return ret;
        }

        /**
         * 单调栈实现：单调递增的单调栈
         *
         * @param heights
         * @return
         * @see TrappingRainWater.Solution#trap(int[])
         */
        public int largestRectangleAreaByStack(int[] heights) {
            // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
            int[] tmp = new int[heights.length + 2];
            System.arraycopy(heights, 0, tmp, 1, heights.length);

            Deque<Integer> stack = new ArrayDeque<>(); // 栈中存放柱体的索引位置
            int area = 0;
            for (int i = 0; i < tmp.length; i++) {
                /* 在『当前柱体』进栈之前，将位于栈顶的、高度高于『当前柱体』的柱体出栈：保证入栈之前，栈顶的柱体是『当前柱体』
                 * 左边第一个高度不高于『当前柱体』的柱体。*/
                while (!stack.isEmpty() && tmp[stack.peek()] > tmp[i]) {
                    int h = tmp[stack.pop()];
                    area = Math.max(area, (i - stack.peek() - 1) * h);
                }
                stack.push(i);
            }
            return area;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    class DpSolution {
        /**
         * 利用动态规划，对于每一个柱体，计算出其左右边界。这里计算出『左右边界』的方式其实和单调栈计算左右边界的原理有
         * 异曲同工之妙！
         *
         * @param heights
         * @return
         */
        public int largestRectangleArea(int[] heights) {
            int n = heights.length;
            if (n == 0) return 0;
            if (n == 1) return heights[0];
            // 计算左边界
            int[] start = new int[n];
            start[0] = 0;
            for (int i = 1; i < n; i++) {
                // 对于柱体 i，计算其左边界
                if (heights[i - 1] >= heights[i]) {
                    int j = start[i - 1] - 1;
                    // 跳跃式搜索左边界
                    while (j >= 0 && heights[j] >= heights[i]) {
                        j = start[j] - 1;
                    }
                    start[i] = j + 1;
                } else start[i] = i; // 相邻左柱体就比它低
            }
            // 计算右边界
            int[] end = new int[n];
            end[n - 1] = n - 1;
            for (int i = n - 2; i >= 0; i--) {
                if (heights[i] <= heights[i + 1]) {
                    int j = end[i + 1] + 1;
                    while (j < n && heights[j] >= heights[i]) {
                        j = end[j] + 1;
                    }
                    end[i] = j - 1;
                } else end[i] = i;
            }
            // 遍历柱高，并通过动态规划查询到的左右边界，计算出面积
            int max = 0;
            for (int i = 0; i < n; i++) {
                int tmp = heights[i] * (end[i] - start[i] + 1);
                if (max < tmp) max = tmp;
            }
            return max;
        }
    }
}
