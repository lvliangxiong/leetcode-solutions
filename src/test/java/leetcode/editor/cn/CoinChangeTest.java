package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/14/2020
 */
class CoinChangeTest {
    @Test
    void coinChange() {
        CoinChange.DpSolution solution = new CoinChange().new DpSolution();
        Assertions.assertEquals(3, solution.coinChange(new int[]{1, 2, 5}, 11));
        Assertions.assertEquals(20, solution.coinChange(new int[]{186, 419, 83, 408}, 6249));
    }
}