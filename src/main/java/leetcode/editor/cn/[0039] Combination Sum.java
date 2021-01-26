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
            Arrays.sort(candidates); // 排序后，可以进行剪枝提升计算效率
            return combinationSum(candidates, target, 0);
        }

        /**
         * 这种递归的解法，思路和 BFS 类似。
         *
         * @param candidates
         * @param target
         * @param begin
         * @return
         */
        private List<List<Integer>> combinationSum(int[] candidates, int target, int begin) {
            // 递归终止条件
            if (target == 0) return new ArrayList<>() {
                {
                    add(new ArrayList<>());
                }
            };

            if (begin == candidates.length) return new ArrayList<>(); // 失败

            // trial and error
            int most = target / candidates[begin], remained = target % candidates[begin];
            if (most == 0) return new ArrayList<>(); // 因为进行了排序操作，所以可以进行剪枝

            List<List<Integer>> ans = new ArrayList<>();
            for (int j = most; j >= 0; j--) {
                List<List<Integer>> list = combinationSum(candidates, remained, begin + 1);
                if (list.size() > 0) { // 一次性添加 j 个 candidates[begin] 是可行解
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

    /**
     * DFS + BackTrack
     */
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
                // 重点理解这里剪枝，前提是候选数组已经有序
                if (target < candidates[i]) {
                    break;
                }

                path.addLast(candidates[i]);
                dfs(candidates, i /*注意这里是 i，表明后续添加到 path 中的元素的索引必须不小于 i*/,
                        len, target - candidates[i], path, result);
                path.removeLast(); // 回溯
            }
        }
    }

    /**
     * 此问题类似于『背包问题』中的『完全背包』问题，因此可以使用 DP 的思路进行解决。
     *
     * @see RegularExpressionMatching.Solution#isMatch(String, String)
     */
    class DPSolution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> ans = new ArrayList<>();
            int len = candidates.length;
            /*
             * dp[i][j] 表示使用 candidates 前 i 个元素组合出 j 的所有组合列表。
             * dp[i][j] = {dp[i-1][j], dp[i-1][j-candidates[i]], ... , dp[i-1][j-candidates[i] * most]}
             * 其等效形式是：
             * dp[i][j] = {dp[i-1][j], dp[i][j-candidates[i]]}
             * 和正则表达式的解题思路有点类似。
             * */
            List<List<Integer>> dp[] = new ArrayList[target + 1]; // 这里进行了空间优化，注意这里不能使用泛型

            // i = 0
            Arrays.setAll(dp, i -> new ArrayList<>());
            dp[0].add(new ArrayList<>()); // 初始状态下，和为 0 的组合（solution）有一个

            // i >= 1
            for (int i = 0; i < len; i++) {
                if (candidates[i] > target) break; // 剪枝
                for (int j = candidates[i]; j <= target; j++) {
                    // 重新计算 dp[j]，对新添加入考虑范围的 candidates[i] 进行 trial and error
                    for (List<Integer> com : dp[j - candidates[i]]) {
                        List<Integer> copy = new ArrayList<>();
                        copy.add(candidates[i]);
                        copy.addAll(com);
                        dp[j].add(copy);
                    }
                }
            }
            return dp[target];
        }
    }
}
