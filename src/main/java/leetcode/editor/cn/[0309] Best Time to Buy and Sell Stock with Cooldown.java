package leetcode.editor.cn;

/**
 * <pre>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and
 * sell one share of the stock multiple times) with the following restrictions:
 *
 *     You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *     After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 *
 * Example:
 *
 * Input: [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 * </pre>
 */
class BestTimeToBuyAndSellStockWithCooldown {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * rest[i][j] 表示在 i-th 天进行 rest 操作，并且该天之后，投资人持有股票 j 张；
         * buy[i] 表示在 i-th 天进行 buy 操作；
         * sell[i] 表示在 i-th 天进行 sell 操作；
         * <p>
         * j 的可选值为 0 和 1。
         * <p>
         * rest[i][0] = max(rest[i-1][0], sell[i-1][0])
         * rest[i][1] = max(rest[i-1][1], buy[i-1][1])
         * sell[i] = max(rest[i-1][1] + price, buy[i-1] + price)
         * buy[i] = rest[i-1][0] - price
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int[] rest = new int[2];
            rest[1] = Integer.MIN_VALUE;
            int buy = Integer.MIN_VALUE, sell = Integer.MIN_VALUE;

            for (int price : prices) {
                int r0 = rest[0], r1 = rest[1];
                rest[0] = Math.max(rest[0], sell);
                rest[1] = Math.max(rest[1], buy);
                sell = Math.max(r1 + price, buy + price); // 前一天可能是休息，也可能是购入
                buy = r0 - price; // 前一天必定是 rest[0]
            }
            return Math.max(rest[0], sell);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
