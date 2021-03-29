package leetcode.editor.cn;

import java.util.Arrays;

/**
 * <pre>
 * Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple
 * values have the same frequency, sort them in decreasing order.
 *
 * Return the sorted array.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,2,2,3]
 * Output: [3,1,1,2,2,2]
 * Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
 *
 * Example 2:
 *
 * Input: nums = [2,3,1,3,2]
 * Output: [1,3,3,2,2]
 * Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
 *
 * Example 3:
 *
 * Input: nums = [-1,1,-6,4,5,-6,1,4,1]
 * Output: [5,-1,4,4,-6,-6,1,1,1]
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 100
 *     -100 <= nums[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-increasing-frequency
 * </pre>
 */
class SortArrayByIncreasingFrequency {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] frequencySort(int[] nums) {
            // 统计频次
            int[] counts = new int[201];
            for (int num : nums) {
                counts[num + 100]++;
            }
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                // 保证频次越大，映射的数值越大，排序后在越后面
                // 保证相同频次的数，原值较大的映射的数值较小，排序靠前
                nums[i] = counts[num + 100] * 201 + (100 - num); // 这里 +100，方便后续取余
            }
            Arrays.sort(nums);
            // 还原
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                nums[i] = 100 - (num % 201);
            }
            return nums;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}