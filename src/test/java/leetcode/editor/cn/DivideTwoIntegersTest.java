package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/14/2020
 */
class DivideTwoIntegersTest {
    @Test
    void divide() {
        DivideTwoIntegers.Solution solution = new DivideTwoIntegers().new Solution();
        Assertions.assertEquals(-2, solution.divide(7, -3));
        Assertions.assertEquals(9, solution.divide(18, 2));
        Assertions.assertEquals(-1, solution.divide(-1, 1));
        Assertions.assertEquals(Integer.MAX_VALUE, solution.divide(Integer.MIN_VALUE, -1));
        Assertions.assertEquals(Integer.MIN_VALUE, solution.divide(Integer.MIN_VALUE, 1));
        Assertions.assertEquals(-1073741824, solution.divide(Integer.MIN_VALUE, 2));
    }
}