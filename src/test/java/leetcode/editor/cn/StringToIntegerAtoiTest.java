package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 7/30/2020
 */
class StringToIntegerAtoiTest {
    @Test
    void myAtoi() {
        StringToIntegerAtoi.Solution solution = new StringToIntegerAtoi().new Solution();
        Assertions.assertEquals(532, solution.myAtoi("  532"));
        Assertions.assertEquals(532, solution.myAtoi("  532.99"));
        Assertions.assertEquals(532, solution.myAtoi("  0532"));
        Assertions.assertEquals(1234, solution.myAtoi("  1234w"));
        Assertions.assertEquals(Integer.MAX_VALUE, solution.myAtoi("  123413457334567w"));
    }
}