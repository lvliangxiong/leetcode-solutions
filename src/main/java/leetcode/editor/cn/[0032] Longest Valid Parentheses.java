package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <pre>
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
 * parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 *
 * Example 2:
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * </pre>
 */
class LongestValidParentheses {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestValidParentheses(String s) {
            int ans = 0;
            // 保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        // 当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新「最后一个没有被匹配的右括号的下标」
                        stack.push(i);
                    } else {
                        ans = Math.max(ans, i - stack.peek());
                    }
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    class DPSolution {
        public int longestValidParentheses(String s) {
            int ans = 0;
            /* dp[i] 表示以索引为 i 的字符结尾的最长有效括号字符串长度。
             * 1. dp[i] = 0                                    if s[i] = '('
             * 2. dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]        if s[i] = ')' && s[i-dp[i-1]-1] = '('
             * 3. dp[i] = 0                                    if s[i] = ')' && s[i-dp[i-1]-1] = ')'
             * */
            int dp[] = new int[s.length()];
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    // pairIndex 和 i 之间，也就是 (pairIndex, i) 之间是一个合法的括号字符串
                    int pairIndex = i - dp[i - 1] - 1;
                    if (pairIndex >= 0 && s.charAt(pairIndex) == '(')
                        dp[i] = dp[i - 1] + 2 +
                                /*well-formed substring ended at pairIndex-1*/
                                (pairIndex >= 2 ? dp[pairIndex - 1] : 0);
                    ans = Math.max(ans, dp[i]);
                }
            }
            return ans;
        }
    }

    class TwoPointerSolution {
        public int longestValidParentheses(String s) {
            int left = 0, right = 0, ans = 0;
            // 从左到右遍历字符串
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') left++;
                else right++;
                if (left == right) {
                    // 以当前字符下标结尾的有效括号长度
                    ans = Math.max(ans, right << 1);
                } else if (right > left) {
                    left = right = 0;
                }
            }
            left = right = 0;
            // 从右到左遍历字符串
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') left++;
                else right++;
                if (left == right) {
                    ans = Math.max(ans, left << 1);
                } else if (left > right) {
                    left = right = 0;
                }
            }
            return ans;
        }
    }

}
