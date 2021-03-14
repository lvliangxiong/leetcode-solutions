package leetcode.editor.cn;

/**
 * <pre>
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and
 * removing them.
 *
 * We repeatedly make duplicate removals on S until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 *
 * Example 1:
 *
 * Input: "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only
 * possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final
 * string is "ca".
 *
 * Note:
 *
 *     1 <= S.length <= 20000
 *     S consists only of English lowercase letters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string
 * </pre>
 */
class RemoveAllAdjacentDuplicatesInString {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeDuplicates(String S) {
            char[] chars = S.toCharArray();
            int top = -1;
            for (int i = 0; i < chars.length; i++) {
                if (top >= 0 && chars[i] == chars[top]) {
                    // 遇到相同的字符，则将 top 指针前移
                    top--;
                } else {
                    top++;
                    if (top != i)
                        chars[top] = chars[i];
                }
            }
            return String.valueOf(chars, 0, top + 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}