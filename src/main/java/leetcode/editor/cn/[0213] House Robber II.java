package leetcode.editor.cn;

/**
 * <pre>
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
 * stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last
 * one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if
 * two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you
 * can rob tonight without alerting the police.
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Example 3:
 *
 * Input: nums = [0]
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 100
 *     0 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * </pre>
 */
class HouseRobberIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 将复杂问题简单化：转化为多个简单问题！
         *
         * @param nums
         * @return
         */
        public int rob(int[] nums) {
            if (nums.length == 1) return nums[0];
            return Math.max(rob(nums, 1, nums.length - 1), rob(nums, 0, nums.length - 2));
        }

        /**
         * @param nums
         * @param i
         * @param j
         * @return
         * @see HouseRobber.Solution#rob(int[])
         */
        public int rob(int[] nums, int i, int j) {
            if (i <= j) {
                int rob = nums[i], notRob = 0;

                for (int k = i + 1; k <= j; k++) {
                    int robCur = notRob + nums[k];
                    notRob = Math.max(notRob, rob);
                    rob = robCur;
                }

                return Math.max(rob, notRob);
            }
            return 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}