package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class PascalsTriangle {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> ans = new ArrayList<>();
            if (numRows >= 1) ans.add(new ArrayList<Integer>() {{
                add(1);
            }});
            if (numRows >= 2) ans.add(new ArrayList<Integer>() {{
                add(1);
                add(1);
            }});
            if (numRows >= 3) {
                for (int row = 3; row <= numRows; row++) {
                    // 第 row 行有 row 个元素，row starts from 1
                    List<Integer> rowElements = new ArrayList<>();
                    List<Integer> pre = ans.get(ans.size() - 1);
                    rowElements.add(1);
                    for (int i = 1; i < row - 1; i++) {
                        rowElements.add(pre.get(i - 1) + pre.get(i));
                    }
                    rowElements.add(1);
                    ans.add(rowElements);
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
