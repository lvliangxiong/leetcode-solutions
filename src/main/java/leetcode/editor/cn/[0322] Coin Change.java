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
         * 为什么尽量使用大面值的贪心策略无法使用？
         * <pre>
         * 例如：1 2 3 5 7 10  =====>  14
         * 有两种方式： 14 = 10 + 2 + 2 （贪心）
         *             14 = 7 + 7
         * </pre>
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
            // 初始化，仅能使用 coins[0] 的情况
            for (int j = 0; j <= amount; j++) {
                if (j % coins[0] == 0) dp[j] = j / coins[0];
                else dp[j] = -1;
            }
            // dp[i][j] = min{dp[i-1][j], dp[i-1][j-coins[i]]+1, .... , dp[i-1][j-most*coins[i]]+most}
            for (int i = 1; i < len; i++) { // you can use [0, i]-th coin in the coins
                for (int j = amount; j >= 0; j--) { // target number of money
                    int most = j / coins[i]; // the most number of i-th coin you can choose
                    int min = dp[j];
                    for (int k = most; k >= 1; k--) { // iterate possible i-th coin number
                        int count = dp[j - k * coins[i]];
                        if (count != -1)
                            if (min != -1)
                                min = Math.min(min, count + k);
                            else
                                min = count + k;
                    }
                    dp[j] = min;
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
         * 和刚好为 amount』，因此每做一次选择时，必然是 coins 数组中的一种，这里进行枚举：
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
}
