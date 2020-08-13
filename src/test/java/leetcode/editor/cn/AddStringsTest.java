package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/13/2020
 */
class AddStringsTest {
    @Test
    void addStrings() {
        AddStrings.Solution solution = new AddStrings().new Solution();
        Assertions.assertEquals("579", solution.addStrings("123", "456"));
        Assertions.assertEquals("912", solution.addStrings("456", "456"));
        Assertions.assertEquals("10212", solution.addStrings("756", "9456"));
    }
}