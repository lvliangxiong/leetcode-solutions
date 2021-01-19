package leetcode.editor.cn;

import java.util.Arrays;

/**
 * <pre>
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to
 * target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * Constraints:
 *
 *     3 <= nums.length <= 10^3
 *     -10^3 <= nums[i] <= 10^3
 *     -10^4 <= target <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * </pre>
 */
class ThreeSumClosest {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            int n = nums.length;
            assert n >= 3 : "Wrong Argument";
            int minDifference = Integer.MAX_VALUE; // 当前状态下，『已查找到的最接近 target 的三数之和』和『target』的差的绝对值
            int ans = -1;
            Arrays.sort(nums);
            for (int i = 0; i < n - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int j = i + 1, k = n - 1;
                while (j < k) {
                    int first = nums[i], second = nums[j], third = nums[k];
                    int sum = first + second + third;
                    int diff = Math.abs(target - sum);
                    if (diff < minDifference) {
                        ans = sum;
                        minDifference = diff;
                    }
                    if (sum == target) {
                        return target;
                    } else if (sum < target) {
                        while (j < k && nums[j] == second) j++;
                    } else {
                        while (j < k && nums[k] == third) k--;
                    }
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
