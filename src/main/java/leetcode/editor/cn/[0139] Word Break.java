package leetcode.editor.cn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can
 * be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 *     The same word in the dictionary may be reused multiple times in the segmentation.
 *     You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * </pre>
 */
class WordBreak {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * dp[i] 表示给定字符串的前 i 个字符组成的子串是否可以由字典中的字符串组成。
         *
         * @param s
         * @param wordDict
         * @return
         */
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> dict = new HashSet<>();
            int max = 0;
            for (String word : wordDict) {
                if (word.length() > max) max = word.length();
                dict.add(word);
            }
            int len = s.length();
            boolean[] dp = new boolean[len + 1];
            dp[0] = true;
            for (int i = 1; i <= len; i++) {
                for (int j = i - 1; j >= 0 && (i - j) <= max; j--) {
                    // 分割为两部分，一部分为 [0, j-1]，另一部分为 [j, i-1]
                    if (dp[j] && dict.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[len];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
