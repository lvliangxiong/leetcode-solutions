package leetcode.editor.cn;

/**
 * <pre>
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical
 * lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
 * with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 * Example:
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * </pre>
 */
class ContainerWithMostWater {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
            int n = height.length, max = Integer.MIN_VALUE;
            for (int i = 0; i < n - 1; i++) {
                if (height[i] == 0) continue;
                for (int j = Math.max(i + 1, max / height[i] + i); j < n; j++) {
                    int area = Math.min(height[i], height[j]) * (j - i);
                    if (area > max) max = area;
                }
            }
            return max < 0 ? 0 : max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class TwoPointerSolution {
        public int maxArea(int[] height) {
            int n = height.length, ans = Integer.MIN_VALUE;
            int left = 0, right = n - 1;
            while (left != right) {
                int minH = Math.min(height[left], height[right]);
                int area = minH * (right - left);
                if (area > ans) ans = area;
                while (left < right && height[left] <= minH) left++; // 加速迭代
                while (left < right && height[right] <= minH) right--; // 加速迭代
            }
            return ans;
        }
    }

}
