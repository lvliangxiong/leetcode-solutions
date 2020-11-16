package leetcode.editor.cn;

/**
 * <pre>
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i;
 * and a non-negative integer fee representing a transaction fee.
 *
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 *
 * Return the maximum profit you can make.
 *
 * Example 1:
 *
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1
 * Selling at prices[3] = 8
 * Buying at prices[4] = 4
 * Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * Note:
 * 1. 0 < prices.length <= 50000.
 * 2. 0 < prices[i] < 50000.
 * 3. 0 <= fee < 50000.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * </pre>
 */
class BestTimeToBuyAndSellStockWithTransactionFee {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * dp[i][0] 在 i-th 天进行操作后，手中没有股票的最大利润
         * dp[i][1] 在 i-th 天进行操作后，手中还有 1 股的最大利润
         *
         * @param prices
         * @param fee
         * @return
         */
        public int maxProfit(int[] prices, int fee) {
            // 数组的分配比较耗费资源，使用两个变量进行表示，比用一个 int[2] 的数组更快
            int zeroHold = 0, oneHold = Integer.MIN_VALUE;
            for (int price : prices) {
                zeroHold = Math.max(zeroHold, oneHold + price/*sell*/);
                oneHold = Math.max(oneHold, zeroHold - price - fee/*buy*/);
            }
            return zeroHold;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
