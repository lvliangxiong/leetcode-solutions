package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all
 * the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 *
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Example 3:
 *
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 *
 *
 * Constraints:
 *
 *     1 <= strs.length <= 10^4
 *     0 <= strs[i].length <= 100
 *     strs[i] consists of lower-case English letters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * </pre>
 */
class GroupAnagrams {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                String key = helper(str);
                List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
                list.add(str);
            }
            return new ArrayList<>(map.values());
        }

        /**
         * 将 str 按照字典序进行排序
         *
         * @param str
         * @return
         */
        private String helper(String str) {
            int[] count = new int[26];
            for (char ch : str.toCharArray()) {
                count[ch - 'a']++;
            }
            /* 本质是对字符串进行某种编码，使得仅字符排列不同的字符串编码之后的字符串或者数字相等，如下给出了两种思路：
             * 1. 排序
             * 2. 构造一个新的字符串，该字符串由各种字符的数量构成和分割符构成 */
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                /*for (int j = 0; j < count[i]; j++) {
                    sb.append((char) ('a' + i));
                }*/
                sb.append(count[i]).append("#");
            }
            return sb.toString();
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
