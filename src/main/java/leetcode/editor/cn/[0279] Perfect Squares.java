package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which
 * sum to n.
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * </pre>
 */
class PerfectSquares {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numSquares(int n) {
            int[] dp = new int[n + 1];
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + 1;
                for (int j = (int) Math.sqrt(i); j > 1; j--) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
            return dp[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class GreedySolution {
        Set<Integer> squares = new HashSet<Integer>();

        /**
         * 计算数字 n 是否能够由 squares 内的 count 个元素（可重复使用）组合而成
         *
         * @param n
         * @param count
         * @return
         */
        private boolean CanBeDividedBy(int n, int count) {
            if (count == 1) {
                return squares.contains(n);
            }

            for (Integer square : squares) {
                if (CanBeDividedBy(n - square, count - 1)) {
                    return true;
                }
            }
            return false;
        }

        public int numSquares(int n) {
            squares.clear();

            int max = (int) Math.sqrt(n);
            for (int i = 1; i <= max; ++i) {
                squares.add(i * i);
            }

            int count = 1;
            for (; count <= n; ++count) {
                if (CanBeDividedBy(n, count))
                    return count;
            }
            return count;
        }
    }


    class BFSSolution {

        public int numSquares(int n) {
            List<Integer> squares = new ArrayList<>();

            int max = (int) Math.sqrt(n);
            for (int i = 1; i <= max; ++i) {
                squares.add(i * i);
            }

            Set<Integer> queue = new HashSet<>();
            queue.add(n);

            int level = 0;
            while (queue.size() > 0) {
                level += 1;
                Set<Integer> next = new HashSet<>(); // 避免重复值

                for (Integer remainder : queue) {
                    for (Integer square : squares) {
                        if (remainder.equals(square)) {
                            return level;
                        } else if (remainder < square) {
                            break;
                        } else {
                            next.add(remainder - square);
                        }
                    }
                }
                queue = next;
            }
            return level;
        }
    }


    class MathSolution {

        private boolean isSquare(int n) {
            int sq = (int) Math.sqrt(n);
            return n == sq * sq;
        }

        public int numSquares(int n) {
            // four-square theorems.
            while (n % 4 == 0)
                n /= 4;
            if (n % 8 == 7)
                return 4;

            if (isSquare(n))
                return 1;

            // enumeration to check if the number can be decomposed into sum of two squares.
            for (int i = 1; i * i <= n; ++i) {
                if (isSquare(n - i * i))
                    return 2;
            }

            // bottom case of three-square theorem.
            return 3;
        }
    }

}
