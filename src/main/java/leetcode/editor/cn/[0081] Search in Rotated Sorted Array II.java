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
            if (null == nums || nums.length == 0)
                return false;

            int low = 0, high = nums.length - 1;
            while (low < high) {
                // to avoid duplicates
                while (low < high && nums[low] == nums[low + 1])
                    ++low;
                while (low < high && nums[high] == nums[high - 1])
                    --high;

                // 循环之后，low <= high
                if (low == high) break;

                // the code below is exactly the same with Problem 33.
                int mid = (low + high) >>> 1;
                if (nums[mid] == target)
                    return true;

                if (nums[mid] >= nums[low]) {
                    // mid locates in the first half
                    if (target >= nums[low] && target < nums[mid])
                        high = mid;
                    else
                        low = mid + 1;
                } else {
                    // mid locates in the latter half
                    if (target <= nums[high] && target > nums[mid])
                        low = mid + 1;
                    else
                        high = mid;
                }
            }
            return nums[low] == target;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}