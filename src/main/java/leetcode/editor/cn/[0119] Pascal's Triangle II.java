package leetcode.editor.cn;

import java.util.Arrays;
import java.util.List;

class PascalsTriangleIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> getRow(int rowIndex) {
            Integer[] dp = new Integer[rowIndex + 1];
            dp[0] = 1; // initial state
            for (int i = 1; i <= rowIndex; i++) {
                dp[i] = 1;
                for (int j = i - 1; j > 0; j--) { // start from the end to update
                    dp[j] += dp[j - 1];
                }
            }
            return Arrays.asList(dp);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
