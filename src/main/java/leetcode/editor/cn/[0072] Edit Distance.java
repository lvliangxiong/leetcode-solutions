package leetcode.editor.cn;

/**
 * <pre>
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 *
 *     Insert a character
 *     Delete a character
 *     Replace a character
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 *
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 *
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * </pre>
 */
class EditDistance {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 编辑距离算法（Edit Distance）被数据科学家广泛应用，是用作机器翻译和语音识别评价标准的基本算法。
         * <p>
         * 1. 对单词 A 删除一个字符和对单词 B 插入一个字符是等价的。
         * 2. 对单词 A 插入一个字符和对单词 B 删除一个字符是等价的。
         * 3. 对单词 A 替换一个字符和对单词 B 替换一个字符是等价的。
         * <p>
         * 因此，本质不同的操作实际上只有三种：
         * 1. 在单词 A 中插入一个字符。
         * 2. 在单词 B 中插入一个字符。
         * 3. 修改单词 A 的一个字符。
         * <p>
         *
         * @param word1
         * @param word2
         * @return
         */
        public int minDistance(String word1, String word2) {
            int n = word1.length();
            int m = word2.length();

            // 有一个字符串为空串
            if (n * m == 0)
                return n + m;

            // DP 数组
            int[][] dp = new int[n + 1][m + 1];

            // 边界状态初始化
            for (int i = 0; i < n + 1; i++) {
                dp[i][0] = i;
            }
            for (int j = 0; j < m + 1; j++) {
                dp[0][j] = j;
            }

            // 计算所有 DP 值
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
                    int insertI = dp[i - 1][j] + 1;
                    int insertJ = dp[i][j - 1] + 1;
                    int replaceIJ = dp[i - 1][j - 1];
                    if (word1.charAt(i - 1) != word2.charAt(j - 1))
                        replaceIJ += 1;
                    dp[i][j] = Math.min(insertI, Math.min(insertJ, replaceIJ));

                }
            }
            return dp[n][m];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
