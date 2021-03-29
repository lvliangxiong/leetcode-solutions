package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContinuousSubarraySumTest {
    @Test
    void checkSubarraySum() {
        ContinuousSubarraySum.Solution solution = new ContinuousSubarraySum().new Solution();
        Assertions.assertTrue(solution.checkSubarraySum(new int[]{0, 0}, 0));
    }
}