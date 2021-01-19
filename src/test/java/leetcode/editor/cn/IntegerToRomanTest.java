package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/25/2020
 */
class IntegerToRomanTest {
    @Test
    void intToRoman() {
        IntegerToRoman.Solution solution = new IntegerToRoman().new Solution();
        Assertions.assertEquals("LVIII", solution.intToRoman(58));
        Assertions.assertEquals("MCMXCIV", solution.intToRoman(1994));
    }
}