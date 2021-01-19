package leetcode.editor.cn;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 *
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
 * A gray code sequence must begin with 0.
 *
 * Example 1:
 *
 * Input: 2
 * Output: [0,1,3,2]
 * Explanation:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 *
 * For a given n, a gray code sequence may not be uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence.
 *
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 *
 * Example 2:
 *
 * Input: 0
 * Output: [0]
 * Explanation: We define the gray code sequence to begin with 0.
 *              A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
 *              Therefore, for n = 0 the gray code sequence is [0].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gray-code
 * </pre>
 */
class GrayCode {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 找出格雷码序列的规律即可
         *
         * @param n
         * @return
         */
        public List<Integer> grayCode(int n) {
            if (n == 0) return Arrays.asList(0);
            int len = 1 << n;
            Integer[] dp = new Integer[len];
            Arrays.fill(dp, 0);
            for (int round = 1; round <= n; round++) {
                int start = 1 << round - 1, end = (1 << round) - 1;
                int mask = 1 << (round - 1);
                for (int i = start, j = start - 1; i <= end; i++, j--) {
                    dp[i] = dp[j] | mask;
                }
            }
            return Arrays.asList(dp);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
