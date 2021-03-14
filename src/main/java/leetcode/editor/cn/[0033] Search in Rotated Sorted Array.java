package leetcode.editor.cn;

/**
 * <pre>
 * You are given an integer array nums sorted in ascending order, and an integer target.
 *
 * Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become
 * [4,5,6,7,0,1,2]).
 *
 * If target is found in the array return its index, otherwise, return -1.
 *
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 *
 * Input: nums = [1], target = 0
 * Output: -1
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 5000
 *     -10^4 <= nums[i] <= 10^4
 *     All values of nums are unique.
 *     nums is guaranteed to be rotated at some pivot.
 *     -10^4 <= target <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * </pre>
 */
class SearchInRotatedSortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 变形的二分法
         *
         * @param nums
         * @param target
         * @return
         */
        public int search(int[] nums, int target) {
            int n = nums.length;
            if (n == 1) return nums[0] == target ? 0 : -1;
            int low = 0, high = n - 1;
            if (target < nums[0] && target > nums[n - 1]) return -1;
            boolean targetInFirstHalf = target >= nums[0];
            while (low < high) {
                // split to two sub array at mid, i.e. [low, mid] and [mid+1, high]
                int mid = (low + high) >>> 1;
                if (nums[mid] == target) return mid;
                if (nums[mid] >= nums[0]) {
                    // mid locates at first half
                    if (!targetInFirstHalf || nums[mid] < target)
                        low = mid + 1;
                    else
                        high = mid;
                } else {
                    // mid locates at latter half
                    if (targetInFirstHalf || nums[mid] > target)
                        high = mid;
                    else
                        low = mid + 1;
                }
            }
            return nums[low] == target ? low : -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
