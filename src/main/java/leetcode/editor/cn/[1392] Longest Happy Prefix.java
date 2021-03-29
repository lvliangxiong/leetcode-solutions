package leetcode.editor.cn;

import java.util.Arrays;

/**
 * <pre>
 * A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).
 *
 * Given a string s. Return the longest happy prefix of s .
 *
 * Return an empty string if no such prefix exists.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "level"
 * Output: "l"
 * Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"), and suffix ("l", "el", "vel", "evel").
 * The largest prefix which is also suffix is given by "l".
 *
 * Example 2:
 *
 * Input: s = "ababab"
 * Output: "abab"
 * Explanation: "abab" is the largest prefix which is also suffix. They can overlap in the original string.
 *  = "leetcodeleet"
 * Output: "leet"
 *
 * Example 4:
 *
 * Input: s = "a"
 * Output: ""
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 10^5
 *     s contains only lowercase English letters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-happy-prefix
 * </pre>
 */
class LongestHappyPrefix {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPrefix(String s) {
            int n = s.length(), ans = 0;
            char[] chs = s.toCharArray();
            // 暴力枚举每一种组合
            for (int i = n - 2; i >= 0; i--) {
                // [0, i] <=====> [n-1-i,n-1]
                int j = n - 1, k = i;
                while (i >= 0 && chs[i] == chs[j]) {
                    j--;
                    i--;
                }
                if (i == -1) {
                    // 成功找到最长的 happy prefix
                    int len = n - 1 - j;
                    if (len > ans) ans = len;
                    break;
                }
                i = k;
            }
            return s.substring(0, ans);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * Rabin-Karp 字符串编码，将『字符串』编码为整数，可以看作是一种 Hash 算法。
     */
    class HashSolution {
        public String longestPrefix(String s) {
            int n = s.length();
            char[] chs = s.toCharArray();
            int prefix = 0, suffix = 0;
            int base = 31, mod = 1000_000_007, mul = 1;
            int happy = 0;
            for (int i = 0; i < n; i++) {
                prefix = ((prefix * base) + chs[i] - 'a') % mod;
                suffix = (suffix + (chs[n - 1 - i] - 'a') * mul) % mod;
                if (prefix == suffix) {
                    happy = i + 1;
                }
                mul = mul * base % mod;
            }
            return s.substring(0, happy);
        }
    }

    class KMPSolution {
        /**
         * KMP 算法中，当字符串匹配失败时，会根据 dp 数组将模式串 pat 的状态进行更新。例如 txt 在匹配 pat 时，在第 i 个字符出现
         * 的不匹配的情况，那么 KMP 算法就会找到 pat 的前 i-1 个字符组成的子串的 longest happy prefix，重新比较 txt 的第 i 个
         * 字符和该 longest happy prefix 的下一个字符。
         *
         * @param s
         * @return
         * @see com.joey.learning.oj.algorithm.string.KMP#buildDP()
         */
        public String longestPrefix(String s) {
            int n = s.length();
            char[] chs = s.toCharArray();

            /* ends[i] 是字符串 s 的 [0, i] 子串的 longest happy prefix 末尾索引 */
            int[] ends = new int[n];
            Arrays.fill(ends, -1); // -1 代表不存在 longest happy prefix

            for (int i = 1; i < n; i++) {
                int j = ends[i - 1]; // [0, i-1] 的 longest happy prefix 末尾索引
                /* [0, j] 子串和 [(i-1)-j ,i-1] 子串相同，因此比较 chs[j+1] 和 chs[i]，
                 * 如果该位置的字符相同，则有 ends[i] = ends[i-1] + 1 = j + 1 */
                while (j != -1 && chs[j + 1] != chs[i]) {
                    j = ends[j];
                }
                if (chs[j + 1] == chs[i]) ends[i] = j + 1;
            }
            return s.substring(0, ends[n - 1] + 1);
        }
    }

}
