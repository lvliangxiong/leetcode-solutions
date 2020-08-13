package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
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

        public List<List<Integer>> permute(int[] nums) {
            len = nums.length;
            // 两种 int[] ==> List<Integer> 的方法，第二种要更快一些
            // permutation = Arrays.stream(nums).boxed().collect(Collectors.toList()); // int[] ==> List<Integer>
            for (int num : nums) permutation.add(num);
            if (len == 0) permutations.add(permutation);
            else backtrack(0);
            return permutations;
        }

        private void backtrack(int i) {
            // select the element for the i-th position of output array in the [i, len]
            for (int j = i; j < len; j++) {
                if (i == len - 1)
                    permutations.add(new ArrayList<>(permutation));
                else {
                    Collections.swap(permutation, i, j); // pick j-th element
                    backtrack(i + 1); // continue
                    Collections.swap(permutation, i, j); // release and prepare for a brand new pick
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
