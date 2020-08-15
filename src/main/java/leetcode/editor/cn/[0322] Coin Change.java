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
            for (int i = 0; i <= amount; i++) {
                if (i % coins[0] == 0) dp[i] = i / coins[0];
                else dp[i] = -1;
            }
            for (int i = 1; i < len; i++) { // you can use [0, i]-th coin in the coins
                for (int j = amount; j >= 0; j--) { // target number of money
                    int most = j / coins[i]; // the most number of i-th coin you can choose
                    for (int k = most; k >= 0; k--) { // iterate possible i-th coin number
                        int count = dp[j - k * coins[i]];
                        if (count != -1)
                            dp[j] = dp[j] == -1 ? count + k : Math.min(dp[j], count + k);
                    }
                    if (i == len - 1 && j == amount) break;
                }
            }
            return dp[amount];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public class Solution2 {
        /**
         * 自下而上的动态规划
         *
         * @param coins
         * @param amount
         * @return
         */
        public int coinChange(int[] coins, int amount) {
            int max = amount + 1;
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, max);
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                for (int j = 0; j < coins.length; j++) {
                    if (coins[j] <= i) {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];
        }
    }

    class Solution3 {
        int min = Integer.MAX_VALUE;

        /**
         * DFS + cutting
         *
         * @param coins
         * @param amount
         * @return
         */
        public int coinChange(int[] coins, int amount) {
            Arrays.sort(coins);
            dfs(coins.length - 1, coins, 0, amount);
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
