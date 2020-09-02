package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringToNumberTest {
    @Test
    void isNumber() {
        StringToNumber.NormalSolution solution = new StringToNumber().new NormalSolution();
        String[] nums = {"+100", "5e2", "-123", "3.1416", "-1E-16", "0123", "1. ", " .5 ", "00220022"};
        String[] nans = {"12e", "1a3.14", "1.2.3", "+-5", "12e+5.4", "."};
        for (String num : nums) {
            Assertions.assertTrue(solution.isNumber(num));
        }
        for (String nan : nans) {
            Assertions.assertFalse(solution.isNumber(nan));
        }
    }
}