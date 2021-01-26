package leetcode.editor.cn;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * <pre>
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps
 * to the last index.
 *
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 3 * 10^4
 *     0 <= nums[i] <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * </pre>
 */
class JumpGameIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * Greedy
         * <p>
         * O(n^2)
         *
         * @param nums
         * @return
         */
        public int jump(int[] nums) {
            int position = nums.length - 1;
            int steps = 0;
            while (position > 0) {
                // 找到第一个能够到达位置 position 的索引
                for (int i = 0; i < position; i++) {
                    if (i + nums[i] >= position) {
                        position = i;
                        steps++;
                        break;
                    }
                }
            }
            return steps;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class BFSSolution {

        public int jump(int[] nums) {
            int n = nums.length, max = 0;
            if (n == 1) return 0;
            Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
            queue.offer(new Pair<>(0, 0));
            while (!queue.isEmpty()) {
                Pair<Integer, Integer> pair = queue.poll();
                int index = pair.getKey();
                int step = pair.getValue();

                step++;
                if (nums[index] + index >= n - 1) return step;
                for (int i = nums[index] + index; i > max; i--) {
                    queue.offer(new Pair<>(i, step));
                }
                max = Math.max(max, nums[index] + index);
            }
            return 0;
        }
    }

    /**
     * O(n)
     * 类似于 BFS
     */
    class GreedySolution {
        public int jump(int[] nums) {
            int length = nums.length;
            int end = 0;
            int maxPosition = 0;
            int steps = 0;
            for (int i = 0; i < length - 1; i++) {
                maxPosition = Math.max(maxPosition, i + nums[i]);

                // 调整更新 steps 的边界条件
                if (i == end) {
                    end = maxPosition;
                    steps++;
                }
            }
            return steps;
        }
    }

}