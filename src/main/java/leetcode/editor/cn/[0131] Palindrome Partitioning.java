package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * </pre>
 */
class PalindromePartitioning {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        String str;
        int n;
        boolean[][] isPalindrome;
        List<List<String>> ans = new ArrayList<>();
        List<String> partitioned = new ArrayList<>();

        /**
         * DFS + backtrack
         *
         * @param s
         * @return
         */
        public List<List<String>> partition(String s) {
            if (!s.equals("")) {
                n = s.length();
                str = s;
                isPalindrome = new boolean[n][n];
                manacher(s);
                backtrack(0);
            }
            return ans;
        }

        private void backtrack(int start) {
            if (start == n) {
                ans.add(new ArrayList<>(partitioned));
            }
            for (int end = start; end < n; end++) {
                if (isPalindrome[start][end]) {
                    partitioned.add(str.substring(start, end + 1));
                    backtrack(end + 1);
                    partitioned.remove(partitioned.size() - 1);
                }
            }
        }

        /**
         * 使用 Manacher 算法计算所有的回文子串，记录在 isPalindrome 数组中
         *
         * @param s
         * @see PalindromicSubstrings.Solution
         */
        public void manacher(String s) {
            // 添加占位符，例如 "aabb" 会被替换为 "#a#a#b#b#"
            int len = (s.length() << 1) + 1;
            char[] chars = new char[len];
            for (int i = 0; i < len; i++) {
                if ((i & 1) == 0) chars[i] = '#';
                else chars[i] = s.charAt(i >> 1);
            }

            int center = -1, right = -1;
            int[] arms = new int[len];

            for (int i = 1; i < len - 1; i++) {
                int arm;
                if (i < right) {
                    int iSym = 2 * center - i;
                    int minArm = Math.min(arms[iSym], right - i);
                    arm = computeArm(chars, i - minArm, i + minArm);
                } else {
                    arm = computeArm(chars, i - 1, i + 1);
                }
                arms[i] = arm;
                if (i + arm > right) {
                    right = i + arm;
                    center = i;
                }
                // update isPalindrome
                int low = i - arm >> 1, high = (i + arm >> 1) - 1;
                while (low <= high) {
                    isPalindrome[low++][high--] = true;
                }
            }
        }

        private int computeArm(char[] chars, int left, int right) {
            while (left >= 0 && right < chars.length) {
                if (chars[left] != chars[right]) break;
                left--;
                right++;
            }
            return (right - left - 1) >> 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
