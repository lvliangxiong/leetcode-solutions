package leetcode.editor.cn;

/**
 * <pre>
 * We can scramble a string s to get a string t using the following algorithm:
 *
 *     1. If the length of the string is 1, stop.
 *     2. If the length of the string is > 1, do the following:
 *         - Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x
 *         and y where s = x + y.
 *         - Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may
 *         become s = x + y or s = y + x.
 *         - Apply step 1 recursively on each of the two substrings x and y.
 *
 * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return
 * false.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 * Explanation: One possible scenario applied on s1 is:
 * "great" --> "gr/eat" // divide at random index.
 * "gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
 * "gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at random index each of
 * them.
 * "g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in
 * the same order.
 * "r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
 * The algorithm stops now and the result string is "rgeat" which is s2.
 * As there is one possible scenario that led s1 to be scrambled to s2, we return true.
 *
 * Example 2:
 *
 * Input: s1 = "abcde", s2 = "caebd"
 * Output: false
 *
 * Example 3:
 *
 * Input: s1 = "a", s2 = "a"
 * Output: true
 *
 *
 *
 * Constraints:
 *
 *     s1.length == s2.length
 *     1 <= s1.length <= 30
 *     s1 and s2 consist of lower-case English letters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/scramble-string
 * </pre>
 */
class ScrambleString {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isScramble(String s1, String s2) {
            if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || s1.length() != s2.length())
                return false;

            int n = s1.length();
            /* dp[i][j][len] 表示 s2[j -- j+len) 是否可以由 s1[i -- i+len) 变化而来。
             * 转移方程：
             * dp[i][j][len] = OR{dp[i][j][k] && dp[i+k][j+k][len-k] | 0 < k < len}
             *              or OR{dp[i][j+len-k][k] && dp[i+k][j][len-k] | 0 < k < len}
             * 初始条件：
             * dp[i][j][1] = s1[i] == s2[j]
             */
            boolean[][][] dp = new boolean[n][n][n + 1];
            char[] chs1 = s1.toCharArray();
            char[] chs2 = s2.toCharArray();
            for (int i = n - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    // len = 1
                    dp[i][j][1] = chs1[i] == chs2[j];
                    // len > 1
                    for (int len = 2; i + len <= n && j + len <= n; len++) {
                        for (int k = 1; k < len; k++) {
                            if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                                dp[i][j][len] = true;
                                break;
                            }
                            if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                                dp[i][j][len] = true;
                                break;
                            }
                        }
                    }
                }
            }
            return dp[0][0][n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}