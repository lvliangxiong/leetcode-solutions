package leetcode.editor.cn;

import java.util.*;

/**
 * <pre>
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique
 * combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 *     All numbers (including target) will be positive integers.
 *     The solution set must not contain duplicate combinations.
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 *
 *
 * Constraints:
 *
 *     1 <= candidates.length <= 30
 *     1 <= candidates[i] <= 200
 *     Each element of candidate is unique.
 *     1 <= target <= 500
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * </pre>
 */
class CombinationSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            return combinationSum(candidates, target, 0);
        }

        private List<List<Integer>> combinationSum(int[] candidates, int target, int begin) {
            // 递归终止条件
            if (target == 0) return new ArrayList<List<Integer>>() {{
                add(new ArrayList<>());
            }}; // 成功
            if (begin == candidates.length) return new ArrayList<>(); // 失败
            // 继续递归
            int most = target / candidates[begin], remained = target % candidates[begin];
            if (most == 0) return new ArrayList<>(); // 减枝
            List<List<Integer>> ans = new ArrayList<>();
            for (int j = most; j >= 0; j--) {
                List<List<Integer>> list = combinationSum(candidates, remained, begin + 1);
                if (list.size() > 0) {
                    for (List<Integer> com : list) {
                        for (int k = 0; k < j; k++) {
                            com.add(candidates[begin]);
                        }
                    }
                }
                ans.addAll(list);
                remained += candidates[begin];
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DFSSolution {

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            int len = candidates.length;
            List<List<Integer>> result = new ArrayList<>();
            if (len == 0) {
                return result;
            }

            // 排序是剪枝的前提
            Arrays.sort(candidates);
            Deque<Integer> path = new ArrayDeque<>();
            dfs(candidates, 0, len, target, path, result);
            return result;
        }

        private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path,
                         List<List<Integer>> result) {
            // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
            if (target == 0) {
                result.add(new ArrayList<>(path));
                return;
            }

            for (int i = begin; i < len; i++) {
                // 重点理解这里剪枝，前提是候选数组已经有序，
                if (target < candidates[i]) {
                    break;
                }

                path.addLast(candidates[i]);
                dfs(candidates, i, len, target - candidates[i], path, result);
                path.removeLast();
            }
        }
    }

}
