package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LetterCombinationsOfAPhoneNumber {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        char[] chs;
        int end;
        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();

        char[][] mappings = new char[][]{
                null/*0*/, null/*1*/, new char[]{'a', 'b', 'c'}/*2*/, new char[]{'d', 'e', 'f'}/*3*/,
                new char[]{'g', 'h', 'i'}/*4*/, new char[]{'j', 'k', 'l'}/*5*/, new char[]{'m', 'n', 'o'}/*6*/,
                new char[]{'p', 'q', 'r', 's'}/*7*/, new char[]{'t', 'u', 'v'}/*8*/, new char[]{'w', 'x', 'y', 'z'}/*9*/
        };

        public List<String> letterCombinations(String digits) {
            chs = digits.toCharArray();
            end = digits.length();
            if (end == 0) return ans;
            dfs(0);
            return ans;
        }

        /**
         * 回溯 + 递归
         *
         * @param pos
         */
        private void dfs(int pos) {
            // 搜索终止条件
            if (pos == end) {
                ans.add(sb.toString());
                return;
            }
            int d = chs[pos] - '0';
            for (char ch : mappings[d]) {
                sb.append(ch);
                dfs(pos + 1);
                sb.deleteCharAt(sb.length() - 1); // back tracking
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
