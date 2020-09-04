package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * </pre>
 */
class GenerateParentheses {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<String> ans = new ArrayList<>();

        public List<String> generateParenthesis(int n) {
            backtrack(new StringBuilder(), n, 0);
            return ans;
        }

        private void backtrack(StringBuilder sb, int remained, int remainedLeft) {
            if (remained == 0 && remainedLeft == 0) ans.add(sb.toString());
            if (remained > 0) {
                sb.append("(");
                backtrack(sb, remained - 1, remainedLeft + 1);
                sb.setLength(sb.length() - 1);
            }
            if (remainedLeft > 0) {
                sb.append(")");
                backtrack(sb, remained, remainedLeft - 1);
                sb.setLength(sb.length() - 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
