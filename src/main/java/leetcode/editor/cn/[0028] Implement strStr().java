package leetcode.editor.cn;

/**
 * <pre>
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 *
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 *
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr()
 * and Java's indexOf().
 *
 *
 *
 * Constraints:
 *
 *     haystack and needle consist only of lowercase English characters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * </pre>
 */
class ImplementStrstr {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 值得注意的是，KMP 算法虽然是 O(N) 的时间复杂度，但是由于需要对『模式串』构建 failed 数组，属于典型的空间换时间的做法。
     * 另外由于 KMP 适用于字符串中包含有大量的重复片段的情况，但是在实际具有语义的句子中，这个算法的表现可能也只是一般。
     */
    class Solution {
        /**
         * KMP 字符串搜索算法：永不后退『主串』上的指针，只后退『模式串』上的指针。
         *
         * @param haystack
         * @param needle
         * @return
         */
        public int strStr(String haystack, String needle) {
            if (needle.equals("")) return 0;
            int n = haystack.length(), m = needle.length();
            char[] h = haystack.toCharArray(), ne = needle.toCharArray();

            int[] failed = getFailed(ne); // 匹配失败后的『模式串指针』回退位置
            int i = 0; // 『主串』指针
            int j = 0; // 『模式串』指针
            while (i < n) {
                if (j == -1 || h[i] == ne[j]) {
                    j++;
                    i++;
                    if (j == m) return i - j;
                } else j = failed[j];
            }
            return -1;
        }

        /**
         * KMP 算法的 failed 数组
         *
         * @param needle
         * @return
         * @see LongestHappyPrefix.Solution#longestPrefix(String)
         */
        private int[] getFailed(char[] needle) {
            int n = needle.length;
            int[] failed = new int[n];

            /* -1 代表需要重新匹配，即『主串』跳过当前字符，『模式串』归零后重新匹配；
             * 0 代表将当前『主串』的不匹配字符和『模式串』的起始位置进行重新匹配，如果还是不匹配，将进入到 -1 状态。
             * 注意这两者的区别。*/
            failed[0] = -1;
            int i = 0;
            int k = -1;
            // [0, k) 子串是 [0, i) 子串的最长 happy prefix
            while (i < n - 1) {
                // 根据当前位置上 failed 数组的值计算下一个位置的匹配失败后的回退位置
                if (k == -1 || needle[i] == needle[k]) {
                    i++;
                    k++;
                    if (needle[i] == needle[k]) {
                        failed[i] = failed[k]; // 这里进行了一个加速
                    } else {
                        failed[i] = k;
                    }
                } else {
                    k = failed[k];
                }
            }
            return failed;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * https://leetcode-cn.com/problems/implement-strstr/solution/shi-xian-strstr-by-leetcode/
     */
    class RabinKarpSolution {
        public int charToInt(int idx, String s) {
            return s.charAt(idx) - 'a';
        }

        public int strStr(String haystack, String needle) {
            int L = needle.length(), n = haystack.length();
            if (L > n) return -1;


            int a = 26; // base value for the rolling hash function
            long modulus = (long) Math.pow(2, 31); // modulus value for the rolling hash function to avoid overflow


            long h = 0, hRef = 0;
            for (int i = 0; i < L; ++i) {
                h = (h * a + charToInt(i, haystack)) % modulus;
                hRef = (hRef * a + charToInt(i, needle)) % modulus;
            }
            if (h == hRef) return 0;

            // const value to be used often : a^L % modulus
            long aL = 1;
            for (int i = 1; i <= L; ++i)
                aL = (aL * a) % modulus;

            for (int start = 1; start < n - L + 1; ++start) {
                // compute rolling hash in O(1) time
                h = (h * a - charToInt(start - 1, haystack) * aL
                        + charToInt(start + L - 1, haystack)) % modulus;
                if (h == hRef) return start;
            }
            return -1;
        }
    }

}
