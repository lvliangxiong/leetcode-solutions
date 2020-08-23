package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/23/2020
 */
class LongestMountainInArrayTest {
    @Test
    void longestMountain() {
        LongestMountainInArray.Solution solution = new LongestMountainInArray().new Solution();
        Assertions.assertEquals(5, solution.longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
        Assertions.assertEquals(3, solution.longestMountain(new int[]{2, 2, 4, 3, 3, 2, 5, 5, 3}));
    }
}