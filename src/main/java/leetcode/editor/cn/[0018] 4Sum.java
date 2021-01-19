package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that
 * a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 *
 * Notice that the solution set must not contain duplicate quadruplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * Example 2:
 *
 * Input: nums = [], target = 0
 * Output: []
 *
 *
 *
 * Constraints:
 *
 *     0 <= nums.length <= 200
 *     -10^9 <= nums[i] <= 10^9
 *     -10^9 <= target <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * </pre>
 */
class FourSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> ans = new ArrayList<>();
            if (nums == null || nums.length < 4) return ans;

            int n = nums.length;
            Arrays.sort(nums);
            for (int i = 0; i < n - 3; i++) {
                if (i > 0 && nums[i - 1] == nums[i]) continue;
                int first = nums[i];
                for (int j = i + 1; j < n - 2; j++) {
                    if (j > i + 1 && nums[j - 1] == nums[j]) continue;
                    int second = nums[j];
                    for (int k = j + 1; k < n - 1; k++) {
                        if (k > j + 1 && nums[k - 1] == nums[k]) continue;
                        int third = nums[k];
                        for (int l = k + 1; l < n; l++) {
                            if (l > k + 1 && nums[l - 1] == nums[l]) continue;
                            int fourth = nums[l];
                            if (first + second + third + fourth == target)
                                ans.add(Arrays.asList(first, second, third, fourth));
                        }
                    }
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class OptimizedSolution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> ans = new ArrayList<>();
            if (nums == null || nums.length < 4) return ans;

            int n = nums.length;
            Arrays.sort(nums);
            for (int i = 0; i < n - 3; i++) {
                if (i > 0 && nums[i - 1] == nums[i]) continue;
                int first = nums[i];

                if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break; // 剪枝
                if (nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue;

                for (int j = i + 1; j < n - 2; j++) {
                    if (j > i + 1 && nums[j - 1] == nums[j]) continue;
                    int second = nums[j];

                    if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break; // 剪枝
                    if (nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) continue;

                    int left = j + 1, right = n - 1;
                    int targetOfLastTwoSum = target - first - second;
                    while (left < right) {
                        int third = nums[left], fourth = nums[right];
                        int lastTwoSum = third + fourth;
                        if (lastTwoSum == targetOfLastTwoSum) {
                            ans.add(Arrays.asList(first, second, third, fourth));
                            while (left < right && nums[left] == third) left++;
                            while (left < right && nums[right] == fourth) right--;
                        } else if (lastTwoSum < targetOfLastTwoSum) {
                            left++;
                        } else {
                            right--;
                        }
                    }
                }
            }
            return ans;
        }
    }

}
