package leetcode.editor.cn;

/**
 * <pre>
 * Given a string, your task is to count how many palindromic substrings in this string.
 *
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist
 * of same characters.
 *
 * Example 1:
 *
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 *
 *
 * Example 2:
 *
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 *
 *
 * Note:
 *
 *     The input string length won't exceed 1000.
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * </pre>
 */
class PalindromicSubstrings {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countSubstrings(String s) {
            int count = 0;
            int len = (s.length() << 1) + 1;
            char[] chs = new char[len];
            for (int i = 0; i < len; i++) {
                if ((i & 1) == 0) chs[i] = '#';
                else chs[i] = s.charAt(i >> 1);
            }

            int center = -1, right = -1;
            int[] arms = new int[len];
            for (int i = 1; i < len - 1; i++) {
                int arm;
                if (right > i) {
                    int iSym = (center << 1) - i;
                    int minArm = Math.min(arms[iSym], right - i);
                    arm = computeArm(chs, i - minArm, i + minArm);
                } else {
                    arm = computeArm(chs, i - 1, i + 1);
                }
                arms[i] = arm;
                if (i + arm > right) {
                    right = i + arm;
                    center = i;
                }
                //         #a#，#b#b#，#a#a#a#，#b#a#a#b#，#a#b#a#b#a#
                // arm   =  1,    2,      3,       4,           5 ...
                // count =  1,    1,      2,       2,           3 ...
                count += arm + 1 >> 1;
            }
            return count;
        }

        private int computeArm(char[] chs, int low, int high) {
            while (low >= 0 && high < chs.length) {
                if (chs[low] != chs[high]) break;
                low--;
                high++;
            }
            return high - low - 1 >> 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
