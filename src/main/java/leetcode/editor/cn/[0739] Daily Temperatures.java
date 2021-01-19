package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <pre>
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days
 * you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0
 * instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be
 * [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range
 * [30, 100].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * </pre>
 */
class DailyTemperatures {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] dailyTemperatures(int[] T) {
            int n = T.length;
            Deque<Integer> stack = new ArrayDeque(); // 维护一个单调递减的栈
            int[] distance = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
                if (stack.isEmpty()) distance[i] = 0;
                else distance[i] = stack.peek() - i;
                stack.push(i);
            }
            return distance;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DpSolution {
        public int[] dailyTemperatures(int[] T) {
            if (T == null || T.length == 0) {
                return T;
            }
            int len = T.length;
            int[] ans = new int[len];
            ans[len - 1] = 0;
            for (int i = len - 2; i >= 0; i--) {
                for (int j = i + 1; j < len; j += ans[j]) { // 注意这里的 step 是 ans[j]
                    if (T[j] > T[i]) {
                        ans[i] = j - i;
                        break;
                    }
                    if (ans[j] == 0) {
                        break;
                    }
                }
            }
            return ans;
        }
    }


}
