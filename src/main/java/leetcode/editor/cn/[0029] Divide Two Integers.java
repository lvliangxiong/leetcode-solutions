package leetcode.editor.cn;

/**
 * <pre>
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero, which means losing its fractional part. For example,
 * truncate(8.345) = 8 and truncate(-2.7335) = -2.
 *
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = truncate(3.33333..) = 3.
 *
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = truncate(-2.33333..) = -2.
 *
 * Note:
 *
 *     1. Both dividend and divisor will be 32-bit signed integers.
 *     2. The divisor will never be 0.
 *     3. Assume we are dealing with an environment which could only store integers within the 32-bit signed integer
 *     range: [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your function returns 2^31 − 1 when the
 *     division result overflows.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * </pre>
 */
class DivideTwoIntegers {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int divide(int dividend, int divisor) {
            boolean negative = (dividend > 0) ^ (divisor > 0); // 最终结果是否为负数
            // 将除数和被除数均转为负数后，再参与计算
            if (dividend > 0) dividend = -dividend;
            if (divisor > 0) divisor = -divisor;
            int result = 0;
            while (dividend <= divisor) {
                int tmpResult = -1, tempDivisor = divisor;
                while (dividend <= (tempDivisor << 1)) {
                    if (tempDivisor <= (Integer.MIN_VALUE >> 1)) break;
                    tmpResult = tmpResult << 1;
                    tempDivisor = tempDivisor << 1;
                }
                dividend -= tempDivisor;
                result += tmpResult; // 注意这里是一些负数在累加，最终累计的结果最多是 Integer.MIN_VALUE
            }
            if (!negative) {
                if (result <= Integer.MIN_VALUE) return Integer.MAX_VALUE;
                result = -result;
            }
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        /**
         * x/y = e^(log(x/y)) = e^(log(x) - log(y))
         *
         * @param dividend
         * @param divisor
         * @return
         */
        public int divide(int dividend, int divisor) {
            // boundary case
            if (divisor == Integer.MIN_VALUE) {
                if (dividend == Integer.MIN_VALUE) return 1;
                return 0;
            }
            double ret, e = 1E-9;
            if (dividend == Integer.MIN_VALUE) {
                double log2E16 = Math.log(1 << 16);
                double log2 = Math.log(2);
                ret = Math.exp(log2E16 + log2E16 - log2 - Math.log(Math.abs(divisor)));
            } else
                ret = Math.exp(Math.log(Math.abs(dividend)) - Math.log(Math.abs(divisor)));
            return dividend > 0 ^ divisor > 0 ? (int) -(ret + e) : (int) (ret + e);
        }
    }
}
