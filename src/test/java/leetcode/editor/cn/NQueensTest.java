package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/6/2020
 */
class NQueensTest {
    @Test
    void solveNQueues() {
        NQueens.Solution solution = new NQueens().new Solution();
        Assertions.assertEquals(2, solution.solveNQueens(4).size());
        Assertions.assertEquals(10, solution.solveNQueens(5).size());
    }
}