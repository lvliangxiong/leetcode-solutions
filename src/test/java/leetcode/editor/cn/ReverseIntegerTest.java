package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 7/30/2020
 */
class ReverseIntegerTest {
    @Test
    void reverse() {
        // Java 中的 % 是『取余运算』，求商向 0 取整
        Assertions.assertEquals(-4, -24 % 10);
        ReverseInteger.Solution solution = new ReverseInteger().new Solution();
        Assertions.assertEquals(solution.reverse(123), 321);
        Assertions.assertEquals(solution.reverse(-543), -345);
        Assertions.assertEquals(solution.reverse(1_111_111_999), 0);
        Assertions.assertEquals(solution.reverse(-1_111_111_999), 0);
    }
}