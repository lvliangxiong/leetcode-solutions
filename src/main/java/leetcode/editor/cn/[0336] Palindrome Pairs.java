package leetcode.editor.cn;

import java.util.*;

/**
 * <pre>
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that
 * the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1:
 *
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 *
 * Example 2:
 *
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-pairs
 * </pre>
 */
class PalindromePairs {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 1. 枚举字符串对，并进行判断。其复杂度为 n(n-1)*(2m)。
         * 2. 枚举字符串，对每个字符串，枚举可以与之成为『回文字符串对』的字符串，并在给定字符串数组中进行查找。其复杂度为 nm^2。
         * <p>
         * n 为给定字符串数组的大小，m 为字符串数组中字符串的平均长度。
         *
         * @param words
         * @return
         */
        public List<List<Integer>> palindromePairs(String[] words) {
            int n = words.length;
            Map<String, Integer> wordsReversedMapping = new HashMap<>(); // 存储给定字符串数组所有字符串的『反转字符串』
            for (int i = 0; i < n; i++) {
                wordsReversedMapping.put(new StringBuilder(words[i]).reverse().toString(), i);
            }
            List<List<Integer>> ret = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String word = words[i];
                int m = word.length();
                if (m == 0) continue;
                for (int j = 0; j <= m; j++) {
                    // 将 word 拆分为两子串 t1 和 t2：[0, j-1] 和 [j, m-1]
                    if (isPalindrome(word, j, m - 1)) {
                        // t2 为回文字符串，则去给定字符串数组中查找 t1 的反转字符串，即去 wordsReversedMapping 中查找是否包含 t1
                        int match = wordsReversedMapping.getOrDefault(word.substring(0, j), -1);
                        if (match != -1 && match != i) {
                            ret.add(Arrays.asList(new Integer[]{i, match}));
                        }
                    }
                    if (j != 0 && isPalindrome(word, 0, j - 1)) {
                        // t1 是回文字符串，则去给定字符串数组中查找 t2 的反转字符串，即去 wordsReversedMapping 中查找是否包含 t2
                        int match = wordsReversedMapping.getOrDefault(word.substring(j, m), -1);
                        if (match != -1 && match != i) {
                            ret.add(Arrays.asList(new Integer[]{match, i}));
                        }
                    }
                }
            }
            return ret;
        }

        /**
         * 判断 word 在 [left, right] 之间的子串是否为回文字符串
         *
         * @param word
         * @param left
         * @param right
         * @return
         */
        private boolean isPalindrome(String word, int left, int right) {
            if (left >= right) return true;
            if (word.charAt(left) == word.charAt(right)) {
                return isPalindrome(word, left + 1, right - 1);
            } else {
                return false;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
