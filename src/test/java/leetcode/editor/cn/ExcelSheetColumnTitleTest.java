package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 9/14/2020
 */
class ExcelSheetColumnTitleTest {
    @Test
    void convertToTitle() {
        ExcelSheetColumnTitle.Solution solution = new ExcelSheetColumnTitle().new Solution();
        Assertions.assertEquals("Z", solution.convertToTitle(26));
        Assertions.assertEquals("ZY", solution.convertToTitle(701));
        Assertions.assertEquals("ZZ", solution.convertToTitle(702));
        Assertions.assertEquals("XBE", solution.convertToTitle(16281));
    }
}