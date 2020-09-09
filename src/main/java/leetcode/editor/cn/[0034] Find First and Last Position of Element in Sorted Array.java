package leetcode.editor.cn;

/**
 * <pre>
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target
 * value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 *
 *
 * Constraints:
 *
 *     0 <= nums.length <= 10^5
 *     -10^9 <= nums[i] <= 10^9
 *     nums is a non decreasing array.
 *     -10^9 <= target <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * </pre>
 */
class FindFirstAndLastPositionOfElementInSortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int n = nums.length;
            if (n == 0) return new int[]{-1, -1};
            int low = 0, high = n - 1;
            // search for the start
            while (low < high) {
                int start = (low + high) >>> 1;
                if (nums[start] < target) low = start + 1;
                else high = start;
            }
            int start = 0;
            if (nums[low] != target) return new int[]{-1, -1};
            else start = low;
            // search for the end
            low = start;
            high = nums.length - 1;
            while (low < high) {
                int end = (low + high + 1) >>> 1;
                if (nums[end] > target) high = end - 1;
                else low = end;
            }
            return new int[]{start, high};
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
