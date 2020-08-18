package leetcode.editor.cn;

import java.util.Arrays;

/**
 * <pre>
 * Given a string s, partition s such that every substring of the partition is a palindrome
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 * Example 2:
 *
 * Input: s = "a"
 * Output: 0
 *
 * Example 3:
 *
 * Input: s = "ab"
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 2000
 *     s consists of lower-case English letters only.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
 * </pre>
 */
class PalindromePartitioningIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        char[] chs;
        int n;
        boolean[][] isPalindrome;
        int minCount = Integer.MAX_VALUE;
        int[] counts;

        public int minCut(String s) {
            if (s.equals("")) return 0;
            chs = s.toCharArray();
            n = chs.length;
            isPalindrome = new boolean[n][n];
            counts = new int[n];
            Arrays.fill(counts, Integer.MAX_VALUE);
            computePalindrome();
            dfs(0, 0);
            return minCount - 1;
        }

        /**
         * DFS + Cutting
         *
         * @param start
         * @param count
         */
        private void dfs(int start, int count) {
            if (count > minCount) return;
            if (start == n) {
                minCount = Math.min(minCount, count);
                return;
            }
            if (count < counts[start]) counts[start] = count;
            else return;
            for (int end = n - 1; end >= start; end--) {
                if (isPalindrome[start][end]) {
                    dfs(end + 1, count + 1);
                }
            }
        }

        /**
         * 使用动态规划的方式来计算 isPalindrome 数组，当然也可以使用 Manacher 算法，参考：
         *
         * @see PalindromePartitioning.Solution#manacher(String)
         */
        private void computePalindrome() {
            for (int i = 0; i < n; i++) {
                isPalindrome[i][i] = true;
            }
            // 注意迭代方向
            for (int len = 2; len <= n; len++) { // 迭代子串长度
                for (int start = 0, end = start + len - 1; start <= n - len; start++, end++) {
                    if (len > 2)
                        isPalindrome[start][end] = isPalindrome[start + 1][end - 1] && chs[start] == chs[end];
                    else
                        isPalindrome[start][end] = chs[start] == chs[end];
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DPSolution {
        char[] chs;
        int n;

        public int minCut(String s) {
            if (s.equals("")) return 0;
            chs = s.toCharArray();
            n = chs.length;

            // dp[i] 表示字符串的 [0, i] 子串分割成回文串，需要使用的最小分割数量
            // dp[i] = min(dp[j] + 1)，j 是满足 [j, i] 子串为回文串的所有可能整数
            int[] dp = new int[n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    if (isPalindrome(j, i)) {
                        dp[i] = Math.min(dp[i], j == 0 ? 0 : dp[j - 1] + 1);
                    }
                }
            }
            return dp[n - 1];
        }

        private boolean isPalindrome(int low, int high) {
            while (low < high) {
                if (chs[low++] != chs[high--]) return false;
            }
            return true;
        }
    }

}
