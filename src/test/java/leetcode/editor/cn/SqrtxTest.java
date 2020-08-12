package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/8/2020
 */
class SqrtxTest {
    Sqrtx.Solution solution = new Sqrtx().new Solution();

    @Test
    void mySqrt() {
        Assertions.assertEquals(2, solution.mySqrt(8));
        Assertions.assertEquals(4, solution.mySqrt(17));
        Assertions.assertEquals(1, solution.mySqrt(2));
        Assertions.assertEquals(4, solution.mySqrt(16));
    }
}