package leetcode.editor.cn;

import java.util.Arrays;

/**
 * <pre>
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * </pre>
 */
class LongestCommonPrefix {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            int n = strs.length;
            if (n == 0) return "";
            if (n == 1) return strs[0];

            StringBuilder sb = new StringBuilder();
            int index = 0; // 比较所有字符串索引为 index 位置的字符
            boolean stop = false;
            while (true) {
                int count = 0;
                if (index >= strs[0].length()) break;
                char ch = strs[0].charAt(index);
                for (int i = 1; i < n; i++) {
                    if (index >= strs[i].length() || strs[i].charAt(index) != ch) {
                        stop = true;
                        break;
                    }
                }
                if (stop) break;
                sb.append(ch);
                index++;
            }
            return sb.toString();
        }

        /**
         * 先排序，后比较第一个和最后一个字符串。
         *
         * @param strs
         * @return
         */
        public String longestCommonPrefixBySorting(String[] strs) {
            int n = strs.length;
            if (n == 0) return "";
            if (n == 1) return strs[0];

            Arrays.sort(strs);
            // compare the first and last string
            String first = strs[0];
            String last = strs[n - 1];
            int stop = first.length();
            for (int i = 0; i < first.length(); i++) {
                if (i >= last.length() || first.charAt(i) != last.charAt(i)) {
                    stop = i;
                    break;
                }
            }
            return first.substring(0, stop);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
