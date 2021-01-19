package leetcode.editor.cn;

/**
 * <pre>
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until the first non-whitespace
 * character is found. Then, starting from this character, takes an optional initial plus or minus sign
 * followed by as many numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number, which are
 * ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no
 * such sequence exists because either str is empty or it contains only whitespace characters, no
 * conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 *
 *     Only the space character ' ' is considered as whitespace character.
 *     Assume we are dealing with an environment which could only store integers within the 32-bit
 *     signed integer range: [−2^31,  2^31 − 1]. If the numerical value is out of the range of
 *     representable values, INT_MAX (2^31 − 1) or INT_MIN (−2^31) is returned.
 *
 * Example 1:
 *
 * Input: "42"
 * Output: 42
 *
 * Example 2:
 *
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 *              Then take as many numerical digits as possible, which gets 42.
 *
 * Example 3:
 *
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 *
 * Example 4:
 *
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 *              digit or a +/- sign. Therefore no valid conversion could be performed.
 *
 * Example 5:
 *
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 *              Therefore INT_MIN (−2^31) is returned.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * </pre>
 */
class StringToIntegerAtoi {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int myAtoi(String str) {
            boolean start = false; // 是否开始处理数字位
            boolean isPositive = true;
            int ret = 0;
            for (char ch : str.toCharArray()) {
                if (!start) {
                    if (ch == ' ') {
                        continue;
                    } else if (ch == '+') {
                        start = true;
                    } else if (ch == '-') {
                        start = true;
                        isPositive = false;
                    } else if (Character.isDigit(ch)) {
                        // 没有符号位，即为正数
                        start = true;
                        ret = ch - '0';
                    } else {
                        return 0;
                    }
                } else {
                    if (Character.isDigit(ch)) {
                        int bit = ch - '0'; // 由于是单个处理的，这里的 bit 必定是非负数
                        if (isPositive) {
                            if (ret <= (Integer.MAX_VALUE - bit) / 10) {
                                ret = ret * 10 + bit;
                            } else {
                                return Integer.MAX_VALUE;
                            }
                        } else {
                            if (ret >= (Integer.MIN_VALUE + bit) / 10) {
                                ret = ret * 10 - bit;
                            } else {
                                return Integer.MIN_VALUE;
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
            return ret;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
