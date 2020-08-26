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
                null, null, new char[]{'a', 'b', 'c'}, new char[]{'d', 'e', 'f'},
                new char[]{'g', 'h', 'i'}, new char[]{'j', 'k', 'l'}, new char[]{'m', 'n', 'o'},
                new char[]{'p', 'q', 'r', 's'}, new char[]{'t', 'u', 'v'}, new char[]{'w', 'x', 'y', 'z'}
        };

        public List<String> letterCombinations(String digits) {
            chs = digits.toCharArray();
            end = digits.length();
            if (end == 0) return ans;
            dfs(0);
            return ans;
        }

        private void dfs(int start) {
            if (start == end) {
                ans.add(sb.toString());
                return;
            }
            int d = chs[start] - '0';
            for (char ch : mappings[d]) {
                sb.append(ch);
                dfs(start + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
