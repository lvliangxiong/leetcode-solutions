package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/20/2020
 */
class BestTimeToBuyAndSellStockIvTest {
    @Test
    void maxProfit() {
        BestTimeToBuyAndSellStockIv.Solution solution = new BestTimeToBuyAndSellStockIv().new Solution();
        Assertions.assertEquals(2, solution.maxProfit(2, new int[]{2, 4, 1}));
    }
}