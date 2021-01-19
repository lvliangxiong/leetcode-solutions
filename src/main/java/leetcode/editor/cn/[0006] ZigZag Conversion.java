package leetcode.editor.cn;

/**
 * <pre>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to
 * display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 *
 * </pre>
 */

import java.util.Arrays;

class ZigzagConversion {
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 模拟法
     */
    class Solution {
        public String convert(String s, int numRows) {
            if (s == null) return null;
            if (s.length() == 0) return "";
            if (numRows == 1) return s;

            StringBuilder[] sbs = new StringBuilder[numRows];
            Arrays.setAll(sbs, index -> new StringBuilder());
            boolean up = false, down = true; // 其实可以使用一个变量，这里使用两个是为了更好的可读性
            int index = 0;
            for (char ch : s.toCharArray()) {
                sbs[index].append(ch);
                if (down && index == numRows - 1) {
                    up = true;
                    down = false;
                }
                if (up && index == 0) {
                    up = false;
                    down = true;
                }
                if (up) {
                    index--;
                } else if (down){
                    index++;
                }
            }
            StringBuilder ret = new StringBuilder();
            for (StringBuilder sb : sbs) {
                ret.append(sb);
            }
            return ret.toString();

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
