package leetcode.editor.cn;

/**
 * <pre>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
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
    class Solution {
        public String convert(String s, int numRows) {
            if (s == null) return null;
            if (s.length() == 0) return "";
            if (numRows == 1) return s;

            StringBuilder[] sbs = new StringBuilder[numRows];
            Arrays.setAll(sbs, index -> new StringBuilder());
            boolean down = false;
            int index = 0;
            for (char ch : s.toCharArray()) {
                sbs[index].append(ch);
                if (down && index == numRows - 1) {
                    down = false;
                }
                if (!down && index == 0) {
                    down = true;
                }
                if (down) {
                    index++;
                } else {
                    index--;
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
