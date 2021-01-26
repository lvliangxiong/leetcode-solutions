package leetcode.editor.cn;

/**
 * <pre>
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it
 * would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 * Input: [1,3,5,6], 5
 * Output: 2
 *
 * Example 2:
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 *
 * Example 3:
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 *
 * Example 4:
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * </pre>
 */
class SearchInsertPosition {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @param nums
         * @param target
         * @return
         * @see FindFirstAndLastPositionOfElementInSortedArray.Solution#indexOf(int[], int)
         */
        public int searchInsert(int[] nums, int target) {
            if (nums == null) return -1;
            int len = nums.length;
            if (len == 0) return 0;

            int low = 0, high = len - 1;
            if (target <= nums[low]) return 0;
            if (target >= nums[high]) return target == nums[high] ? high : high + 1;

            // 数组中至少有两个元素，且 nums[0] < target < nums[len-1]
            while (low < high) {
                int mid = (low + high) >>> 1;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
