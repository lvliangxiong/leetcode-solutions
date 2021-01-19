package leetcode.editor.cn;

class ContainerWithMostWater {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
            int n = height.length, max = Integer.MIN_VALUE;
            for (int i = 0; i < n - 1; i++) {
                if (height[i] == 0) continue;
                for (int j = Math.max(i + 1, max / height[i] + i); j < n; j++) { // 这里有一个遍历索引的加速，类似于剪枝
                    // 索引为 i 和 j 的 line 分别为容器的左右边界
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
                int area = minH * (right - left); // 计算出当前位置组成的 container 的容量
                if (area > ans) ans = area;
                while (left < right && height[left] <= minH) left++; // 加速迭代
                while (left < right && height[right] <= minH) right--; // 加速迭代
            }
            return ans;
        }
    }

}
