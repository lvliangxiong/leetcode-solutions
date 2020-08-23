package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/23/2020
 */
class SubarrayProductLessThanKTest {
    @Test
    void numSubarrayProductLessThanK() {
        SubarrayProductLessThanK.Solution solution = new SubarrayProductLessThanK().new Solution();
        Assertions.assertEquals(8, solution.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }
}