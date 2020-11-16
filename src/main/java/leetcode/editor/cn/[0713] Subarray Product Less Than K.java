package leetcode.editor.cn;

/**
 * <pre>
 * Your are given an array of positive integers nums.
 *
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less
 * than k.
 *
 * Example 1:
 *
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6],
 * [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 *
 * Note:
 * 0 < nums.length <= 50000.
 * 0 < nums[i] < 1000.
 * 0 <= k < 10^6.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k
 * </pre>
 */
class SubarrayProductLessThanK {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 这里考虑了乘法溢出的情况，实际上，根据题意，product * nums[end] < 10^6 * 10^3 = 10^9 是不会产生溢出的，因此
         * 可以简化循环内部的判断。
         *
         * @param nums
         * @param k
         * @return
         */
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            int start = 0, end = 0, n = nums.length, product = 1, count = 0;
            while (end < n) {
                // 逐步剔除左边界，直到以 end 结尾的子数组之积小于 k，或者 start == end
                while (start < end && (product > k / nums[end] || product * nums[end] == k)) {
                    product /= nums[start];
                    start++;
                }
                if (product <= k / nums[end] && product * nums[end] != k) {
                    // 满足条件
                    count += (end - start + 1);
                    product *= nums[end];
                    end++;
                } else {
                    // 仅有一个元素，仍然不满足条件
                    product = 1;
                    start++;
                    end++;
                }
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
