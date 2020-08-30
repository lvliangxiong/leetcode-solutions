package leetcode.editor.cn;

/**
 * <pre>
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving
 * whitespace and initial word order.
 *
 * Example 1:
 *
 * Input: "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 *
 * Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
 * </pre>
 */
class ReverseWordsInAStringIii {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseWords(String s) {
            StringBuilder sb = new StringBuilder();
            char[] chs = s.toCharArray();
            int i = 0, n = s.length();
            while (i < n) {
                int j = i;
                while (j < n && chs[j] != ' ') j++;
                swap(chs, i, j - 1);
                i = j + 1;
            }
            return String.valueOf(chs);
        }

        private void swap(char[] chs, int low, int high) {
            while (low < high) {
                char ch = chs[low];
                chs[low] = chs[high];
                chs[high] = ch;
                low++;
                high--;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
