package leetcode.editor.cn;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/19/2020
 */
class SortColorsTest {
    @Test
    void sortColors() {
        SortColors.Solution solution = new SortColors().new Solution();
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        solution.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}