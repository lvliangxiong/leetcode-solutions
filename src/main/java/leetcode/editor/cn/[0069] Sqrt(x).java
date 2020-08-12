package leetcode.editor.cn;

/**
 * <pre>
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 *
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of
 * the result is returned.
 *
 * Example 1:
 *
 * Input: 4
 * Output: 2
 *
 * Example 2:
 *
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 *              the decimal part is truncated, 2 is returned.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * </pre>
 */
class Sqrtx {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 二分法查找平方根，注意最终的结果判断。
         *
         * @param x
         * @return
         */
        public int mySqrt(int x) {
            int low = 1, high = x;
            while (low < high) {
                int mid = (low + high) >>> 1;
                if (mid < x / mid) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low <= x / low ? low : low - 1;
        }

        /**
         * 袖珍计算器算法：利用指数函数 exp(x) 和对数函数 In(x) 计算平方根函数。
         * <p>
         * x^0.5 = e^(In(x^0.5)) = e^(0.5*In(x))
         *
         * @param x
         * @return
         */
        public int sqrtByPocketCalculatorAlgorithm(int x) {
            if (x == 0) return 0;
            int ans = (int) Math.exp(0.5 * Math.log(x));
            return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
        }

        /**
         * 牛顿法求解平方根
         *
         * @param x
         * @return
         */
        public int sqrtByNewtonMethod(int x) {
            if (x == 0) {
                return 0;
            }
            double C = x; // y = x^2 - C 的根即为我们需要求解的答案
            double x0 = C; // 起始解
            while (true) {
                double xi = 0.5 * (x0 + C / x0); // 下一个近似解
                // 终止条件
                if (Math.abs(x0 - xi) < 1e-7) {
                    break;
                }
                x0 = xi;
            }
            return (int) x0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
