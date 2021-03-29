package leetcode.editor.cn;

/**
 * <pre>
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Follow up: Could you write a solution that works in logarithmic time complexity?
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 *
 * Example 2:
 *
 * Input: n = 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 *
 * Example 3:
 *
 * Input: n = 0
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 *     0 <= n <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
 * </pre>
 */
class FactorialTrailingZeroes {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 在 1 ~ n 中，含有 2 的因子每两个出现一次，含有 5 的因子每 5 个出现一次，所有 2 出现的个数远远多于 5，
         * 换言之找到一个 5，一定能找到一个 2 与之配对。所以我们只需要找有多少个 5。
         *
         * @param n
         * @return
         */
        public int trailingZeroes(int n) {
            int count = 0;
            while (n > 0) {
                // 每隔 5 个数，会有 1 个 5 的因子；每隔 25 个数，再多加 1 个 5 的因子 ...
                count += n / 5;
                n = n / 5;
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}