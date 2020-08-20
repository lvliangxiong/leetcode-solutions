package leetcode.editor.cn;

/**
 * <pre>
 * Say you have an array for which the i-th element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *
 * Example 2:
 *
 * Input: [3,2,6,5,0,3], k = 2
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 *              Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * </pre>
 */
class BestTimeToBuyAndSellStockIv {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int k, int[] prices) {
            /* 两种确定交易次数的最大有效值的方式，第二种方式，虽然不精确，但是比较快就能够确定一个大概临界值。*/
            if (prices.length <= 1) return 0;
            int validK = validK(prices);
            // int validK = prices.length >> 1;
            System.out.println(validK);
            if (k >= validK) {
                // equivalent to infinity transactions problem
                int max = 0, pre = Integer.MAX_VALUE;
                for (int price : prices) {
                    if (pre < price) max += price - pre;
                    pre = price;
                }
                return max;
            } else {
                /* dp[j][0] 表示前 [0, i-1] 天内，最多进行 j 次交易，最终剩余 0 张股票，所能够获得的最大收益。
                 * dp[j][1] 表示前 [0, i-1] 天内，最多进行 j 次交易，最终剩余 1 张股票，所能够获得的最大收益。*/
                int[][] dp = new int[k + 1][2];
                for (int j = 0; j <= k; j++) {
                    dp[j][0] = 0;
                    dp[j][1] = Integer.MIN_VALUE;
                }
                for (int price : prices) {
                    for (int j = k; j >= 1; j--) {
                        dp[j][0] = Math.max(dp[j][0]/*rest*/, dp[j][1] + price/*sell*/);
                        dp[j][1] = Math.max(dp[j][1]/*rest*/, dp[j - 1][0] - price/*buy*/);
                    }
                }
                return dp[k][0];
            }
        }

        private int validK(int[] prices) {
            int n = prices.length;
            int count = 0, index = 0;
            while (index < n) {
                // 遍历一次连续上升区
                int start = index;
                while (index + 1 < n) {
                    if (prices[index] <= prices[index + 1]) {
                        index++;
                    } else {
                        break;
                    }
                }
                if (prices[index] > prices[start]) count++;
                // 遍历一次连续下降区
                index++;
                while (index + 1 < n) {
                    if (prices[index] >= prices[index + 1]) {
                        index++;
                    } else {
                        break;
                    }
                }
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
