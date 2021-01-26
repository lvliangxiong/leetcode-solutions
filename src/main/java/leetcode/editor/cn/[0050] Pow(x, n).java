package leetcode.editor.cn;

import com.sun.security.jgss.GSSUtil;

/**
 * <pre>
 * Implement pow(x, n), which calculates x raised to the power n (i.e. x^n).
 *
 *
 * Example 1:
 *
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 *
 * Example 2:
 *
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 *
 * Example 3:
 *
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 *
 *
 * Constraints:
 *
 *     -100.0 < x < 100.0
 *     -2^31 <= n <= 2^31-1
 *     -10^4 <= x^n <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * </pre>
 */
class PowxN {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double myPow(double x, int n) {
            return myPowIteratively(x, n);
        }

        /**
         * 递归
         *
         * @param x
         * @param n
         * @return
         */
        private double myPowRecursively(double x, int n) {
            if (n == 0) return 1;
            if (n == 1) return x;
            if (n == -1) return 1 / x;

            double ans = myPow(x, n / 2);
            ans *= ans;
            if ((n & 1) == 1) ans *= myPow(x, n > 0 ? 1 : -1);
            return ans;
        }

        private double myPowIteratively(double x, int n) {
            boolean isNegative = n < 0;
            long exp = n;
            if (isNegative) exp = -exp;
            double ans = myPowIterativelyCore(x, exp);
            return isNegative ? 1 / ans : ans;
        }

        /**
         * <pre>
         * 当 n >= 0 时，n 的二进制表示为 c31c30 .... c2c1c0，有：
         *     n = (c0*2^0) + (c1*2^1) + .... + (c31*2^31)
         *     x^n = x^((c0*2^0) + (c1*2^1) + .... + (c31*2^31))
         *         = x^(c0*2^0) * x^(c1*2^1) * .... * x^(c31*2^31)
         *         = [(x^c0)^(2^0)] * [(x^c1)^(2^1)] * .... * [(x^c31)^(2^31)]
         * 对于 cn = 0，那么 [(x^cn)^(2^n)] = [1^(2^n)] = 1，可以忽略。
         *
         * 例如 n = 77，即 0000 0000 00000 00000 0000 0000 0100 1101
         *     x^n = x^77 = [x^(2^0)] * [x^(2^2)] * [x^(2^3)] * [x^(2^6)]
         *                = x^1 * x^4 * x^8 * x^64
         *                = x^77
         * </pre>
         *
         * @param x
         * @param n
         * @return
         */
        private double myPowIterativelyCore(double x, long n) {
            double base = x, ans = 1.0;
            long mask = 1;
            for (int i = 0; i < 32; i++) {
                boolean flag = (n & mask) == mask; // n 的第 i 位是否为 1
                if (flag) {
                    ans *= base;
                }
                base *= base;
                mask <<= 1;
            }
            return ans;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
}