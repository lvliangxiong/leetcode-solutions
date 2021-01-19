package leetcode.editor.cn;

/**
 * <pre>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * </pre>
 */

import java.util.HashMap;
import java.util.Map;

class TwoSum {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>(); // 存储『数组元素』和其『索引』
            for (int i = 0; i < nums.length; i++) {
                int sub = target - nums[i];
                // 如果 Map 中包含了我们需要的元素，直接返回该元素对应的数组索引和当前索引
                if (map.containsKey(sub)) {
                    return new int[]{map.get(sub), i};
                } else {
                    // 将已遍历过的数组元素添加到 Map 中
                    map.put(nums[i], i);
                }
            }
            return null;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
