package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/11/2020
 */
class KthLargestElementInAStreamTest {
    @Test
    void KthLargest() {
        KthLargestElementInAStream.KthLargest solution = new KthLargestElementInAStream().new KthLargest(2, new int[]{0});
        Assertions.assertEquals(-1, solution.add(-1));
        Assertions.assertEquals(0, solution.add(1));
        Assertions.assertEquals(0, solution.add(-2));
        Assertions.assertEquals(0, solution.add(-4));
        Assertions.assertEquals(1, solution.add(3));
    }
}