package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <pre>
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method
 * signature.
 *
 *
 *
 * Constraints:
 *
 *     intervals[i][0] <= intervals[i][1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * </pre>
 */
class MergeIntervals {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            int n = intervals.length, size = 0;
            int[][] ans = new int[n][];
            for (int i = 0; i < n; i++) {
                int start = intervals[i][0];
                int end = intervals[i][1];
                while (i + 1 < n && intervals[i + 1][0] <= end) {
                    end = Math.max(end, intervals[i + 1][1]);
                    i++;
                }
                ans[size++] = new int[]{start, end};
            }
            return Arrays.copyOf(ans, size);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
