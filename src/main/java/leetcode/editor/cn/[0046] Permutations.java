package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * </pre>
 */
class Permutations {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private List<List<Integer>> permutations = new ArrayList<>();

        private int len;
        private Integer[] nums;

        public List<List<Integer>> permute(int[] nums) {
            len = nums.length;
            this.nums = new Integer[len];
            Arrays.setAll(this.nums, i -> nums[i]);
            if (len == 0)
                permutations.add(Arrays.asList(this.nums));
            else
                backtrack(0);
            return permutations;
        }

        private void backtrack(int i) {
            if (i == len)
                permutations.add(Arrays.asList(nums.clone()));
            // select the element for the i-th position of output array in the [i, len]
            for (int j = i; j < len; j++) {
                swap(i, j);
                backtrack(i + 1); // continue
                swap(i, j);
            }
        }

        private void swap(int i, int j) {
            Integer tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
