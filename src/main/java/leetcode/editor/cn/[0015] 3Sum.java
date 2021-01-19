package leetcode.editor.cn;

import java.util.*;

/**
 * <pre>
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique
 * triplets in the array which gives the sum of zero.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 *
 * Example 2:
 *
 * Input: nums = []
 * Output: []
 *
 * Example 3:
 *
 * Input: nums = [0]
 * Output: []
 *
 *
 *
 * Constraints:
 *
 *     0 <= nums.length <= 3000
 *     -10^5 <= nums[i] <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * </pre>
 */
class ThreeSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * a + b + c = 0  ======>  a + b = -c
         * 将三数之和问题转化为两数之和问题。
         *
         * @param nums
         * @return
         * @see ContainerWithMostWater.TwoPointerSolution#maxArea(int[])
         */
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            List<List<Integer>> ans = new ArrayList<>();
            if (n <= 2) return ans;

            for (int i = 0; i <= n - 3; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;

                int target = -nums[i];
                // 在一个递增的数组的 [i+1, n-1] 索引范围内，寻找两个和为 target 的数
                int left = i + 1, right = n - 1;
                while (left < right) {
                    int le = nums[left], ri = nums[right];
                    int sum = le + ri;
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        // 移动到下一个不相同的数
                        while (left < right && nums[left] == le) left++;
                        while (left < right && nums[right] == ri) right--;
                    } else if (sum < target) {
                        left++; // 增大
                    } else {
                        right--; // 减小
                    }
                }
            }
            return new ArrayList<>(ans);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
