package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/6/2020
 */
class ValidParenthesesTest {
    @Test
    void isValid() {
        ValidParentheses.Solution solution = new ValidParentheses().new Solution();
        Assertions.assertEquals(true, solution.isValid("{}[]{}({[][]([])})"));
    }
}