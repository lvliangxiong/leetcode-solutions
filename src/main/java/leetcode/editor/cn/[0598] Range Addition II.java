package leetcode.editor.cn;

class RangeAdditionIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxCount(int m, int n, int[][] ops) {
            int xMin = m, yMin = n;
            for (int[] op : ops) {
                int x = op[0];
                int y = op[1];
                xMin = Math.min(xMin, x);
                yMin = Math.min(yMin, y);
            }
            return xMin * yMin;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}