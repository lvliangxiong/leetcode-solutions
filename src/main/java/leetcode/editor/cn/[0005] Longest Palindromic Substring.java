package leetcode.editor.cn;

/**
 * <pre>
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * </pre>
 */
class LongestPalindromicSubstring {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            int len = s.length(), max = 0, center = 0;
            if (len <= 1) return s;
            char[] chars = s.toCharArray();
            int count = (len << 1) + 1;
            for (int i = 0; i < count; i++) {
                int length;
                if ((i & 1) == 0) {
                    length = expand(chars, (i >> 1) - 1, (i >> 1) + 1);
                } else {
                    length = expand(chars, i - 1 >> 1, i + 1 >> 1);
                }
                if (max < length) {
                    max = length;
                    center = i;
                }
            }
            if ((center & 1) == 0) {
                return s.substring((center >> 1) - (max - 1 >> 1), (center >> 1) + (max + 1 >> 1));
            } else {
                return s.substring((center + 1 >> 1) - (max >> 1), (center + 1 >> 1) + (max >> 1));
            }
        }

        private int expand(char[] chars, int left, int right) {
            while (left >= 0 && right < chars.length) {
                if (chars[left] != chars[right]) break;
                left--;
                right++;
            }
            return (right - left) - 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class ManacherSolution {
        public String longestPalindrome(String s) {
            if (s.equals("")) return s;
            // 为原字符串添加 '#' 简化后面的计算
            // 例如 "aabbcc" 会被转变为："#a#a#b#b#c#c#"
            int len = (s.length() << 1) + 1;
            char[] chars = new char[len];
            for (int i = 0; i < len; i++)
                chars[i] = (i & 1) == 0 ? '#' : s.charAt(i - 1 >> 1);

            int center = -1; // 对称中心
            int right = -1; // 以 center 为中心的最长回文子串的右边界
            int[] arms = new int[len]; // 存储已计算过的『臂长』
            int start = 0, end = -1; // 遍历过程中的最长子串

            for (int i = 1; i < len - 1; i++) { // 注意添加了 '#' 后，中心扩展的位置
                int arm;
                if (right > i) {
                    // 使用前面计算过的结果简化计算
                    int iSym = (center << 1) - i;
                    int minArm = Math.min(arms[iSym], right - i);
                    arm = computeArm(chars, i - minArm, i + minArm);
                } else {
                    arm = computeArm(chars, i - 1, i + 1);
                }
                arms[i] = arm;
                // 更新 center 和 right 的位置
                if (i + arm > right) {
                    center = i;
                    right = i + arm;
                }
                // 更新最后的结果
                if ((arm << 1) + 1 > end - start) {
                    start = i - arm;
                    end = i + arm;
                }
            }
            return s.substring(start >> 1, (end >> 1));
        }

        private int computeArm(char[] chars, int left, int right) {
            while (left >= 0 && right < chars.length) {
                if (chars[left] != chars[right]) break;
                left--;
                right++;
            }
            return (right - left >> 1) - 1;
        }
    }

}
