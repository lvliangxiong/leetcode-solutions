package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/19/2020
 */
class PartitionEqualSubsetSumTest {
    @Test
    void canPartition() {
        PartitionEqualSubsetSum.Solution solution = new PartitionEqualSubsetSum().new Solution();
        Assertions.assertTrue(solution.canPartition(new int[]{5, 1, 11, 5}));
    }
}