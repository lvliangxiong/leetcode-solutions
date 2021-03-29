package leetcode.editor.cn;

/**
 * <pre>
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies
 * of the substring together. You may assume the given string consists of lowercase English letters only and its length
 * will not exceed 10000.
 *
 *
 *
 * Example 1:
 *
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 *
 * Example 2:
 *
 * Input: "aba"
 * Output: False
 *
 * Example 3:
 *
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern
 * </pre>
 */
class RepeatedSubstringPattern {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean repeatedSubstringPattern(String s) {
            int n = s.length();
            char[] chs = s.toCharArray();
            for (int segLen = 1; segLen <= (n >> 1); segLen++) { // 遍历 repeat substring 的长度
                if (n % segLen == 0) { // 整除时，才有可能是正确的 substring
                    // 判断是否为循环节
                    boolean flag = true;
                    for (int i = 0; i < segLen; i++) {
                        for (int j = i; j < n; j += segLen) {
                            if (chs[j] != chs[i]) {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag) return true;
                }
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}