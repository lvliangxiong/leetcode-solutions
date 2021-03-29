package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Given an integer array nums and an integer k, return true if the array has a continuous subarray of size at least
 * two, that sums up to a multiple of k. That is, it sums up to n * k where n is also an integer.
 *
 *
 * Example 1:
 *
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 *
 * Example 2:
 *
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 *
 * Example 3:
 *
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 10^4
 *     0 <= nums[i] <= 10^9
 *     0 <= sum(nums[i]) <= 2^31 - 1
 *     -2^31 <= k <= 2^31 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
 * </pre>
 */
class ContinuousSubarraySum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkSubarraySum(int[] nums, int k) {
            int[] prefixSum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < prefixSum.length; i++) {
                int num = k == 0 ? prefixSum[i] : prefixSum[i] % k;
                if (map.containsKey(num)) {
                    if (i - map.get(num) >= 2)
                        return true;
                } else map.put(num, i);
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}