package leetcode.editor.cn;

/**
 * <pre>
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 *
 * Input: "aba"
 * Output: True
 *
 * Example 2:
 *
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 *
 * Note:
 *
 *     The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome-ii
 * </pre>
 */
class ValidPalindromeIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean validPalindrome(String s) {
            int low = 0, high = s.length() - 1;
            char[] chs = s.toCharArray();
            while (low < high) {
                if (chs[low] != chs[high]) {
                    if (chs[low] != chs[high - 1]) return isPalindrome(chs, low + 1, high);
                    if (chs[low + 1] != chs[high]) return isPalindrome(chs, low, high - 1);
                    return isPalindrome(chs, low, high - 1) || isPalindrome(chs, low + 1, high);
                }
                low++;
                high--;
            }
            return true;
        }

        private boolean isPalindrome(char[] chs, int low, int high) {
            while (low < high) {
                if (chs[low] != chs[high]) return false;
                low++;
                high--;
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
