package leetcode.editor.cn;

/**
 * <pre>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 *
 * Example 2:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * </pre>
 */
class BestTimeToBuyAndSellStock {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            // min 存储当前日期前的所有日期的价格的最小值，因此如果在当日卖出，则必会选择 min 作为购入价格
            int min = Integer.MAX_VALUE, ans = Integer.MIN_VALUE;
            for (int price : prices) {
                if (price > min) {
                    ans = Math.max(ans, price - min);
                }
                if (price < min) min = price;
            }
            return ans == Integer.MIN_VALUE ? 0 : ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
