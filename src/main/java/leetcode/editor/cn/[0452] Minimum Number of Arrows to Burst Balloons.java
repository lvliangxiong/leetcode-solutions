package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <pre>
 * There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the
 * start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence
 * the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most
 * 104 balloons.
 *
 * An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend
 * bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An
 * arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be
 * shot to burst all balloons.
 *
 * Example:
 *
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 *
 * Output:
 * 2
 *
 * Explanation:
 * One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at
 * x = 11 (bursting the other two balloons).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
 * </pre>
 */
class MinimumNumberOfArrowsToBurstBalloons {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMinArrowShots(int[][] points) {
            Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
            int n = points.length, count = 0;
            for (int i = 0; i < n; i++) {
                int start = points[i][0];
                int end = points[i][1];
                while (i + 1 < n && points[i + 1][0] <= end) {
                    start = points[i + 1][0]; // start 越来越大
                    end = Math.min(end, points[i + 1][1]); // end 越来越小
                    i++;
                }
                count++;
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
