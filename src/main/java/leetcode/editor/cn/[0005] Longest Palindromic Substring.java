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
         *
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {
            int len = s.length(), max = 0, center = 0;
            if (len <= 1) return s;
            char[] chars = s.toCharArray();
            int count = (len << 1) + 1; // n 个字符的字符串，有 2n+1 个中心
            for (int i = 0; i < count; i++) {
                int length;
                if ((i & 1) == 0) {
                    // i 为偶数，扩展后的回文字符串长度为奇数
                    length = expand(chars, (i >> 1) - 1, (i >> 1) + 1);
                } else {
                    // i 为奇数，扩展后的回文字符串长度为偶数
                    length = expand(chars, i - 1 >> 1, i + 1 >> 1);
                }
                if (max < length) {
                    // 更新当前已查找到的最大子回文串的长度和中心位置
                    max = length;
                    center = i;
                }
            }
            if ((center & 1) == 0) {
                return s.substring((center >> 1) - (max - 1 >> 1), (center >> 1) + (max + 1 >> 1));
            } else {
                return s.substring((center + 1 >> 1) - (max >> 1), (center + 1 >> 1) + (max >> 1));
            }
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

            int center = -1; // 具有最右边界的回文子串的对称中心
            int right = -1; // 以 center 为中心的最长回文子串的右边界
            int[] arms = new int[len]; // 存储已计算过的『臂长』
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
            /*
             * dp[i][j] = dp[i+1][j-1] if s[i] == s[j] else false
             * */
            boolean[] dp = new boolean[n];
            char[] chs = s.toCharArray();
            int ansLen = 1, ansStart = 0, ansEnd = 0;
            for (int i = n - 1; i >= 0; i--) {
                dp[i] = true;
                for (int j = n - 1; j > i; j--) {
                    if (chs[i] == chs[j]) {
                        if (j == i + 1)
                            dp[j] = true;
                        else
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
