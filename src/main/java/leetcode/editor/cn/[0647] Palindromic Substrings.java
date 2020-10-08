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
        /**
         * 1. 中心扩展法 O(n^2) {@link CenterExpansionSolution#countSubstrings(String)}
         * 2. 动态规划 dp[i][j] = dp[i+1][j-1] if s[i] == s[j] else false，算法复杂度同样是 O(n^2)
         * 3. Manacher 算法可以在线性时间内求解最长回文子串，也可以用来求解回文串的个数 O(n)
         * <p>
         * 这里给出 Manacher 算法的实现
         *
         * @param s
         * @return
         */
        public int countSubstrings(String s) {
            int count = 0;
            int len = (s.length() << 1) + 1;
            char[] chs = new char[len];
            // 添加占位符，例如 "aabb" 会被替换为 "#a#a#b#b#"，配合枚举的回文中心位置可以保证所有找到的回文串都是奇数长度的
            for (int i = 0; i < len; i++) {
                if ((i & 1) == 0) chs[i] = '#'; // 偶数索引位置为 #
                else chs[i] = s.charAt(i >> 1);
            }
            // 利用回文的对称性，可以加速后续回文串的计算
            int center = -1, right = -1; // 维护一个具有最右边界的已知回文串的中心位置和右边界
            int[] arms = new int[len]; // 各个回文中心位置的最长回文臂展
            for (int i = 1; i < len - 1; i++) { // 枚举回文中心位置
                int arm;
                if (right > i) {
                    // iSym 和 i 关于 center 对称，并且都在以 center 为回文中心的最长回文串内
                    int iSym = (center << 1) - i;
                    // 根据 iSym 和 center 的臂展信息可以加速 arm[i] 的计算（对称原理）
                    int minArm = Math.min(arms[iSym], right - i);
                    arm = computeArm(chs, i - minArm, i + minArm);
                } else {
                    arm = computeArm(chs, i - 1, i + 1);
                }
                arms[i] = arm;
                // 更新 center 和 right
                if (i + arm > right) {
                    right = i + arm;
                    center = i;
                }
                //          #，#a#，#b#b#，#a#a#a#，#b#a#a#b#，#a#b#a#b#a#
                // arm   =  0,  1,    2,      3,       4,           5 ...
                // count =  0,  1,    1,      2,       2,           3 ...
                count += arm + 1 >> 1; // 更新 count
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

    class CenterExpansionSolution {
        public int countSubstrings(String s) {
            int n = s.length(), ans = 0;
            for (int i = 0; i < 2 * n - 1; ++i) { // 枚举回文中心的位置
                int l = i / 2, r = i / 2 + i % 2;
                while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                    --l;
                    ++r;
                    ++ans;
                }
            }
            return ans;
        }
    }
}
