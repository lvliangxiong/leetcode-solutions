package leetcode.editor.cn;

/**
 * <pre>
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it
 * impossible to reach the last index.
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 3 * 10^4
 *     0 <= nums[i][j] <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * </pre>
 */
class JumpGame {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canJump(int[] nums) {
            int n = nums.length;
            boolean[] dp = new boolean[n]; // dp[i] 表示从起点位置，是否可以跳跃到 i 位置
            dp[0] = true;
            for (int i = 1; i < n; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (dp[j] && nums[j] >= (i - j)) {
                        // 从起点位置是否可以跳跃到 j，从 j 是否可以跳跃到 i
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[n - 1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class GreedySolution {
        public boolean canJump(int[] nums) {
            int n = nums.length, max = 0/*记录当前棋子可以访问到的最大索引位置*/;
            for (int i = 0; i < n && i <= max; i++) {
                if (nums[i] + i > max) max = nums[i] + i; // 更新 max
            }
            return max >= n - 1;
        }
    }

}
