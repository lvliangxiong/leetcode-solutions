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


/**
 * Knuth-Morris-Pratt Algorithm.
 * <p>
 * The core of KMP search is never back the pointer on txt when failed. It only change the state on pat to
 * the most appropriate state, and this is implemented by an extra array.
 */
class KMP {
    /**
     * extra array record the state transition for every step in matching.
     * <p>
     * dp[state][ch] indicates the next state of matching when the current state encounters char ch.
     */
    private int[][] dp;
    private String pat;

    public KMP(String pat) {
        this.pat = pat;
        buildDP();
    }

    /**
     * build DP array, which has the information where the pattern pointer should be replaced when failed.
     */
    public void buildDP() {
        int M = pat.length();
        dp = new int[M][256];
        dp[0][pat.charAt(0)] = 1; // base case
        int x = 0;
        for (int state = 1; state < M; state++) {
            for (int ch = 0; ch < 256; ch++) {
                if (pat.charAt(state) == ch) {
                    dp[state][ch] = state + 1;
                } else {
                    dp[state][ch] = dp[x][ch];
                }
            }
            // search for longest repeat prefix
            x = dp[x][pat.charAt(state)];
        }
    }

    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        int state = 0; // current state, state == M means matching succeeds.
        for (int i = 0; i < N; i++) {
            state = dp[state][txt.charAt(i)]; // using dp to compute the next state
            if (state == M) return i - M + 1;
        }
        return -1; // matching fails.
    }
}
