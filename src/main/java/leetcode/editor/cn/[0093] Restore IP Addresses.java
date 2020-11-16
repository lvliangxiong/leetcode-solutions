package leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * A valid IP address consists of exactly four integers (each integer is between 0 and 255) separated by single points.
 *
 * Example:
 *
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * </pre>
 */
class RestoreIpAddresses {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private List<String> solutions;

        public List<String> restoreIpAddresses(String s) {
            solutions = new ArrayList<>();
            if (s.length() > 12) return solutions;
            backtrack(s.toCharArray(), 0, null, new StringBuilder(), 0);
            return solutions;
        }

        private void backtrack(char[] chars, int index, Integer num, StringBuilder sb, int count) {
            if (count > 4) return;
            if (count == 4 && num != null) return;
            if (index == chars.length) {
                if (count == 3) {
                    if (num <= 255) {
                        sb.append(num);
                        solutions.add(sb.toString());
                    }
                }
                return;
            }

            // 两种选择:
            // op1：将索引为 index 的 char 合并到 num 中
            // op2：重新开辟一个新数

            if (num == null || num != 0) {
                int newNum = (num == null ? 0 : num * 10) + chars[index] - '0';
                if (newNum <= 255) {
                    backtrack(chars, index + 1, newNum, new StringBuilder(sb), count);
                }
            }
            if (num != null) {
                backtrack(chars, index + 1, chars[index] - '0',
                        sb.append(num).append('.'), count + 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
