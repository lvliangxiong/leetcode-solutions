package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/20/2020
 */
class BestTimeToBuyAndSellStockIiiTest {
    @Test
    void maxProfit() {
        BestTimeToBuyAndSellStockIii.DPSolution solution = new BestTimeToBuyAndSellStockIii().new DPSolution();
        Assertions.assertEquals(6, solution.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }
}