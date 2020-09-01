package leetcode.editor.cn;

/**
 * <pre>
 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the
 * array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not
 * be available for the next player. This continues until all the scores have been chosen. The player with the maximum
 * score wins.
 *
 * Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his
 * score.
 *
 * Example 1:
 *
 * Input: [1, 5, 2]
 * Output: False
 * Explanation: Initially, player 1 can choose between 1 and 2.
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will
 * be left with 1 (or 2).
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 * Hence, player 1 will never be the winner and you need to return False.
 *
 *
 *
 * Example 2:
 *
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player
 * 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 *
 *
 *
 * Constraints:
 *
 *     1 <= length of the array <= 20.
 *     Any scores in the given array are non-negative integers and will not exceed 10,000,000.
 *     If the scores of both players are equal, then player 1 is still the winner.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/predict-the-winner
 * </pre>
 */
class PredictTheWinner {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean PredictTheWinner(int[] nums) {
            return total(nums, 0, nums.length - 1, 1) >= 0;
        }

        /**
         * @param nums
         * @param start
         * @param end
         * @param turn  这一轮需要选择分数的玩家，1 代表先手玩家，-1 代表后手玩家
         * @return 这一轮对最终结果（差值）的影响
         */
        public int total(int[] nums, int start, int end, int turn) {
            // 仅剩余一个分数尚未选择
            if (start == end) {
                return nums[start] * turn;
            }
            // 多个分数尚未选择
            int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
            int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
            /* 根据题意，先手玩家会尽量让自己的得分大，而后手玩家也是一样。*/
            return turn == 1 ? Math.max(scoreStart, scoreEnd) : Math.min(scoreStart, scoreEnd);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DPSolution {
        public boolean PredictTheWinner(int[] nums) {
            int length = nums.length;
            // dp[i][j] 表示数组剩余边界为 [i,j] 时，当前玩家和另一个玩家之间的分数的差值的最大值
            // 注意这里不分先手和后手玩家
            int[][] dp = new int[length][length];
            // 初始化
            for (int i = 0; i < length; i++) {
                dp[i][i] = nums[i];
            }

            for (int i = length - 2; i >= 0; i--) {
                for (int j = i + 1; j < length; j++) {
                    dp[i][j] = Math.max(nums[i] - dp[i + 1][j] /* 当前玩家选择起始的分数 */,
                            nums[j] - dp[i][j - 1]/* 当前玩家选择末尾的分数 */);
                }
            }
            return dp[0][length - 1] >= 0;
        }
    }

}
