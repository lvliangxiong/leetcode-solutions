package leetcode.editor.cn;

import java.util.ArrayList;
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
        private List<Integer> permutation = new ArrayList<>();

        private int len;
        private int[] nums;

        public List<List<Integer>> permute(int[] nums) {
            len = nums.length;
            this.nums = nums;
            if (len == 0) permutations.add(permutation);
            else backtrack(0);
            return permutations;
        }

        private void backtrack(int i) {
            // select the element for the i-th position of output array in the [i, len]
            for (int j = i; j < len; j++) {
                permutation.add(nums[j]); // pick the j-th element
                if (i == len - 1)
                    permutations.add(new ArrayList<>(permutation));
                else {
                    swap(i, j);
                    backtrack(i + 1); // continue
                    swap(i, j);
                }
                permutation.remove(i); // release this pick, prepare for the next pick
            }
        }

        private void swap(int i, int j) {
            int num = nums[i];
            nums[i] = nums[j];
            nums[j] = num;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
