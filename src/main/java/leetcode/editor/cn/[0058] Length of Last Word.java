package leetcode.editor.cn;

/**
 * <pre>
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last
 * word (last word means the last appearing word if we loop from left to right) in the string.
 *
 * If the last word does not exist, return 0.
 *
 * Note: A word is defined as a maximal substring consisting of non-space characters only.
 *
 * Example:
 *
 * Input: "Hello World"
 * Output: 5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/length-of-last-word
 * </pre>
 */
class LengthOfLastWord {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLastWord(String s) {
            String str = s.trim();
            int end = str.length() - 1;
            while (end >= 0 && str.charAt(end) != ' ') end--;
            return str.length() - 1 - end;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
