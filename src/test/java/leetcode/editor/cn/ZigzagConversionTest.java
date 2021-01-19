package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 7/30/2020
 */
class ZigzagConversionTest {
    @Test
    void convert() {
        Assertions.assertEquals("PINALSIGYAHRPI",
                new ZigzagConversion().new Solution().convert("PAYPALISHIRING", 4));
    }
}