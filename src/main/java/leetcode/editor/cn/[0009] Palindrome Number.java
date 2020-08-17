package leetcode.editor.cn;

/**
 * <pre>
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 *
 * Example 1:
 *
 * Input: 121
 * Output: true
 *
 * Example 2:
 *
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 *
 * Example 3:
 *
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 *
 * Follow up:
 *
 * Coud you solve it without converting the integer to a string?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * </pre>
 */
class PalindromeNumber {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPalindrome(int x) {
            if (x <= 0 || (x % 10 == 0 && x != 0)) return x == 0;
            int y = 0, z = x;
            while (x > 0) {
                y = 10 * y + x % 10;
                x /= 10;
            }
            return y == z;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
