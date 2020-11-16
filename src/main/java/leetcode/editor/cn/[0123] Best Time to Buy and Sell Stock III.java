package leetcode.editor.cn;

/**
 * <pre>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you
 * buy again).
 *
 * Example 1:
 *
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
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
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * </pre>
 */
class BestTimeToBuyAndSellStockIii {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 将价格数组分割成两个子数组，原问题等效于分别求两个子数组的单次交易能够获得的最大利润之和。
         * 注意分割的位置基本上是任意的。
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int n = prices.length, max = Integer.MIN_VALUE;
            if (n < 4) return maxProfit(prices, 0, n - 1);
            for (int split = 1; split < n - 2; split++) {
                int maxProfit = maxProfit(prices, 0, split) + maxProfit(prices, split + 1, n - 1);
                if (max < maxProfit) {
                    max = maxProfit;
                }
            }
            max = Math.max(max, maxProfit(prices, 0, n - 1));
            return max;
        }

        private int maxProfit(int[] prices, int start, int end) {
            if (start == end) {
                return 0;
            }
            int min = Integer.MAX_VALUE, ans = Integer.MIN_VALUE;
            for (int i = start; i <= end; i++) {
                if (prices[i] > min) ans = Math.max(ans, prices[i] - min); // 更新最大利润
                if (prices[i] < min) min = prices[i]; // 更新最小值
            }
            return ans < 0 ? 0 : ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    class DPSolution {
        /**
         * T[i][k][0] 表示 [0, i-1] 天内，最多允许完成交易 k 次，并在 i-th 天过后手中没有股票所能达到的最大利润。
         * T[i][k][1] 表示 [0, i-1] 天内，最多允许完成交易 k 次，并在 i-th 天过后手中有 1 股票所能达到的最大利润。
         * <p>
         * T[i][1][0] = max(T[i-1][1][0], T[i-1][1][1] + prices[i])
         * T[i][1][1] = max(T[i-1][1][1], - prices[i])
         * T[i][2][0] = max(T[i-1][2][0], T[i-1][2][1] + prices[i])
         * T[i][2][1] = max(T[i-1][2][1], T[i-1][1][0] - prices[i])
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int T_i10 = 0, T_i11 = Integer.MIN_VALUE, T_i20 = 0, T_i21 = Integer.MIN_VALUE;
            for (int price : prices) {
                T_i10 = Math.max(T_i10/*rest*/, T_i11 + price/*sell*/);
                T_i11 = Math.max(T_i11/*rest*/, -price/*buy*/);
                T_i20 = Math.max(T_i20/*rest*/, T_i21 + price/*sell*/);
                /* 这里使用是更新之后的 T_i10，如果 T_i10 在更新时，选择了 T_i11 + price 这样一个选项，则说明当前 price 价格
                 * 较高，适合卖出，而更新 T_i21 的选项中，在当前日期进行购买，显然不太合理。*/
                T_i21 = Math.max(T_i21/*rest*/, T_i10 - price/*buy*/);
            }
            return T_i20;
        }
    }
}
