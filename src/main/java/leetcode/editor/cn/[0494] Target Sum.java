package leetcode.editor.cn;

/**
 * <pre>
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For
 * each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example 1:
 *
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 *
 *
 *
 * Constraints:
 *
 *     The length of the given array is positive and will not exceed 20.
 *     The sum of elements in the given array will not exceed 1000.
 *     Your output answer is guaranteed to be fitted in a 32-bit integer.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * </pre>
 */
class TargetSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findTargetSumWays(int[] nums, int S) {
            if (S > 1000) return 0;
            int[][] dp = new int[nums.length][2001];
            dp[0][nums[0] + 1000] = 1;
            dp[0][-nums[0] + 1000] += 1;
            for (int i = 1; i < nums.length; i++) {
                for (int sum = -1000; sum <= 1000; sum++) {
                    if (dp[i - 1][sum + 1000] > 0) {
                        dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                        dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                    }
                }
            }
            return dp[nums.length - 1][S + 1000];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class OptimisedSolution {
        public int findTargetSumWays(int[] nums, int S) {
            int[] dp = new int[2001];
            dp[nums[0] + 1000] = 1;
            dp[-nums[0] + 1000] += 1;
            for (int i = 1; i < nums.length; i++) {
                int[] next = new int[2001];
                for (int sum = -1000; sum <= 1000; sum++) {
                    if (dp[sum + 1000] > 0) {
                        next[sum + nums[i] + 1000] += dp[sum + 1000];
                        next[sum - nums[i] + 1000] += dp[sum + 1000];
                    }
                }
                dp = next;
            }
            return S > 1000 ? 0 : dp[S + 1000];
        }
    }

}