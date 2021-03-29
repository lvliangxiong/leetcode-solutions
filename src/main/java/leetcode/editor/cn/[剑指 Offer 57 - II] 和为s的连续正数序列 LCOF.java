package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 输入一个正整数 target，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 *
 * 示例 2：
 *
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *
 *
 * 限制：
 *
 *     1 <= target <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 * </pre>
 */
class SumOfContinuousSequenceEqualsToTarget {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] findContinuousSequence(int target) {
            List<int[]> list = new ArrayList<>();
            int limit = (target - 1) >> 1;
            for (int i = 1; i <= limit; ++i) {
                long delta = 1 - 4 * (i - (long) i * i - 2L * target);
                if (delta < 0) {
                    continue;
                }
                int delta_sqrt = (int) Math.sqrt(delta + 0.5);
                if ((long) delta_sqrt * delta_sqrt == delta && (delta_sqrt - 1) % 2 == 0) {
                    int j = (-1 + delta_sqrt) / 2; // 另一个解必然小于 0，不用考虑
                    if (i < j) {
                        int[] res = new int[j - i + 1];
                        for (int k = i; k <= j; ++k) {
                            res[k - i] = k;
                        }
                        list.add(res);
                    }
                }
            }
            return list.toArray(new int[list.size()][]);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class TwoPointerSolution {
        public int[][] findContinuousSequence(int target) {
            List<int[]> list = new ArrayList<>();
            int left = 1, right = 2;
            int sum = 3;
            while (left < right) {
                if (sum == target) {
                    int[] res = new int[right - left + 1];
                    for (int i = left; i <= right; ++i) {
                        res[i - left] = i;
                    }
                    list.add(res);
                    sum -= left++;
                } else if (sum < target) {
                    sum += ++right;
                } else {
                    sum -= left++;
                }
            }
            return list.toArray(new int[list.size()][]);
        }
    }

}





