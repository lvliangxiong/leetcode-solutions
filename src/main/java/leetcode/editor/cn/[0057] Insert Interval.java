package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 * Example 3:
 *
 * Input: intervals = [], newInterval = [5,7]
 * Output: [[5,7]]
 *
 * Example 4:
 *
 * Input: intervals = [[1,5]], newInterval = [2,3]
 * Output: [[1,5]]
 *
 * Example 5:
 *
 * Input: intervals = [[1,5]], newInterval = [2,7]
 * Output: [[1,7]]
 *
 *
 *
 * Constraints:
 *
 *     0 <= intervals.length <= 10^4
 *     intervals[i].length == 2
 *     0 <= intervals[i][0] <= intervals[i][1] <= 10^5
 *     intervals is sorted by intervals[i][0] in ascending order.
 *     newInterval.length == 2
 *     0 <= newInterval[0] <= newInterval[1] <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * </pre>
 */
class InsertInterval {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int left = newInterval[0];
            int right = newInterval[1];
            boolean placed = false;
            List<int[]> ansList = new ArrayList<>();
            for (int[] interval : intervals) {
                if (interval[0] > right) {
                    // 在插入区间的右侧且无交集
                    if (!placed) {
                        ansList.add(new int[]{left, right}); // 插入新区间（可能是和原区间数组中的若干区间合并后的区间）
                        placed = true; // 表明新区间已经插入，后续就不需要再次插入
                    }
                    ansList.add(interval);
                } else if (interval[1] < left) {
                    // 在插入区间的左侧且无交集
                    ansList.add(interval);
                } else {
                    // 与插入区间有交集，计算它们的并集
                    left = Math.min(left, interval[0]);
                    right = Math.max(right, interval[1]);
                }
            }
            if (!placed) {
                ansList.add(new int[]{left, right});
            }
            int[][] ans = new int[ansList.size()][2];
            return ansList.toArray(ans);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}