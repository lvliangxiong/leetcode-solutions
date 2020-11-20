package leetcode.editor.cn;

import java.util.*;

/**
 * <pre>
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your
 * result is the smallest in lexicographical order among all possible results.
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 *
 * Example 2:
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 * Constraints:
 *
 *     1 <= s.length <= 10^4
 *     s consists of lowercase English letters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
 * </pre>
 */
class RemoveDuplicateLetters {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeDuplicateLetters(String s) {
            int[] count = new int[26]; // 统计 a - z 的出现次数
            int pos = 0;
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }
            for (int i = 0; i < s.length(); i++) {
                // 最左侧的字符是在能保证其他字符至少能出现一次情况下的最小字符
                if (s.charAt(i) < s.charAt(pos)) pos = i;
                if (--count[s.charAt(i) - 'a'] == 0) break;
            }
            return s.length() == 0 ? "" :
                    s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class StackSolution {
        public String removeDuplicateLetters(String s) {
            Deque<Character> stack = new ArrayDeque<>();
            HashSet<Character> seen = new HashSet<>(); // 存储已经在 stack 中的字符
            HashMap<Character, Integer> lastOccurrence = new HashMap<>(); // 记录字符最后一次出现的索引
            for (int i = 0; i < s.length(); i++)
                lastOccurrence.put(s.charAt(i), i);
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (!seen.contains(ch)) {
                    // if the character on the stack top:
                    //     1. exists
                    //     2. is greater than c so removing it will make the string smaller
                    //     3. it's not the last occurrence so it can be removed
                    // we remove it from the stack to keep the solution optimal
                    while (!stack.isEmpty() && ch < stack.peek() && lastOccurrence.get(stack.peek()) > i) {
                        seen.remove(stack.pop());
                    }
                    seen.add(ch);
                    stack.push(ch);
                }
            }
            StringBuilder sb = new StringBuilder(stack.size());
            Iterator<Character> iterator = stack.descendingIterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
            }
            return sb.toString();
        }
    }

}
