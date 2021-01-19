package leetcode.editor.cn;

import java.util.*;

/**
 * <pre>
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to
 * construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 *
 * Note:
 *
 *     The same word in the dictionary may be reused multiple times in the segmentation.
 *     You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 *
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * Example 2:
 *
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 *
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 * </pre>
 */
class WordBreakIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> wordBreak(String s, List<String> wordDict) {
            int len = s.length();
            boolean[] dp = new boolean[len + 1];
            dp[0] = true;

            int max = Integer.MIN_VALUE;
            Set<String> set = new HashSet<>();
            for (String str : wordDict) {
                set.add(str);
                if (max < str.length())
                    max = str.length();
            }
            // 使用动态规划，判断该字符串是否能被 break，并且获得的结果可以用于后续的快速判断
            for (int i = 1; i <= len; i++) {
                for (int j = i; j >= 0 && i - j <= max; j--) {
                    if (dp[j] && set.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            List<String> result = new ArrayList<>();
            if (dp[len]) {
                backtrack(s, len, set, result, new LinkedList<>(), dp);
            }

            return result;
        }

        public void backtrack(String s, int end, Set<String> wordSet, List<String> result, LinkedList<String> queue,
                              boolean[] dp) {
            if (end == 0) {
                StringBuilder sb = new StringBuilder();
                for (String str : queue) {
                    sb.append(str).append(" ");
                }
                sb.setLength(sb.length() - 1);
                result.add(sb.toString());
                return;
            }

            for (int i = 0; i < end; i++) {
                // 判断前缀 [0, i-1] 子串
                if (dp[i]) {
                    String suffix = s.substring(i, end);
                    // 判断后缀 [i, len-1] 子串，只有后缀和前缀均能被 break，才能保证不会出错
                    if (wordSet.contains(suffix)) {
                        queue.addFirst(suffix);
                        backtrack(s, i, wordSet, result, queue, dp);
                        queue.removeFirst();
                    }
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
