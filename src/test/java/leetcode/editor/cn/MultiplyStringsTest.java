package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/13/2020
 */
class MultiplyStringsTest {
    @Test
    void multiply() {
        MultiplyStrings.Solution solution = new MultiplyStrings().new Solution();
        Assertions.assertEquals("6", solution.multiply("2", "3"));
        Assertions.assertEquals("56088", solution.multiply("123", "456"));
    }
}