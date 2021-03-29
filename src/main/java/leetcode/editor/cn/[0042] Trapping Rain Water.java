package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

class TrappingRainWater {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 单调栈，维护一个单调递减的栈结构，一旦出现高于栈顶元素的元素出现，将弹出所有低于该元素的栈顶元素，再将该元素入栈。
         * <p>
         * 如果当前柱体的高度小于或等于栈顶的柱体的高度，我们将该柱体的索引入栈，意思是当前的柱体上的雨水被栈中的前一个条形块界定。
         * 如果我们发现当前柱体的高度高于栈顶，我们可以确定栈顶的条形块被当前柱体和栈的前一个柱体界定。
         *
         * @param height
         * @return
         * @see LargestRectangleInHistogram.Solution#largestRectangleAreaByStack(int[])
         */
        public int trap(int[] height) {
            if (height == null || height.length <= 2) return 0;
            Deque<Integer> stack = new ArrayDeque<>();
            int count = 0;
            int current = 0;
            while (current < height.length) {
                while (!stack.isEmpty() && height[stack.peek()] < height[current]) {
                    // 左边界为栈中栈顶柱体的前一个柱体，右边界为当前柱体
                    int top = stack.pop();
                    if (stack.isEmpty())
                        break;
                    int distance = current - stack.peek() - 1; // 『当前柱体』为右边界
                    // 高度，注意这里要减去一个 height[top]
                    int boundedHeight = Math.min(height[current], height[stack.peek()]) - height[top];
                    count += distance * boundedHeight;
                }
                stack.push(current++); // 将当前柱体的索引压栈，并将当前柱体指向下一个柱体
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DpSolution {
        public int trap(int[] height) {
            if (height == null || height.length == 0)
                return 0;
            int ans = 0;
            int size = height.length;
            int[] leftMax = new int[size];
            int[] rightMax = new int[size];
            leftMax[0] = height[0];
            for (int i = 1; i < size; i++) {
                leftMax[i] = Math.max(height[i], leftMax[i - 1]);
            }
            rightMax[size - 1] = height[size - 1];
            for (int i = size - 2; i >= 0; i--) {
                rightMax[i] = Math.max(height[i], rightMax[i + 1]);
            }
            for (int i = 1; i < size - 1; i++) {
                // 思考这里为什么是 leftMax[i] 和 rightMax[i] 而不是 leftMax[i-1] 和 rightMax[i+1]？
                ans += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
            return ans;
        }
    }

    class TwoPointerSolution {
        public int trap(int[] height) {
            int ans = 0;
            /*
             * leftMax：left 左边所有柱体高度的最大值，它是从左往右遍历找到的。
             * rightMax：right 右边所有柱体高度的最大值，它是从右往左遍历找到的。
             * left：从左往右处理的当前下标。
             * right：从右往左处理的当前下标。
             *
             * 1. 在某个位置 i 处，它能存的水，取决于它左右两边柱体高度的最大值中较小的一个。
             * 2. 当我们从左往右处理到 left 下标时，左边的最大值 leftMax 对它而言是可信的，但 rightMax 对它而言是不可信的。
             * 3. 当我们从右往左处理到 right 下标时，右边的最大值 rightMax 对它而言是可信的，但 leftMax 对它而言是不可信的。
             * */
            int left = 0, right = height.length - 1;
            int leftMax = 0, rightMax = 0;
            while (left < right) {
                /*
                 * 由于总是优先处理较低的柱体，因此较低柱体一侧的所有已处理的柱体的高度必定全部低于较高的柱体！因此有：
                 * if height[left] < height[right], then leftMax < height[right]
                 * if height[left] > height[right], then rightMax < height[left]
                 * 因此，较低的柱体的能够存储的水量必定由自身那一侧的 max 和自身的高度决定！
                 * */
                if (height[left] < height[right]) {
                    // 处理左边的柱体，即 left
                    if (height[left] >= leftMax) {
                        leftMax = height[left]; // 更新 leftMax，并且由于原先的 leftMax 太低，导致 left 处无法蓄水
                    } else {
                        ans += (leftMax - height[left]);
                    }
                    ++left;
                } else {
                    // 处理右边的柱体，即 right
                    if (height[right] >= rightMax) {
                        rightMax = height[right];
                    } else {
                        ans += (rightMax - height[right]);
                    }
                    --right;
                }
            }
            return ans;
        }
    }
}
