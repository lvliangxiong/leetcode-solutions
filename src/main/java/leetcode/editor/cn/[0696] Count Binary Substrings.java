package leetcode.editor.cn;

/**
 * <pre>
 * Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's,
 * and all the 0's and all the 1's in these substrings are grouped consecutively.
 *
 * Substrings that occur multiple times are counted the number of times they occur.
 *
 * Example 1:
 *
 * Input: "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10",
 * "0011", and "01".
 *
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 *
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 *
 * Example 2:
 *
 * Input: "10101"
 * Output: 4
 * Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
 *
 * Note:
 * s.length will be between 1 and 50,000.
 * s will only consist of "0" or "1" characters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-binary-substrings
 * </pre>
 */
class CountBinarySubstrings {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 1. 频繁调用字符串的 charAt 方法，没有先将字符串转为字符数组后，使用索引进行获取快。
         * 2. 频繁使用字符串的 length 方法，不如将其值先获取，然后直接使用局部变量。
         *
         * @param s
         * @return
         */
        public int countBinarySubstrings(String s) {
            int last = 0, current = 0, index = 0, ans = 0;
            char[] chs = s.toCharArray();
            int n = s.length();
            while (index < n) {
                char ch = chs[index];
                current = 0;
                while (index < n && chs[index] == ch) {
                    current++;
                    index++;
                }
                ans += Math.min(last, current);
                last = current;
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
