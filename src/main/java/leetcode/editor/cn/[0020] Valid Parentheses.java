package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 *     Open brackets must be closed by the same type of brackets.
 *     Open brackets must be closed in the correct order.
 *
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 *
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 *
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 *
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 *
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * </pre>
 */
class ValidParentheses {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            if ("".equals(s) || s == null) return true;
            Map<Character, Character> mapping = new HashMap() {
                {
                    put('[', ']');
                    put('{', '}');
                    put('(', ')');
                }
            };
            Deque<Character> stack = new ArrayDeque<>();
            for (Character ch : s.toCharArray()) {
                if (mapping.containsKey(ch)) {
                    // 开括号，则压入栈
                    stack.push(ch);
                } else {
                    // 闭括号，则尝试与栈顶元素抵消
                    Character top = stack.peek();
                    if (top != null && ch.equals(mapping.get(top))) {
                        stack.pop();
                    } else {
                        // 不匹配，则说明给定字符串不符合规范
                        return false;
                    }
                }
            }
            return stack.size() == 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
