package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible
 * results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 *
 * Example 2:
 *
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 *
 * Example 3:
 *
 * Input: ")("
 * Output: [""]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
 * </pre>
 */
class RemoveInvalidParentheses {


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        Set<String> validExpressions = new HashSet<>();
        char[] chs;
        int len;

        public List<String> removeInvalidParentheses(String s) {
            len = s.length();
            chs = s.toCharArray();

            // Find out the number of misplaced left and right parentheses.
            int left = 0; // 最终有效表达式中要删除的左括号数
            int right = 0; // 最终有效表达式中要删除的右括号数
            for (int i = 0; i < len; i++) {
                if (chs[i] == '(') left++;
                else if (chs[i] == ')')
                    if (left == 0) right++;
                    else left--;
            }
            dfs(0, 0, left, 0, right, new StringBuilder());
            return new ArrayList<>(validExpressions);
        }

        private void dfs(int i, int leftBracketCount, int leftBracketRemainedToBeRemoved, int rightBracketCount,
                         int rightBracketRemainedToBeRemoved, StringBuilder expr) {
            // If we have reached the end of string.
            if (i == len) {
                // If the current expression is valid.
                if (leftBracketCount == rightBracketCount) {
                    if (leftBracketRemainedToBeRemoved == 0 && rightBracketRemainedToBeRemoved == 0) {
                        String ans = expr.toString();
                        validExpressions.add(ans);
                    }
                }
                return;
            }

            char ch = chs[i];
            int length = expr.length();

            // Keep current character greedy
            expr.append(ch);
            if (ch == '(') {
                dfs(i + 1, leftBracketCount + 1, leftBracketRemainedToBeRemoved,
                        rightBracketCount, rightBracketRemainedToBeRemoved, expr);
            } else if (ch == ')') {
                if (leftBracketCount > rightBracketCount)
                    dfs(i + 1, leftBracketCount, leftBracketRemainedToBeRemoved,
                            rightBracketCount + 1, rightBracketRemainedToBeRemoved, expr);
            } else {
                dfs(i + 1, leftBracketCount, leftBracketRemainedToBeRemoved,
                        rightBracketCount, rightBracketRemainedToBeRemoved, expr);
            }

            // Delete the current character when it is either '(' or ')'
            expr.setLength(length);
            if (ch == '(' && leftBracketRemainedToBeRemoved > 0) {
                dfs(i + 1, leftBracketCount, leftBracketRemainedToBeRemoved - 1,
                        rightBracketCount, rightBracketRemainedToBeRemoved, expr);
            }
            if (ch == ')' && rightBracketRemainedToBeRemoved > 0) {
                dfs(i + 1, leftBracketCount, leftBracketRemainedToBeRemoved,
                        rightBracketCount, rightBracketRemainedToBeRemoved - 1, expr);

            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
