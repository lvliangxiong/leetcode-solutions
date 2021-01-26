package leetcode.editor.cn;

/**
 * <pre>
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also
 * represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 *
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 * Note:
 *
 *     The length of both num1 and num2 is < 110.
 *     Both num1 and num2 contain only digits 0-9.
 *     Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 *     You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * </pre>
 */
class MultiplyStrings {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) return "0";
            int m = num1.length(), n = num2.length();
            int len = m + n;
            int[] ans = new int[len];
            // 多次使用 char[i] 一般会比多次使用 charAt(i) 要快
            char[] num1Chars = num1.toCharArray(), num2Chars = num2.toCharArray();
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    int prod = (num1Chars[i] - '0')
                            * (num2Chars[j] - '0');
                    ans[i + j + 1] += prod; // 由于进位比较复杂，直接在该位置上进行累加，最后再进行进位即可
                }
            }
            for (int i = len - 1; i > 0; i--) { // ans 数组中，索引大的位置是低位
                if (ans[i] >= 10) {
                    ans[i - 1] += ans[i] / 10; // 进位
                    ans[i] %= 10;
                }
            }
            StringBuilder sb = new StringBuilder();
            int index = ans[0] == 0 ? 1 : 0; // 起始位置
            while (index < len) {
                sb.append(ans[index++]);
            }
            return sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
