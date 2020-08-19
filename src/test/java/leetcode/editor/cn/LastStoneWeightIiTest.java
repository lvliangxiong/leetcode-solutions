package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/19/2020
 */
class LastStoneWeightIiTest {
    @Test
    void lastStoneWeightII() {
        LastStoneWeightIi.Solution solution = new LastStoneWeightIi().new Solution();
        // Assertions.assertEquals(1, solution.lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1}));
        Assertions.assertEquals(4, solution.lastStoneWeightII(new int[]{57, 32, 40, 27, 35, 61}));
    }
}