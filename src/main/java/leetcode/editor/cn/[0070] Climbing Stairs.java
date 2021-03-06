package leetcode.editor.cn;

/**
 * <pre>
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 *
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 *
 *
 * Constraints:
 *
 *     1 <= n <= 45
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * </pre>
 */
class ClimbingStairs {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int climbStairs(int n) {
            if (n <= 2) return n;
            int ways = 2, pre = 1; // 2 级阶梯和 1 级阶梯的方法数
            for (int i = 3; i <= n; i++) {
                int tmp = ways;
                ways = ways + pre;
                pre = tmp;
            }
            return ways;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
