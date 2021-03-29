package leetcode.editor.cn;

/**
 * <pre>
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which
 * has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 *
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * </pre>
 */
class MaximumProductSubarray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * min[i] 表示第 i 个元素结尾的连续子数组的最小累计积
         * max[i] 表示第 i 个元素结尾的连续子数组的最大累计积
         * <p>
         * min[i] = min(min[i-1]*nums[i], max[i-1]*nums[i], nums[i])
         * max[i] = max(min[i-1]*nums[i], max[i-1]*nums[i], nums[i])
         *
         * @param nums
         * @return
         */
        public int maxProduct(int[] nums) {
            int min = 1, max = 1, ans = Integer.MIN_VALUE;
            for (int num : nums) {
                int m = Math.max(Math.max(num * min, num * max), num);
                int n = Math.min(Math.min(num * min, num * max), num);
                max = m;
                min = n;
                if (ans < max) ans = max;
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
