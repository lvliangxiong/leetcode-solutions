package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/22/2020
 */
class SlidingWindowMaximumTest {
    @Test
    void maxSlidingWindow() {
        SlidingWindowMaximum.Solution solution = new SlidingWindowMaximum().new Solution();
        Assertions.assertArrayEquals(new int[]{3, 3, 5, 5, 6, 7}, solution.maxSlidingWindow(
                new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }
}