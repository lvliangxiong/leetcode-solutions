package leetcode.editor.cn;

/**
 * <pre>
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest
 * sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
 * which is more subtle.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * </pre>
 */
class MaximumSubarray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 使用累计和数组，遍历了所有的连续子数组，并计算出它们的和，比较得出最大值。
         * 注意这里使用了一个 mins 数组，计算了累计和的阶段最小值数组，进一步降低了时间复杂度。
         * <p>
         * Time cost: O(n).
         *
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            int len = nums.length;
            int[] sums = new int[len + 1]; // 累计和数组
            int[] mins = new int[len + 1];
            int sum = 0;
            // sums[i] 为原数组中前 i 项累计和，索引为 0 ... (i-1)
            for (int i = 0; i < len; i++) {
                sum += nums[i];
                sums[i + 1] = sum;
            }
            int min = Integer.MAX_VALUE;
            // mins[i] 为累计和数组，也就是 sums 中索引在 [0, i] 之间的元素的最小值
            for (int i = 0; i <= len; i++) {
                min = Math.min(min, sums[i]);
                mins[i] = min;
            }
            int max = Integer.MIN_VALUE;
            for (int end = 1; end <= len; end++) {
                max = Math.max(max, sums[end] - mins[end - 1]);
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DPSolution {
        /**
         * dp[i] 表示以 nums[i] 结尾的『连续子数组的最大和』。
         * <p>
         * dp[i+1] = max(dp[i]+nums[i+1], nums[i+1])
         * <p>
         * 因此我们可以先求出 dp 数组，然后求该数组中的最大值即可。
         * <p>
         * Time Cost: O(n)
         * Space Cost: O(1)
         *
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            // ans 是最终结果，在迭代中进行更新
            // pre 表示上一次 dp 计算的结果
            int ans = Integer.MIN_VALUE, pre = 0;
            for (int num : nums) {
                pre = Math.max(pre + num, num);
                ans = Math.max(ans, pre);
            }
            return ans;
        }
    }

    class DivideAndConquerSolution {

        public int maxSubArray(int[] nums) {
            return get(nums, 0, nums.length - 1).max;
        }

        /**
         * 求给定子序列 [left, right] 的最大子序列和
         *
         * @param nums
         * @param left
         * @param right
         * @return
         */
        private Status get(int[] nums, int left, int right) {
            if (left == right) {
                return new Status(nums[left]);
            }
            int mid = (left + right) >>> 1;
            // divide and conquer in [left, mid] and [mid+1, right]
            Status l = get(nums, left, mid);
            Status r = get(nums, mid + 1, right);
            return pushUp(l, r);
        }

        /**
         * 两个相邻子序列的信息合并
         *
         * @param l
         * @param r
         * @return
         */
        private Status pushUp(Status l, Status r) {
            int sum = l.sum + r.sum;
            int lMax = Math.max(l.lMax, l.sum + r.lMax);
            int rMax = Math.max(r.rMax, r.sum + l.rMax);
            int max = Math.max(Math.max(l.max, r.max), l.rMax + r.lMax);
            return new Status(sum, max, lMax, rMax);
        }

    }

    class Status {
        int sum; // [left, right] 的区间和
        int max; // [left, right] 区间内的最大子序列和
        int lMax; // [left, right] 区间内以 left 为左端点的最大子序列和
        int rMax; // [left, right] 区间内以 right 为右端点的最大子序列和

        public Status(int sum, int max, int lMax, int rMax) {
            this.sum = sum;
            this.max = max;
            this.lMax = lMax;
            this.rMax = rMax;
        }

        public Status(int num) {
            this(num, num, num, num);
        }
    }
}
