package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which
 * is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
 * Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same
 * principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 *     I can be placed before V (5) and X (10) to make 4 and 9.
 *     X can be placed before L (50) and C (100) to make 40 and 90.
 *     C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 *
 * Example 1:
 *
 * Input: 3
 * Output: "III"
 *
 * Example 2:
 *
 * Input: 4
 * Output: "IV"
 *
 * Example 3:
 *
 * Input: 9
 * Output: "IX"
 *
 * Example 4:
 *
 * Input: 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 *
 * Example 5:
 *
 * Input: 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-roman
 * </pre>
 */
class IntegerToRoman {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        Map<Integer, String> mapping = new HashMap<Integer, String>() {{
            put(1000, "M");
            put(900, "CM");
            put(500, "D");
            put(400, "CD");
            put(100, "C");
            put(90, "XC");
            put(50, "L");
            put(40, "XL");
            put(10, "X");
            put(9, "IX");
            put(5, "V");
            put(4, "IV");
            put(1, "I");
        }};

        public String intToRoman(int num) {
            int i = 1; // 代表位数，个位为 1，十位为 10，百位为 100 ...
            String ans = "";
            while (num > 0) { // 一位一位的处理
                int j = num % 10; // 取当前最低位
                int n = j * i; // 该位的实际大小
                if (mapping.containsKey(n)) {
                    ans = mapping.get(n) + ans;
                } else {
                    // j = 0 2 3 6 7 8 时，该位由 i 和 5i 拼凑出来
                    for (int k = 0; k < (j < 5 ? j : j - 5); k++) {
                        ans = mapping.get(i) + ans;
                    }
                    if (j > 5) {
                        ans = mapping.get(i * 5) + ans;
                    }
                }
                i *= 10;
                num /= 10;
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class GreedySolution {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        public String intToRoman(int num) {
            StringBuilder sb = new StringBuilder();
            // Loop through each symbol, stopping if num becomes 0.
            for (int i = 0; i < values.length && num > 0; i++) {
                // Repeat while the current symbol still fits into num.
                while (values[i] <= num) {
                    num -= values[i];
                    sb.append(symbols[i]);
                }
            }
            return sb.toString();
        }
    }

}
