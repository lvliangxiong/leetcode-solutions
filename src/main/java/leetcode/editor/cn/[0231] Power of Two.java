package leetcode.editor.cn;

/**
 * <pre>
 * Given an integer n, write a function to determine if it is a power of two.
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: true
 * Explanation: 2^0 = 1
 *
 * Example 2:
 *
 * Input: n = 16
 * Output: true
 * Explanation: 2^4 = 16
 *
 * Example 3:
 *
 * Input: n = 3
 * Output: false
 *
 * Example 4:
 *
 * Input: n = 4
 * Output: true
 *
 * Example 5:
 *
 * Input: n = 5
 * Output: false
 *
 * Constraints:
 *
 *     -2^31 <= n <= 2^31 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-two
 * </pre>
 */
class PowerOfTwo {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 使用递归进行求解，当然也可以使用循环
         *
         * @param n
         * @return
         */
        public boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
            if (n == 1) return true;
            if ((n & 1) == 1) return false;
            return isPowerOfTwo(n / 2);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class ConstantSolution {
        /**
         * 2 的幂的特点是其二进制表示中：符号位为 0，其它位有且仅有一个 1
         * <p>
         * 常见位运算：
         * x & (-x)        ==>         保留 x 的 rightmost 的 1，将其它所有位置为 0
         * x & (x - 1)     ==>         去掉 x 的 rightmost 的 1，全其它位置不变
         * <p>
         * 因此判断一个正数 n 是否为 2 的幂，只需要判断 n == (n & -n) 或者 n & (n-1) == 0
         *
         * @param n
         * @return
         */
        public boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
            return n == (n & (-n));
        }
    }

}
