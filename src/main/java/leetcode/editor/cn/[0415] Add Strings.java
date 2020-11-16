package leetcode.editor.cn;

/**
 * <pre>
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 *
 * Note:
 *
 *     The length of both num1 and num2 is < 5100.
 *     Both num1 and num2 contains only digits 0-9.
 *     Both num1 and num2 does not contain any leading zero.
 *     You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * </pre>
 */
class AddStrings {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addStrings(String num1, String num2) {
            int m = num1.length() - 1;
            int n = num2.length() - 1;
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            while (m >= 0 || n >= 0) {
                int sum = (m >= 0 ? (num1.charAt(m--) - '0') : 0)
                        + (n >= 0 ? (num2.charAt(n--) - '0') : 0)
                        + carry;
                carry = sum / 10;
                sb.append(sum % 10);
            }
            if (carry == 1) sb.append(1);
            return sb.reverse().toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
