package leetcode.editor.cn;

/**
 * <pre>
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 *
 * Example 2:
 *
 * Input: -123
 * Output: -321
 *
 * Example 3:
 *
 * Input: 120
 * Output: 21
 *
 * Note:
 * Assume we are dealing with an environment which could only store integers within
 * the 32-bit signed integer range: [−2^31,  2^31 − 1]. For the purpose of this problem,
 * assume that your function returns 0 when the reversed integer overflows.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * </pre>
 */
class ReverseInteger {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 注意两点：
         * 1. 如何处理负数
         * 2. 如何处理反转的溢出
         *
         * @param x
         * @return
         */
        public int reverse(int x) {
            boolean isPositive = x >= 0;
            int ret = 0; // 结果
            while (x != 0) {
                int bit = x % 10; // 最右一位数字，如果原数为负数，则 bit 也为负数
                if ((isPositive && ret <= (Integer.MAX_VALUE - bit) / 10) ||
                        (!isPositive && ret >= (Integer.MIN_VALUE - bit) / 10)) {
                    ret = ret * 10 + bit;
                    x = x / 10;
                } else {
                    return 0;
                }
            }
            return ret;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
