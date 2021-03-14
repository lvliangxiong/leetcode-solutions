package leetcode.editor.cn;

/**
 * <pre>
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * </pre>
 */
class LongestPalindromicSubstring {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 中心扩展算法
         * <p>
         * index:   0   1   2   3
         * str:     a   b   c   d
         * center:  0 1 2 3 4 5 6
         * <p>
         * 时间复杂度为 O(n^2)
         *
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) return "";
            // 保证了 s 的长度至少为 1
            char[] chars = s.toCharArray();
            int maxLen = 1, start = 0, end = 0;
            for (int i = 0; i < chars.length; i++) {
                int len1 = expand(chars, i, i); // 以 i 为中心的最长回文子串
                int len2 = expand(chars, i, i + 1); // 以 i 和 i+1 之间的位置为中心的最长回文子串
                int len = Math.max(len1, len2);
                if (maxLen < len) {
                    start = i - ((len - 1) >> 1);
                    end = i + (len >> 1);
                    maxLen = end - start + 1;
                }
            }
            return s.substring(start, end + 1);
        }

        private int expand(char[] chars, int left, int right) {
            while (left >= 0 && right < chars.length) {
                if (chars[left] != chars[right]) break;
                left--;
                right++;
            }
            return (right - left) - 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class ManacherSolution {
        public String longestPalindrome(String s) {
            if (s.equals("")) return s;
            // 为原字符串添加 '#' 简化后面的计算
            // 例如 "aabbcc" 会被转变为："#a#a#b#b#c#c#"
            int len = (s.length() << 1) + 1;
            char[] chars = new char[len];
            for (int i = 0; i < len; i++)
                chars[i] = (i & 1) == 0 ? '#' : s.charAt(i - 1 >> 1);

            int center = -1; // 当前已知的具有最右边界的回文子串的对称中心
            int right = -1; // 以 center 为中心的最长回文子串的右边界
            int[] arms = new int[len]; // 存储已计算过的『臂长』，例如长度为 2n+1 的回文子串，其臂长就是 n
            int start = 0, end = -1; // 遍历过程中的最长子串

            // 注意添加了 '#' 后，中心扩展的位置（用字符 # 代替了原字符之间的位置）
            // 注意边界位置的字符都是 #，并且回文串的长度都是奇数
            for (int i = 1; i < len - 1; i++) {
                int arm;
                if (right > i) {
                    // 使用前面计算过的结果简化计算 rightSym --- iSym ------ center ------ i --- right
                    int iSym = (center << 1) - i;
                    int minArm = Math.min(arms[iSym], right - i);
                    arm = computeArm(chars, i - minArm, i + minArm);
                } else {
                    arm = computeArm(chars, i - 1, i + 1);
                }
                arms[i] = arm;
                // 更新 center 和 right 的位置
                if (i + arm > right) {
                    center = i;
                    right = i + arm;
                }
                // 更新最后的结果
                if ((arm << 1) + 1 > end - start) {
                    start = i - arm;
                    end = i + arm;
                }
            }
            // 注意这里的索引位置和原字符串的索引位置需要进行映射
            return s.substring(start >> 1, (end >> 1));
        }

        private int computeArm(char[] chars, int left, int right) {
            while (left >= 0 && right < chars.length) {
                if (chars[left] != chars[right]) break;
                left--;
                right++;
            }
            return (right - left >> 1) - 1;
        }
    }

    class DpSolution {
        /**
         * 区间 DP 问题
         *
         * @param s
         * @return
         * @see LongestPalindromicSubsequence.Solution#longestPalindromeSubseq(String)
         */
        public String longestPalindrome(String s) {
            int n = s.length();
            if (n <= 1) return s;
            boolean[][] dp = new boolean[n][n];
            char[] chs = s.toCharArray();
            int ansLen = -1, ansStart = -1, ansEnd = -1;
            for (int len = 1; len <= n; len++) {
                for (int start = 0; start + len <= n; start++) {
                    int end = start + len - 1;
                    if (len == 1)
                        dp[start][end] = true;
                    else {
                        boolean flag = chs[start] == chs[end];
                        if (len == 2)
                            dp[start][end] = flag;
                        else
                            dp[start][end] = flag && dp[start + 1][end - 1];
                    }
                    if (dp[start][end] && len > ansLen) {
                        ansLen = len;
                        ansStart = start;
                        ansEnd = end;
                    }
                }
            }
            return s.substring(ansStart, ansEnd + 1);
        }
    }

    class OptimisedDpSolution {
        /**
         * @param s
         * @return
         * @see LongestPalindromicSubsequence.OptimisedSolution#longestPalindromeSubseq(String)
         */
        public String longestPalindrome(String s) {
            int n = s.length();
            if (n <= 1) return s;
            boolean[] dp = new boolean[n];
            char[] chs = s.toCharArray();
            int ansLen = 1, ansStart = 0, ansEnd = 0;
            for (int i = n - 1; i >= 0; i--) {
                dp[i] = true; // dp[i][i]
                for (int j = n - 1; j > i; j--) {
                    if (chs[i] == chs[j]) {
                        if (j == i + 1)
                            // dp[i][i+1]
                            dp[j] = true;
                        else
                            // dp[i][j] = dp[i+1][j-1] if s[i] == s[j] else false
                            dp[j] = dp[j - 1];
                    } else {
                        dp[j] = false;
                    }
                    if (dp[j]) {
                        if (j - i + 1 > ansLen) {
                            ansLen = j - i + 1;
                            ansStart = i;
                            ansEnd = j;
                        }
                    }
                }
            }
            return s.substring(ansStart, ansEnd + 1);
        }
    }

}
