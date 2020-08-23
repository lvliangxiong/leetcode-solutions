package leetcode.editor.cn;

/**
 * <pre>
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 *
 *
 *
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 *
 * 示例 2:
 *
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 *
 *
 * 限制：
 *
 * 1 <= n <= 11
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 */
class NgeTouZiDeDianShuLcof {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * dp[i][j] 表示前 i 轮投骰子的总点数为 j 的次数
         * <p>
         * dp[i+1][j+1] = dp[i][j] + dp[i][j-1] + ... + dp[i][j-5] + dp[i-6]
         *
         * @param n
         * @return
         */
        public double[] twoSum(int n) {
            int[] points = new int[6 * n + 1];
            for (int i = 1; i <= 6; i++) {
                points[i] = 1;
            }
            for (int i = 2; i <= n; i++) { // 遍历投掷次数
                for (int j = 6 * i; j >= i; j--) { // 遍历可能的点数进行更新
                    points[j] = 0;
                    for (int k = 1; k <= 6 && j > k; k++) { // 遍历第 i 轮投掷的可能
                        points[j] += points[j - k];
                    }
                }
                for (int j = 0; j < i; j++) {
                    points[j] = 0; // 将前面不可能出现的点数置为 0
                }
            }
            int sum = 1;
            for (int i = 0; i < n; i++) {
                sum *= 6;
            }
            double[] ret = new double[5 * n + 1];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = points[n + i] * 1.0 / sum;
            }
            return ret;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
