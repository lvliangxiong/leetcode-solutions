package leetcode.editor.cn;

/**
 * <pre>
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You
 * are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right]
 * coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 *
 *     You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 *     0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * Example:
 *
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 * </pre>
 */
class BurstBalloons {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] val;
        int[][] memo;


        /**
         * 每次戳爆一个气球，会导致原本不相邻的气球，现在相邻。如果将全过程反过来看，那么可以将戳爆气球的过程看成是添加气球的过程。
         * <pre>
         *     0 1 2 3
         *
         *     3 1 5 8      戳爆 nums[1]，得分 3*1*5=15
         *     3   5 8      戳爆 nums[2]，得分 3*5*8=120
         *     3     8      戳爆 nums[0]，得分 1*3*8=24
         *           8      戳爆 nums[3]，得分 1*8*1=8
         *                               总得分 167 分
         * </pre>
         * 将上述过程从底部向上看，则可以看作是在一个数组中，添加气球的过程。
         *
         * @param nums
         * @return
         */
        public int maxCoins(int[] nums) {
            int n = nums.length;
            val = new int[n + 2];
            memo = new int[n + 2][n + 2]; // 存储中间计算结果，加速计算
            val[0] = val[n + 1] = 1; // 首尾添加 1，便于后续计算
            System.arraycopy(nums, 0, val, 1, n);
            return solve(0, n + 1);
        }

        /**
         * solve(left, right) 表示将开区间 (left, right) 内的位置全部填满气球能够得到的最多硬币数。
         *
         * @param left
         * @param right
         * @return
         */
        private int solve(int left, int right) {
            if (memo[left][right] != 0) return memo[left][right];
            int max = Integer.MIN_VALUE;
            if (left < right - 1) {
                // (left, right) 至少包含一个元素
                // 枚举填满开区间 (left, right) 时，第一次被添加的元素位置为 mid
                for (int mid = left + 1; mid < right; mid++) {
                    // 这里可以看作除了(left, right)区间内的元素，其它位置均已经被添加过了，
                    // 可以参考注释总的例子反向逆推进行理解。
                    max = Math.max(solve(left, mid) + solve(mid, right) + val[left] * val[mid] * val[right], max);
                }
            } else {
                max = 0;
            }
            if (max != 0) memo[left][right] = max;
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DPSolution {
        /**
         * {@link Solution} 体现的是一种自顶向下的分治递归思想，计算过程中还采用了 memo 数组记录中间计算结果，避免重复计算，
         * 降低时间复杂度。
         * <p>
         * 此实现使用的是自底向上的动态规划。
         *
         * @param nums
         * @return
         */
        public int maxCoins(int[] nums) {
            int n = nums.length;
            int[][] dp = new int[n + 2][n + 2];
            int[] val = new int[n + 2];
            val[0] = val[n + 1] = 1;
            System.arraycopy(nums, 0, val, 1, n);

            // dp[i][j] 表示填满开区间 (i,j) 能得到的最多硬币数
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 2; j <= n + 1; j++) {
                    /* i : n-1 -----> 0
                     * j : i+2 -----> n+1
                     * 可以保证 dp[i][mid] 和 dp[mid][j] 都计算过。*/
                    for (int mid = i + 1; mid < j; mid++) {
                        dp[i][j] = Math.max(dp[i][j], val[i] * val[mid] * val[j] + dp[i][mid] + dp[mid][j]);
                    }
                }
            }
            return dp[0][n + 1];
        }
    }

}
