package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/9/2020
 */
class MaximalRectangleTest {
    @Test
    void maximalRectangle() {
        MaximalRectangle.Solution solution = new MaximalRectangle().new Solution();
        char[][] matrix = new char[][]{
                new char[]{'1', '0', '1', '0', '0'},
                new char[]{'1', '0', '1', '1', '1'},
                new char[]{'1', '1', '1', '1', '1'},
                new char[]{'1', '0', '0', '1', '0'}
        };

        Assertions.assertEquals(6, solution.maximalRectangle(matrix));
    }
}