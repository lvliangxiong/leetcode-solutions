package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/2/2020
 */
class InterleavingStringTest {
    @Test
    void dp() {
        InterleavingString.DpSolution solution = new InterleavingString().new DpSolution();
        Assertions.assertEquals(true, solution.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        Assertions.assertEquals(true, solution.isInterleave("", "a", "a"));
    }
}