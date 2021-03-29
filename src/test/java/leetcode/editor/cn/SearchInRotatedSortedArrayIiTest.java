package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchInRotatedSortedArrayIiTest {
    @Test
    void search() {
        SearchInRotatedSortedArrayIi.Solution solution = new SearchInRotatedSortedArrayIi().new Solution();
        Assertions.assertTrue(solution.search(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1}, 2));
        Assertions.assertFalse(solution.search(new int[]{1, 1}, 2));
        Assertions.assertFalse(solution.search(new int[]{1, 1}, 0));
    }
}