package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SortAnArrayTest {
    @Test
    void heapSort() {
        SortAnArray.HeapSortSolution solution = new SortAnArray().new HeapSortSolution();
        int[] nums = {-4, 0, 7, 4, 9, -5, -1, 0, -7, -1};
        solution.sortArray(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            Assertions.assertTrue(nums[i] <= nums[i + 1]);
        }
    }
}