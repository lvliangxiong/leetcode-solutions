package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example:
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * </pre>
 */
class PermutationsIi {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private List<List<Integer>> permutations = new ArrayList<>();
        private List<Integer> permutation = new ArrayList<>();

        private int len;
        private int[] nums;
        private boolean[] selected;

        public List<List<Integer>> permuteUnique(int[] nums) {
            len = nums.length;
            Arrays.sort(nums); // sort the array anyway

            if (len == 0) {
                permutations.add(permutation);
            } else {
                this.nums = nums;
                selected = new boolean[len];
                backtrack(0);
            }
            return permutations;
        }

        private void backtrack(int i) {
            if (i == len)
                permutations.add(new ArrayList<>(permutation));

            // select the element for the i-th position of output array
            for (int j = 0; j < len; j++) {
                // if the j-th element of original array has been selected, skip
                if (selected[j]) continue;

                // always only choose the first one if there are duplicate element
                if (j != 0 && !selected[j - 1] && nums[j - 1] == nums[j]) continue;

                permutation.add(nums[j]);
                selected[j] = true;
                backtrack(i + 1); // continue
                selected[j] = false;
                permutation.remove(i);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
