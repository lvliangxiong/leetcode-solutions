package leetcode.editor.cn;

/**
 * <pre>
 * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return
 * the shortest palindrome you can find by performing this transformation.
 *
 * Example 1:
 *
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 *
 * Example 2:
 *
 * Input: "abcd"
 * Output: "dcbabcd"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-palindrome
 * </pre>
 */
class ShortestPalindrome {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String shortestPalindrome(String s) {
            char[] chs = s.toCharArray();
            int high = s.length() - 1;
            if (high <= 0) return s;
            while (high > 0) {
                if (isPalindrome(chs, 0, high))
                    return new StringBuilder(s.substring(high + 1)).reverse().toString() + s;
                high--;
            }
            return new StringBuilder(s.substring(1)).reverse().toString() + s;
        }

        boolean isPalindrome(char[] chs, int low, int high) {
            while (low < high) {
                if (chs[low] != chs[high]) return false;
                low++;
                high--;
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class HashSolution {
        /**
         * Rabin-Karp 字符串哈希算法
         * <p>
         * 一般来说，我们选取一个大于字符集大小（即字符串中可能出现的字符种类的数目）的质数作为 base，
         * 再选取一个在字符串长度平方级别左右的质数作为 mod，产生哈希碰撞的概率就会很低。
         *
         * @param s
         * @return
         */
        public String shortestPalindrome(String s) {
            int n = s.length();
            int base = 131, mod = (int) (1E9 + 7);
            int left = 0, right = 0, mul = 1;
            int best = -1;
            for (int i = 0; i < n; ++i) {
                // 从左向右的字符串的 hash 值
                left = (int) (((long) left * base + s.charAt(i)) % mod);
                // 从右向左的字符串的 hash 值
                right = (int) ((right + (long) mul * s.charAt(i)) % mod);
                if (left == right) {
                    best = i;
                }
                mul = (int) ((long) mul * base % mod);
            }
            String add = (best == n - 1 ? "" : s.substring(best + 1));
            StringBuffer ans = new StringBuffer(add).reverse();
            ans.append(s);
            return ans.toString();
        }
    }

}
