package leetcode.editor.cn;

/**
 * <pre>
 * Given two binary strings, return their sum (also a binary string).
 *
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 *
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 *
 *
 * Constraints:
 *
 *     Each string consists only of '0' or '1' characters.
 *     1 <= a.length, b.length <= 10^4
 *     Each string is either "0" or doesn't contain any leading zero.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * </pre>
 */
class AddBinary {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addBinary(String a, String b) {
            // 保证 a 的长度不小于 b
            if (a.length() < b.length()) {
                String c = a;
                a = b;
                b = c;
            }
            int m = a.length(), n = b.length(); // m >= b
            char[] cb = b.toCharArray();
            StringBuilder sb = new StringBuilder(a);
            int i = m - 1, j = n - 1, carry = 0;
            while (i >= 0 && j >= 0) {
                int sum = (sb.charAt(i) - '0') + (cb[j] - '0') + carry; // 可能为 0，1，2，3
                sb.setCharAt(i, (sum & 1) == 0 ? '0' : '1');
                carry = sum > 1 ? 1 : 0;
                i--;
                j--;
            }
            while (i >= 0 && carry == 1) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                } else {
                    sb.setCharAt(i, '1');
                    carry = 0;
                }
                i--;
            }
            return (carry == 0 ? "" : "1") + sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
