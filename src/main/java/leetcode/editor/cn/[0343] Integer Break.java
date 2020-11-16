package leetcode.editor.cn;

/**
 * <pre>
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of
 * those integers. Return the maximum product you can get.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 *
 * Example 2:
 *
 * Input: 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 *
 * Note: You may assume that n is not less than 2 and not larger than 58.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-break
 * </pre>
 */
class IntegerBreak {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            dp[2] = 1;
            for (int i = 3; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    dp[i] = Math.max(Math.max(dp[i], j * dp[i - j]), j * (i - j));
                }
            }
            return dp[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * dp[i] 表示将 i 分解为至少为 2 个数的最大积。
     * dp[2] = 1;
     * dp[3] = 2;
     * dp[4] = 4;
     * d[5] = 6;
     * 考虑对于 i > =6 的分解策略：
     * 1. 被分解的数中有 1，那么 dp[i] = max(1 * dp[i-1], 1 * (i-1)) = max(dp[i-1], i-1)
     * 2. 被分解的数中有 2，那么 dp[i] = max(2 * dp[i-2], 2 * (i-2))
     * 3. 被分解的数中有 3，那么 dp[i] = max(3 * dp[i-3], 3 * (i-3))
     * 4. 被分解的数中有 4，那么 dp[i] = max(4 * dp[i-4], 4 * (i-4))
     * 5. 被分解的数中有 n >= 5 的情况，那么 dp[i] = max(n * dp[i-n], n * (i-n))
     * 分别考虑上述情况：
     * 1. 对于 case 5，我们可以发现，n 总是可以被分解为 2 和 3 的和，并且分解之后它们的乘积要大于 n。
     * 例如：5 = 3 + 2 < 3 * 2 = 6，6 = 3 + 3 < 3 * 3 = 9，7 = 2 + 2 + 3 < 2 * 2 * 3 = 12，
     * 因此我们可以认为『case 5』是一种不合理的情况。
     * 2. 对于 case 4，其实 4 本身可以被分解为 2 + 2 = 2 * 2，所以分解为 4 和分解为两个 2 是完全等效的，因此我们可以不考虑 case 4。
     * 3. 对于 case 1，我们容易发现： i-1 < 2(i-2) ，另外还有 2dp[i-2] > dp[i-1]。
     * 因为如果 i-1 的最大分割为 a1 * a2 * ... * an，那么我们可以构造一个 i-2 的分割 (a1-1) * a2 * ... * an，并且其中 a1 != 1，
     * 因此就有 2dp[i-2] >= 2*(a1-1) * a2 * ... * an，考虑到 2*(a1-1) 肯定是 >= a1 的，因此 2dp[i-2] >= dp[i-1]。事实上，对于
     * i > 6 的情况，这个等于也是不成立的。
     * 总之，我们发现，对于 i > 6 的情况，分解的数种包含有 1 的情况，也是不合理的，不如分解出 2 的最终结果大。
     */
    class GreedySolution {
        public int integerBreak(int n) {
            int[] dp = new int[n + 1 > 6 ? n + 1 : 6];
            dp[2] = 1;
            dp[3] = 2;
            dp[4] = 4;
            dp[5] = 6;
            for (int i = 6; i <= n; i++) {
                dp[i] = Math.max(Math.max(dp[i], 2 * dp[i - 2]), 2 * (i - 2));
                dp[i] = Math.max(Math.max(dp[i], 3 * dp[i - 3]), 3 * (i - 3));
            }
            return dp[n];
        }
    }
}
