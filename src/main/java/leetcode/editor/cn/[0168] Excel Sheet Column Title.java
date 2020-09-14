package leetcode.editor.cn;

/**
 * <pre>
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 *
 * For example:
 *
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *     ...
 *
 * Example 1:
 *
 * Input: 1
 * Output: "A"
 *
 * Example 2:
 *
 * Input: 28
 * Output: "AB"
 *
 * Example 3:
 *
 * Input: 701
 * Output: "ZY"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-title
 * </pre>
 */
class ExcelSheetColumnTitle {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 相当于将其转化为 26 进制数
         *
         * @param n
         * @return
         */
        public String convertToTitle(int n) {
            StringBuilder sb = new StringBuilder();
            while (n > 0) {
                int mod = n % 26;
                if (mod == 0) {
                    sb.append('Z');
                    n = n / 26 - 1;
                } else {
                    sb.append((char) (mod - 1 + 'A'));
                    n = n / 26;
                }
            }
            return sb.reverse().toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
