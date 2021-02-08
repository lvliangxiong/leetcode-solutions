package leetcode.editor.cn;

/**
 * <pre>
 * You are given an integer array nums sorted in ascending order (not necessarily distinct values), and an integer
 * target.
 *
 * Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,4,4,5,6,6,7] might become
 * [4,5,6,6,7,0,1,2,4,4]).
 *
 * If target is found in the array return true, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 *
 * Example 2:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 5000
 *     -10^4 <= nums[i] <= 10^4
 *     nums is guaranteed to be rotated at some pivot.
 *     -10^4 <= target <= 10^4
 *
 *
 * Follow up: This problem is the same as Search in Rotated Sorted Array, where nums may contain duplicates. Would this
 * affect the run-time complexity? How and why?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
 * </pre>
 */
class SearchInRotatedSortedArrayIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean search(int[] nums, int target) {
            int n = nums.length;
            if (n == 1) return nums[0] == target;
            int low = 0, high = n - 1;
            if (target < nums[0] && target > nums[n - 1]) return false;
            if (target == nums[0]) return true;
            boolean targetInFirstHalf = target > nums[0];
            while (low < high) {
                // split to two sub array at mid, i.e. [0, mid] and [mid+1, n-1]
                int mid = (low + high) >>> 1;
                if (nums[mid] == target) return true;
                if (nums[mid] == nums[0]) {
                    // scan element by element
                    for (int i = low; i <= high; i++) {
                        if (nums[i] == target) return true;
                    }
                    return false;
                } else if (nums[mid] > nums[0]) {
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
            return nums[low] == target ? true : false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}