package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidParenthesisStringTest {
    @Test
    void checkValidString() {
        ValidParenthesisString.Solution solution = new ValidParenthesisString().new Solution();
        Assertions.assertTrue(solution.checkValidString("(*))"));
    }
}