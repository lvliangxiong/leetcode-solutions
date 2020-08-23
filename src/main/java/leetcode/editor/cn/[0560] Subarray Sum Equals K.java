package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum
 * equals to k.
 *
 * Example 1:
 *
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 *
 *
 * Constraints:
 *
 *     The length of the array is in range [1, 20,000].
 *     The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * </pre>
 */
class SubarraySumEqualsK {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int subarraySum(int[] nums, int k) {
            int n = nums.length;
            int[] sums = new int[n + 1];
            for (int i = 0, sum = 0; i < n; ) {
                sum += nums[i];
                i++;
                sums[i] = sum;
            }
            Map<Integer, Integer> map = new HashMap<>();
            int count = 0;
            for (int i = 0; i <= n; i++) {
                int target = sums[i] - k;
                count += map.getOrDefault(target, 0);
                map.put(sums[i], map.getOrDefault(sums[i], 0) + 1);
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
