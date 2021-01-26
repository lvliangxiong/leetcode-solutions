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
 * Constraints:
 *
 *     0 <= s.length <= 3 * 10^4
 *     s[i] is '(', or ')'.
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
            // 保持『栈底』元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号 ')' 的下标」
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                // 如果压栈和出栈的左右括号是匹配的，那么该栈永远不会为空，一旦栈为空，说明导致出栈的 ')' 的数量多于了 '('，
                // 因此可以更新栈底的元素为最新的 ')' 的索引位置，进行下一轮的遍历。
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
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
            /* dp[i] 表示以索引为 i 的字符结尾的最长有效括号字符串的长度（必定是个偶数）。
             * 1. dp[i] = 0                                    if s[i] = '('
             * 2. dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]        if s[i] = ')' && s[i-dp[i-1]-1] = '('
             * 3. dp[i] = 0                                    if s[i] = ')' && s[i-dp[i-1]-1] = ')'
             * */
            int dp[] = new int[s.length()];
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    // 判断 pairIndex 和 i 之间，也就是 (pairIndex, i) 之间是否为一个合法的括号字符串
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
                } else if (left < left) {
                    left = right = 0; // -> 1
                }
            }
            left = right = 0;
            // 从右到左遍历字符串，防止类似于 '(((())' 或者 '()(()' 这样的字符串漏掉了上述的遍历
            // 注意注释 1 和 2 处的条件不一样
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') left++;
                else right++;
                if (left == right) {
                    ans = Math.max(ans, left << 1);
                } else if (left > right) { // -> 2
                    left = right = 0;
                }
            }
            return ans;
        }
    }

}
