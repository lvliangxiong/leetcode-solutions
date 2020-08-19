package leetcode.editor.cn;

/**
 * <pre>
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two
 * subsets such that the sum of elements in both subsets is equal.
 *
 * Note:
 *
 *     Each of the array element will not exceed 100.
 *     The array size will not exceed 200.
 *
 *
 *
 * Example 1:
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 *
 *
 * Example 2:
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * </pre>
 */
class PartitionEqualSubsetSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 0-1 背包问题
         *
         * @param nums
         * @return
         */
        public boolean canPartition(int[] nums) {
            int n = nums.length;
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if ((sum & 1) == 1) return false; // 总和为奇数，直接返回 false
            int target = sum >> 1;

            // dp[i][j] 表示使用 nums 的前 i 项是否可以刚好装满背包（容量为 j）
            boolean[][] dp = new boolean[n + 1][target + 1];
            dp[0][0] = true;
            for (int i = 1; i <= n; i++) {
                for (int j = target; j >= 0; j--) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= nums[i - 1])
                        dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                    if (j == target && dp[i][j]) return true;
                }
            }
            return dp[n][target];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DPSolutionOptimised {
        public boolean canPartition(int[] nums) {
            int n = nums.length;
            int[] sums = new int[n + 1]; // 累计和，sums[i] 表示原 nums 前 i 项的和
            int sum = 0;
            for (int i = 0; i < n; ) {
                sum += nums[i];
                i++;
                sums[i] = sum;
            }
            int target = sum >> 1; // 背包容量
            if ((sum & 1) == 1) return false;

            boolean[] dp = new boolean[target + 1]; // 简化为『一维数组』
            dp[0] = true;

            for (int i = 1; i <= n; i++) {
                for (int j = Math.min(sums[i], target); j >= nums[i - 1]; j--) {
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                    if (j == target && dp[j]) return true;
                }
            }
            return dp[target];
        }
    }
}
