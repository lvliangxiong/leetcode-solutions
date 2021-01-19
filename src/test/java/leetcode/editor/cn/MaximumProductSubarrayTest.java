package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/17/2020
 */
class MaximumProductSubarrayTest {
    @Test
    void maxProduct() {
        MaximumProductSubarray.Solution solution = new MaximumProductSubarray().new Solution();
        Assertions.assertEquals(108, solution.maxProduct(new int[]{-1, -2, -9, -6}));
    }
}