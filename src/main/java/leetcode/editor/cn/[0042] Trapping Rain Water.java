package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

class TrappingRainWater {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 单调栈
         *
         * @param height
         * @return
         */
        public int trap(int[] height) {
            if (height == null || height.length <= 2) return 0;
            Deque<Integer> stack = new ArrayDeque<>();
            int count = 0;
            int current = 0;
            while (current < height.length) {
                while (!stack.isEmpty() && height[stack.peek()] < height[current]) {
                    // 当前柱体的高度高于栈顶柱体的高度，此时栈顶柱体处的雨水数量，
                    // 由『当前柱体』和『栈顶柱体的前一个柱体』的高度以及它们之间的距离决定。
                    int top = stack.pop(); // 栈顶柱体
                    if (stack.isEmpty())
                        break;
                    int distance = current - stack.peek() - 1; // 宽度
                    // 边界高度，由较低者决定，注意这里要减去一个 height[top]
                    int boundedHeight =
                            Math.min(height[current], height[stack.peek()]) - height[top];
                    count += distance * boundedHeight;
                }
                stack.push(current++); // 将当前柱体的索引压栈，并将当前柱体指向下一个柱体
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
