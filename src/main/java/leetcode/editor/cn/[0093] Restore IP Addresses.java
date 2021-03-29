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
        private char[] chs;
        int[] ip = new int[4];

        /**
         * 递归遍历所有可能的组合
         *
         * @param s
         * @return
         */
        public List<String> restoreIpAddresses(String s) {
            solutions = new ArrayList<>();
            if (s.length() > 12) return solutions;
            chs = s.toCharArray();

            helper(0, 0);
            return solutions;
        }

        private void helper(int startIndex, int count) {
            if (startIndex == chs.length) {
                if (count == 4) {
                    // 成功找到一种解法
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 4; i++) {
                        sb.append(ip[i]);
                        if (i != 3) sb.append('.');
                    }
                    solutions.add(sb.toString());
                }
                return;
            }
            // 还有没有处理的数字，但是已经分割出了 4 个数字
            if (count == 4) return;

            // 枚举本次分割数字的方法
            if (chs[startIndex] == '0') {
                ip[count] = 0;
                helper(startIndex + 1, count + 1);
            } else {
                int num = 0, cur = startIndex;
                while (cur < chs.length) {
                    num = num * 10 + chs[cur++] - '0';
                    if (num <= 0xFF) {
                        ip[count] = num;
                        helper(cur, count + 1);
                    } else break;
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
