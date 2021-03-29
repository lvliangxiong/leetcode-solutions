package leetcode.editor.cn;

/**
 * <pre>
 * You are playing the following Nim Game with your friend:
 *
 *     Initially, there is a heap of stones on the table.
 *     You and your friend will alternate taking turns, and you go first.
 *     On each turn, the person whose turn it is will remove 1 to 3 stones from the heap.
 *     The one who removes the last stone is the winner.
 *
 * Given n, the number of stones in the heap, return true if you can win the game assuming both you and your friend
 * play optimally, otherwise return false.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: false
 * Explanation: These are the possible outcomes:
 * 1. You remove 1 stone. Your friend removes 3 stones, including the last stone. Your friend wins.
 * 2. You remove 2 stones. Your friend removes 2 stones, including the last stone. Your friend wins.
 * 3. You remove 3 stones. Your friend removes the last stone. Your friend wins.
 * In all outcomes, your friend wins.
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: true
 *
 * Example 3:
 *
 * Input: n = 2
 * Output: true
 *
 *
 * Constraints:
 *
 *     1 <= n <= 2^31 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nim-game
 * </pre>
 */
class NimGame {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canWinNim(int n) {
            /* 以 dp[i] 表示还剩 i 个石头时，先手玩家是否会赢。
             *
             * 那么就有 dp[i] = !(dp[i-1] && dp[i-2] && dp[i-3])。
             * 此递推关系式表示只有当 dp[i-1] dp[i-2] dp[i-3] 全部为 true，也就是说不管先手玩家拿走 1 颗石头，还是 2 颗石头，
             * 还是 3 颗石头，最终都是对手赢的情况下，先手玩家才一定会输，否则先手玩家总可以找到一种赢的方案。
             *
             * dp[1] = true, dp[2] = true, dp[3] = true,
             * dp[4] = false, dp[5] = true, dp[6] = true, dp[7] = true,
             * dp[8] = false, dp[9] = true, dp[10] = true, dp[11] = true,
             * dp[12] = false, dp[13] = true, ...
             *
             * dp[4*k] = false, dp[other] = true;
             * 因此只需要判断给定的 n 是否是 4 的倍数即可。
             * */
            return n % 4 != 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
