package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: [[1]]
 *
 *
 *
 * Constraints:
 *
 *     1 <= n <= 20
 *     1 <= k <= n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * </pre>
 */
class Combinations {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> combinations = new ArrayList<>();
        int n;
        int k;
        List<Integer> combination;


        public List<List<Integer>> combine(int n, int k) {
            this.n = n;
            this.k = k;
            combination = new ArrayList<>(k);
            if (n != 0 && k != 0)
                backtrack(0, 1);
            return combinations;
        }

        private void backtrack(int i, int start) {
            // 当可选择的数的数量小于组合还需要的数的数量时，进行『剪枝』
            if (n - start + 1 < k - i) return;
            // 递归终止条件
            if (i == k) {
                combinations.add(new ArrayList<>(combination));
                return;
            }
            // choose the i-th element for the combination
            for (int num = start; num <= n; num++) {
                combination.add(num);
                backtrack(i + 1, num + 1);
                combination.remove(i);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    class BinarySolution {
        /**
         * @param n
         * @param k
         * @return
         * @see NextPermutation.Solution#nextPermutation(int[])
         */
        public List<List<Integer>> combine(int n, int k) {
            List<Integer> combination = new ArrayList<>();
            List<List<Integer>> combinations = new ArrayList<>();
            // 初始化
            for (int i = 1; i <= k; ++i) {
                combination.add(i);
            }
            // 末尾加一位 n + 1 作为哨兵
            combination.add(n + 1);

            int j = 0;
            while (j < k) {
                combinations.add(new ArrayList<>(combination.subList(0, k)));
                j = 0;
                // 寻找第一个 combination[j] + 1 != combination[j + 1] 的位置 t
                // 我们需要把 [0, t - 1] 区间内的每个位置重置成 [1, t]
                while (j < k && combination.get(j) + 1 == combination.get(j + 1)) {
                    combination.set(j, j + 1);
                    ++j;
                }
                // j 是第一个 combination[j] + 1 != combination[j + 1] 的位置
                combination.set(j, combination.get(j) + 1);
            }
            return combinations;
        }
    }

}
