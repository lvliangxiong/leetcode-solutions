package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <pre>
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending
 * order, then the whole array will be sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * Example 1:
 *
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 *
 * Note:
 *
 *     Then length of the input array is in range [1, 10,000].
 *     The input array may contain duplicates, so ascending order here means <=.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
 * </pre>
 */
class ShortestUnsortedContinuousSubarray {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /**
         * 单调栈
         *
         * @param nums
         * @return
         */
        public int findUnsortedSubarray(int[] nums) {
            Deque<Integer> stack = new ArrayDeque<>();
            int l = nums.length, r = 0;
            for (int i = 0; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                    l = Math.min(l, stack.pop());
                stack.push(i);
            }
            stack.clear();
            for (int i = nums.length - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                    r = Math.max(r, stack.pop());
                stack.push(i);
            }
            return r - l > 0 ? r - l + 1 : 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
