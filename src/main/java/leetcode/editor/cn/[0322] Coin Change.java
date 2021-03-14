package leetcode.editor.cn;

import java.util.Arrays;

/**
 * <pre>
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute
 * the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 *
 * Example 1:
 *
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Note:
 * </pre>
 */
class CoinChange {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 模板式背包问题解，但是产生了许多根本没有用到的解，速度很慢。
         *
         * @param coins
         * @param amount
         * @return
         */
        public int coinChange(int[] coins, int amount) {
            int len = coins.length;
            if (len == 0) return -1;
            Arrays.sort(coins);
            int[] dp = new int[amount + 1];
            for (int j = 0; j <= amount; j++) {
                if (j % coins[0] == 0) dp[j] = j / coins[0];
                else dp[j] = -1;
            }
            for (int i = 1; i < len; i++) { // you can use [0, i]-th coin in the coins
                for (int j = amount; j >= 0; j--) { // target number of money
                    int most = j / coins[i]; // the most number of i-th coin you can choose
                    for (int k = most; k >= 0; k--) { // iterate possible i-th coin number
                        int count = dp[j - k * coins[i]];
                        if (count != -1)
                            dp[j] = count + k;
                        dp[j] = dp[j] == -1 ? count + k : Math.min(dp[j], count + k);
                    }
                    if (i == len - 1 && j == amount) break;
                }
            }
            return dp[amount];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public class DpSolution {
        /**
         * 自下而上的动态规划。
         * <p>
         * 考虑这样一个问题，对于固定 amount 的钱，我们使用硬币进行兑换，可以将此问题，抽象为『多次选择多个硬币，使得最终的面值
         * 和刚好为 amount』，因此第一次选择时，必然也是 coins 数组中的一种，这里进行枚举：
         * dp[i] = min(dp[i-coins[j]] + 1) where j = [0, ... , n-1]
         * dp[0] = 0
         * dp[i] = -1 when n = 0, i > 0
         *
         * @param coins
         * @param amount
         * @return
         */
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1); // 初始值设置为 amount+1 代表不能组成该数额
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) { // 需要凑出的硬币总额为 i
                for (int j = 0; j < coins.length; j++) { // 枚举第一次选择的硬币面值
                    if (coins[j] <= i) { // 判断是否可以选择 coins[j]
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];
        }
    }

    class DfsSolution {
        int min = Integer.MAX_VALUE;

        /**
         * DFS + cutting
         *
         * @param coins
         * @param amount
         * @return
         */
        public int coinChange(int[] coins, int amount) {
            Arrays.sort(coins); // 先排序，便于剪枝
            dfs(coins.length - 1 /*从大的硬币开始尝试，类似于贪心*/, coins, 0, amount);
            return min == Integer.MAX_VALUE ? -1 : min;
        }

        private void dfs(int index, int[] coins, int count, int remained) {
            if (remained == 0) {
                min = Math.min(count, min);
                return;
            }
            if (index < 0) {
                return;
            }
            int most = remained / coins[index];
            for (int k = most; k >= 0 && count + k < min; k--) {
                dfs(index - 1, coins, count + k, remained - k * coins[index]);
            }
        }
    }
}
