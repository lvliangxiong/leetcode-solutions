package leetcode.editor.cn;

/**
 * <pre>
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 *
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 *
 *
 *
 * Constraints:
 *
 *     s consists only of printable ASCII characters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * </pre>
 */
class ValidPalindrome {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPalindrome(String s) {
            int low = 0, high = s.length() - 1;
            while (low < high) {
                while (!Character.isLetterOrDigit(s.charAt(low)) && low < high) low++;
                while (!Character.isLetterOrDigit(s.charAt(high)) && low < high) high--;
                if (Character.toLowerCase(s.charAt(low)) != Character.toLowerCase(s.charAt(high))) return false;
                low++;
                high--;
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
