package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/26/2020
 */
class ImplementStrstrTest {
    @Test
    void strStr() {
        ImplementStrstr.Solution solution = new ImplementStrstr().new Solution();
        Assertions.assertEquals(2, solution.strStr("hello", "ll"));
        Assertions.assertEquals(-1, solution.strStr("aaaaa", "bba"));
    }
}