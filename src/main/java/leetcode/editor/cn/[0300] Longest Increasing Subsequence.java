package leetcode.editor.cn;

import java.util.Arrays;

/**
 * <pre>
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Note:
 *
 *     There may be more than one LIS combination, it is only necessary for you to return the length.
 *     Your algorithm should run in O(n2) complexity.
 *
 * Follow up: Could you improve it to O(n log n) time complexity?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * </pre>
 */
class LongestIncreasingSubsequence {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLIS(int[] nums) {
            int n = nums.length, max = Integer.MIN_VALUE;
            if (n <= 1) return n;
            /*dp[i] 表示以 nums[i] 结尾的最大递增子序列的长度*/
            int[] dp = new int[n];
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class GreedySolution {
        /**
         * 对于同样长度的递增子序列，我们只关心末尾元素小的。
         *
         * @param nums
         * @return
         */
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            if (n <= 1) return n;

            /* end[i] 保存长度为 i 的递增子序列中末尾元素的最小值。
             * end[i] 关于 i 是严格单调递增的。*/
            int[] end = new int[n + 1];
            int maxLen = 1;
            end[maxLen] = nums[0]; // 初始化

            for (int i = 1; i < n; i++) {
                int num = nums[i];
                if (num > end[maxLen]) end[++maxLen] = num;
                else if (num < end[1]) end[1] = num;
                else {
                    /* 找出一个 i，使其满足 end[i] < num < end[i+1]，这样我们就可以使用长度为 i 的递增子序列加上当前 num
                     * 构成一个长度为 i+1 的递增子序列，这个子序列的末尾元素比 end[i+1] 小，因此更新 end[i+1] = num。*/
                    int left = 1, right = maxLen, pos = 0;
                    while (left < right) {
                        int mid = (left + right) >>> 1;
                        if (end[mid] < num) {
                            pos = mid;
                            left = mid + 1;
                        } else if (end[mid] == num) {
                            pos = -1;
                            break;
                        } else {
                            right = mid;
                        }
                    }
                    if (pos != -1) end[pos + 1] = num;
                }
            }
            return maxLen;
        }
    }

}
