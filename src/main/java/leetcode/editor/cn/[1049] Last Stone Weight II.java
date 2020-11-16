package leetcode.editor.cn;

import java.util.Arrays;

/**
 * <pre>
 * We have a collection of rocks, each rock has a positive integer weight.
 *
 * Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.
 * The result of this smash is:
 *
 *     If x == y, both stones are totally destroyed;
 *     If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 *
 * At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0
 * if there are no stones left.)
 *
 *
 *
 * Example 1:
 *
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 *
 *
 *
 * Note:
 *
 *     1 <= stones.length <= 30
 *     1 <= stones[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii
 * </pre>
 */
class LastStoneWeightIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 原问题可以等效于找到数组 stones 两个互补子数组的差的最小值，也就是说我们需要找到一个子数组，它的所有元素
         * 之和和整个数组的元素之和的一半最接近。
         * <p>
         * 如果你有求解『数组是否可以被分成两个和相等的子数组』的经验，那么这道题将会比较容易。
         * <p>
         * 我们不妨设我们需要找到的子数组的和是小于等于『所有元素之和』的。
         *
         * @param stones
         * @return
         * @see PartitionEqualSubsetSum.Solution#canPartition(int[])
         */
        public int lastStoneWeightII(int[] stones) {
            int n = stones.length;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += stones[i];
            }

            int target = sum >> 1;
            int[] dp = new int[target + 1]; // dp[j] 表示仅考虑数组前 i 个元素时，该子数组元素之和和目标值 j 的差值
            Arrays.setAll(dp, i -> i); // 初始值设置

            for (int i = 1; i <= n; i++) {
                for (int j = target; j >= stones[i - 1]; j--) {
                    if (j >= stones[i - 1]) dp[j] = Math.min(dp[j], dp[j - stones[i - 1]]);
                    if (j == target && dp[j] == 0) return sum - (target << 1);
                }
            }
            return sum - ((target - dp[target]) << 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DFSSolution {
        public int lastStoneWeightII(int[] stones) {
            int sum = 0;
            for (int stone : stones)
                sum += stone;
            // 尝试挑选数组中的元素，如果能够其和能够刚好等于 i，则停止
            for (int i = (sum >> 1); ; i--) {
                if (helper(stones, 0, 0, i))
                    return sum - 2 * i;
            }
        }

        boolean helper(int[] nums, int idx, int sum, int target) {
            if (sum == target)
                return true;
            if (sum > target)
                return false;
            if (idx == nums.length)
                return false;
            return helper(nums, idx + 1, sum + nums[idx], target)
                    || helper(nums, idx + 1, sum, target);
        }
    }

}
