package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContainerWithMostWaterTest {
    @Test
    void maxArea() {
        ContainerWithMostWater.TwoPointerSolution solution = new ContainerWithMostWater().new TwoPointerSolution();
        Assertions.assertEquals(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}), 49);
    }
}