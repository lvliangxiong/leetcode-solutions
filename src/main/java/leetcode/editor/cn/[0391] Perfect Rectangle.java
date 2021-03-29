package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class PerfectRectangle {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isRectangleCover(int[][] rectangles) {
            int X1 = Integer.MAX_VALUE, Y1 = Integer.MAX_VALUE;
            int X2 = Integer.MIN_VALUE, Y2 = Integer.MIN_VALUE;

            Set<String> set = new HashSet<>(); // 定点集合

            int areas = 0; // 所有矩形的面积总和

            for (int[] rectangle : rectangles) {
                int x1 = rectangle[0], y1 = rectangle[1];
                int x2 = rectangle[2], y2 = rectangle[3];

                X1 = Math.min(X1, x1);
                Y1 = Math.min(Y1, y1);
                X2 = Math.max(X2, x2);
                Y2 = Math.max(Y2, y2);

                areas += (x2 - x1) * (y2 - y1);

                String[] points = {x1 + " " + y1, x1 + " " + y2, x2 + " " + y1, x2 + " " + y2};

                for (String point : points) {
                    if (set.contains(point)) {
                        set.remove(point);
                    } else {
                        set.add(point);
                    }
                }
            }

            if (areas != (X2 - X1) * (Y2 - Y1)) return false;
            if (set.size() != 4 || !set.contains(X1 + " " + Y1) || !set.contains(X1 + " " + Y2)
                    || !set.contains(X2 + " " + Y1) || !set.contains(X2 + " " + Y2))
                return false;

            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}