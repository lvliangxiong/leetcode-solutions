package leetcode.editor.cn;

/**
 * <pre>
 * Given several boxes with different colors represented by different positive numbers.
 * You may experience several rounds to remove boxes until there is no box left. Each time you can choose some
 * continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
 * Find the maximum points you can get.
 *
 *
 *
 * Example 1:
 *
 * Input: boxes = [1,3,2,2,2,3,4,3,1]
 * Output: 23
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 * ----> [1, 3, 3, 3, 1] (1*1=1 points)
 * ----> [1, 1] (3*3=9 points)
 * ----> [] (2*2=4 points)
 *
 *
 *
 * Constraints:
 *
 *     1 <= boxes.length <= 100
 *     1 <= boxes[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-boxes
 * </pre>
 */
class RemoveBoxes {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int removeBoxes(int[] boxes) {
            int[][][] dp = new int[100][100][100];
            return calculatePoints(boxes, 0, boxes.length - 1, 0, dp);
        }

        private int calculatePoints(int[] boxes, int left, int right, int tail, int[][][] dp) {
            if (left > right) return 0;
            if (dp[left][right][tail] != 0) return dp[left][right][tail];
            // 考虑对 right 位置 box 的处理方式：
            // 1. 直接消去，被消去的 box 个数应该是 tail + 『right 位置向左统计连续相同颜色的 box 个数 』
            while (left < right && boxes[right] == boxes[right - 1]) {
                right--;
                tail++;
            }
            dp[left][right][tail] = calculatePoints(boxes, left, right - 1, 0, dp)
                    + (tail + 1) * (tail + 1);
            // 2. 保留，等待 [left, right] 之间的某些 box 先被消除后，将 right 位置的 box 组成更多连续 box 后再消除
            for (int i = left; i < right; i++) {
                if (boxes[i] == boxes[right])
                    // [i+1, right-1] was removed for longer consequence of boxes[right]
                    dp[left][right][tail] = Math.max(dp[left][right][tail],
                            calculatePoints(boxes, left, i, tail + 1, dp) +
                                    calculatePoints(boxes, i + 1, right - 1, 0, dp));
            }
            return dp[left][right][tail];
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
