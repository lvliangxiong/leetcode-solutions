package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than
 * 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Example 2:
 *
 * Input:
 * s: "abab" p: "ab"
 *
 * Output:
 * [0, 1, 2]
 *
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * </pre>
 */
class FindAllAnagramsInAString {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            int[] counts = new int[26];
            for (char ch : p.toCharArray()) {
                counts[ch - 'a']++;
            }
            int len = p.length(), n = s.length();
            List<Integer> ans = new ArrayList<>();
            if (n < len) return ans;
            char[] chs = s.toCharArray();
            for (int i = 0; i < len; i++) {
                counts[chs[i] - 'a']--;
            }
            int count = 0;
            for (int i = 0; i < 26; i++) {
                if (counts[i] == 0) count++;
            }
            if (count == 26) ans.add(0);
            for (int i = 1; i <= n - len; i++) {
                int add = chs[i + len - 1] - 'a';
                int remove = chs[i - 1] - 'a';
                if (add == remove) {
                    if (count == 26) ans.add(i);
                } else {
                    counts[add]--;
                    counts[remove]++;
                    if (counts[add] == 0) count++;
                    else if (counts[add] == -1) count--;
                    if (counts[remove] == 0) count++;
                    else if (counts[remove] == 1) count--;
                    if (count == 26) ans.add(i);
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
