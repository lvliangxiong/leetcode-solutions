package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <pre>
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
 * right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return
 * the max sliding window.
 *
 * Follow up:
 * Could you solve it in linear time?
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 10^5
 *     -10^4 <= nums[i] <= 10^4
 *     1 <= k <= nums.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * </pre>
 */
class SlidingWindowMaximum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * Maintain indices of a monotonic array elements, descending order
         */
        Deque<Integer> deque = new ArrayDeque<>();

        int[] nums;

        private void offer(int i) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);
        }

        public int[] maxSlidingWindow(int[] nums, int k) {
            this.nums = nums;
            for (int i = 0; i < k; i++) {
                offer(i);
            }

            int n = nums.length;
            int[] result = new int[n - k + 1];
            result[0] = nums[deque.peekFirst()]; // First element is always the biggest element in the deque
            for (int i = k, j = 1; i < n; i++, j++) {
                if (deque.peek() == i - k) deque.poll();
                offer(i);
                result[j] = nums[deque.peekFirst()];
            }
            return result;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DPSolution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int[] left = new int[n], right = new int[n];
            for (int i = 0; i < n; i++) {
                if (i % k == 0) left[i] = nums[i];
                else left[i] = Math.max(left[i - 1], nums[i]);

                int j = (n - 1 - i);
                if (j % k == k - 1 || i == 0) right[j] = nums[j];
                else right[j] = Math.max(right[j + 1], nums[j]);
            }

            int[] ret = new int[n - k + 1];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = Math.max(right[i], left[i + k - 1]);
            }
            return ret;
        }
    }

}
