package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 7/31/2020
 */
class UniqueBinarySearchTreesTest {
    UniqueBinarySearchTrees.Solution solution = new UniqueBinarySearchTrees().new Solution();

    @Test
    void numTrees() {
        Assertions.assertEquals(132, solution.numTrees(6));
        Assertions.assertEquals(16796, solution.numTrees(10));
        Assertions.assertEquals(58786, solution.numTrees(11));
    }

    @Test
    void numTreesIterative() {
        Assertions.assertEquals(132, solution.numTreesIterative(6));
        Assertions.assertEquals(16796, solution.numTreesIterative(10));
        Assertions.assertEquals(58786, solution.numTreesIterative(11));
    }
}