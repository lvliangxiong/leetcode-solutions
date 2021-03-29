package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <pre>
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 *
 * The following rules define a valid string:
 *
 *     Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 *     Any right parenthesis ')' must have a corresponding left parenthesis '('.
 *     Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 *     '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "(*)"
 * Output: true
 *
 * Example 3:
 *
 * Input: s = "(*))"
 * Output: true
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 100
 *     s[i] is '(', ')' or '*'.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parenthesis-string
 * </pre>
 */
class ValidParenthesisString {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 贪心算法
         *
         * @param s
         * @return
         */
        public boolean checkValidString(String s) {
            // possible range
            int min = 0, max = 0; // 维护当前左括号的数量范围：[min, max]
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    ++min;
                    ++max;
                } else if (c == ')') {
                    if (max == 0) return false; // 左括号不够
                    if (min > 0) min--;
                    max--;
                } else {
                    if (min > 0) min--; // 可作为右括号，抵消左括号
                    max++; // 也可作为左括号
                }
            }
            return min == 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DoubleStackSolution {
        public boolean checkValidString(String s) {
            Deque<Integer> left = new ArrayDeque<>(); // 左括号的索引
            Deque<Integer> star = new ArrayDeque<>(); // 星号的索引

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '(') left.push(i);
                else if (ch == '*') star.push(i);
                else {
                    if (!left.isEmpty()) left.pop();
                    else if (!star.isEmpty()) star.pop();
                    else return false;
                }
            }
            while (!left.isEmpty() && !star.isEmpty()) {
                // 多余的 （ 需要使用 * 作为 ）来抵消
                if (left.pop() > star.pop()) return false;
            }
            return left.isEmpty();
        }
    }

    class RecursionSolution {
        public boolean checkValidString(String s) {
            return check(s, 0, 0);
        }

        /**
         * 太多的 * 会超时！
         * <p>
         * TODO 尝试使用记忆化递归
         *
         * @param s
         * @param start
         * @param count 剩余左括号的数量
         * @return
         */
        private boolean check(String s, int start, int count) {
            if (count < 0) return false;
            for (int i = start; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '(') count++;
                else if (ch == ')') {
                    if (count-- == 0) return false;
                } else if (ch == '*') {
                    // 遇到 * 需要进行递归求解
                    return check(s, i + 1, count + 1) ||  // 作为 (
                            check(s, i + 1, count - 1) || // 作为 )
                            check(s, i + 1, count);       // 作为 空串
                }
            }
            return count == 0;
        }
    }

}