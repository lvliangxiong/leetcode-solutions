package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TargetSumTest {
    @Test
    void findTargetSumWays() {
        TargetSum.Solution solution = new TargetSum().new Solution();
        Assertions.assertEquals(5, solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        Assertions.assertEquals(256, solution.findTargetSumWays(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, 1));
    }
}