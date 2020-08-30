package leetcode.editor.cn;

/**
 * <pre>
 * Given a non-empty array of digits representing a non-negative integer, increment one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array
 * contains a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 *
 *
 * Example 1:
 *
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 *
 * Example 2:
 *
 * Input: digits = [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 *
 * Example 3:
 *
 * Input: digits = [0]
 * Output: [1]
 *
 *
 *
 * Constraints:
 *
 *     1 <= digits.length <= 100
 *     0 <= digits[i] <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * </pre>
 */
class PlusOne {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] plusOne(int[] digits) {
            int carry = 0, n = digits.length;
            for (int i = n - 1; i >= 0; i--) {
                int sum = digits[i] + carry + (i == n - 1 ? 1 : 0);
                digits[i] = sum % 10;
                if (sum >= 10) {
                    carry = 1;
                } else {
                    carry = 0;
                    break;
                }
            }
            if (carry == 0) return digits;
            int[] ans = new int[n + 1];
            ans[0] = 1;
            System.arraycopy(digits, 0, ans, 1, n);
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
