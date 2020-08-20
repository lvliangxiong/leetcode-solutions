package leetcode.editor.cn;

/**
 * <pre>
 * Say you have an array prices for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one
 * and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy
 * again).
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 *
 * Example 2:
 *
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 *
 * Example 3:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 *
 *
 * Constraints:
 *
 *     1 <= prices.length <= 3 * 10 ^ 4
 *     0 <= prices[i] <= 10 ^ 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * </pre>
 */
class BestTimeToBuyAndSellStockIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 考虑 prices 数组组成的一个曲线，将所有上升端的『上升值』进行累加即可得到最大的利润
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int max = 0, n = prices.length;
            for (int i = 1; i < n; i++) {
                if (prices[i - 1] < prices[i]) {
                    max += prices[i] - prices[i - 1];
                }
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DPSolution {
        /**
         * T[i][k][0] = max(T[i-1][k][0], T[i-1][k][1] + prices[i])
         * T[i][k][1] = max(T[i-1][k][1], T[i-1][k-1][0] - prices[i]) = max(T[i-1][k][1], T[i-1][k][0] - prices[i])
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int T_0 = 0, T_1 = Integer.MIN_VALUE;
            for (int price : prices) {
                /* 如果在计算 T_0 时，T_1 + price 比较小，说明当前日期的价格比较高。
                 * 这里假设 T_0 在更新后，取的是 T_1 + price，那么再更新 T_1 时，事实上，比较的就是 T_1 和 T_0 - price
                 * 的大小，但是这里由于 T_0 是被更新过的值，因此实际上比较的是 T_1 和 T_1 + price - price = T_1 的值，
                 * 因此最终 T_1 就会保持不变，也就是 rest 状态。
                 * 那么实际上，如果是使用的旧值，是否仍然是 T_1 保持不变这样一个结果呢？
                 * 前面提到，在更新 T_0 时，如果选择了 T_1 + price，那么至少意味着在当前日期卖出股票是一个正确的决定。也就
                 * 是说当前日期的价格至少是比前一天的价格要高的。在计算 T_1 时，T_0 - price 其实代表的是在当前日期进行买入，
                 * 而在上述情况下，显然这是一种不经济的行为，因此肯定是保持不变这个策略更好。*/
                T_0 = Math.max(T_0/*rest*/, T_1 + price/*sell*/);
                T_1 = Math.max(T_1/*rest*/, T_0 - price/*buy*/);
            }
            return T_0;
        }
    }

}
