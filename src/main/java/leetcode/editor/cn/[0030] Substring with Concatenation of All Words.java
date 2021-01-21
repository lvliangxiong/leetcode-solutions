package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * You are given a string s and an array of strings words of the same length. Return all starting indices of
 * substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any
 * intervening characters.
 *
 * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 *
 * Example 2:
 *
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * Output: []
 *
 * Example 3:
 *
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * Output: [6,9,12]
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 10^4
 *     s consists of lower-case English letters.
 *     1 <= words.length <= 5000
 *     1 <= words[i].length <= 30
 *     words[i] consists of lower-case English letters.
 * </pre>
 */
class SubstringWithConcatenationOfAllWords {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int len = 0; // words 数组中字符串的长度
        int n = 0; // words 数组的长度
        Map<String, Integer> counts = new HashMap<>(); // 存储 words 中各字符串出现的次数

        public List<Integer> findSubstring(String s, String[] words) {
            len = words[0].length();
            n = words.length;
            for (int i = 0; i < words.length; i++) {
                counts.put(words[i], counts.getOrDefault(words[i], 0) + 1);
            }

            List<Integer> ans = new ArrayList<>();
            int subLen = n * len; // 用于和 words 进行比较的子串的长度
            for (int i = 0; i < s.length(); i++) {
                if (s.length() - i < subLen) break; // 以索引 i 为起始位置的子串长度如果小于 subLen，后续就不用再判断了
                if (isPermutation(s, i)) ans.add(i);
            }
            return ans;
        }

        private boolean isPermutation(String str, int left) {
            Map<String, Integer> seen = new HashMap<>(counts.size() << 1);
            for (int i = 0; i < n; i++) {
                // 将待判断的 str 分成 n 等分，分别与 words 中的元素进行判断
                int start = left + i * len;
                String s = str.substring(start, start + len);
                Integer count = counts.get(s); // 字符串 s 应该出现的次数
                if (count == null || count == seen.getOrDefault(s, 0)) {
                    return false;
                }
                seen.put(s, seen.getOrDefault(s, 0) + 1);
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DPSolution {
        public List<Integer> findSubstring(String s, String[] words) {
            ArrayList<Integer> result = new ArrayList<>();
            HashMap<String, Integer> wordMap = new HashMap<>(); // 存储字符串出现的次数
            for (String word : words) {
                int v = wordMap.getOrDefault(word, 0);
                wordMap.put(word, v + 1);
            }
            int wordLen = words[0].length();
            int len = words.length;
            int totalLen = wordLen * len;

            for (int i = 0; i < wordLen; i++) {
                int left = i;
                while (left <= s.length() - totalLen) {
                    // 存储 [left, right] 子串按照 wordLen 长度分割后的字符串数组的计数值
                    HashMap<String, Integer> newMap = new HashMap<>();
                    boolean flag = true;
                    int right = left + totalLen;
                    // 判断 [left, right) 子串是否为 words 的一个排列
                    while (right > left) {
                        String w = s.substring(right - wordLen, right); // 长度为 wordLen 的子串
                        int newValue = newMap.getOrDefault(w, 0) + 1;
                        int wordCount = wordMap.getOrDefault(w, 0);
                        if (newValue > wordCount) {
                            left = right; // 快速迭代 left，反向进行判断也是为了迭代更快
                            flag = false;
                            break;
                        }
                        right -= wordLen;
                        newMap.put(w, newValue);
                    }
                    if (flag) {
                        // 这里应该还可以使用类似与滚动 hash 和滑动窗口的思想进行下一个子串的快速判断
                        result.add(left);
                        left += wordLen;
                    }
                }
            }
            return result;
        }
    }

}