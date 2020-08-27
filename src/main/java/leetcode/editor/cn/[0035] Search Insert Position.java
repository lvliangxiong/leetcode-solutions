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
        public int searchInsert(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            if (nums.length == 1) return target <= nums[0] ? 0 : 1; // 数组仅有一个元素
            if (target >= nums[high]) return target == nums[high] ? high : high + 1;
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
