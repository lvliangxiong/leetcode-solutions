package leetcode.editor.cn;

import java.util.*;

/**
 * <pre>
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in
 * candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 *     All numbers (including target) will be positive integers.
 *     The solution set must not contain duplicate combinations.
 *
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * </pre>
 */
class CombinationSumIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> ans = new ArrayList<>();

        /**
         * 这里使用 DFS + Backtracking 的思路进行求解，其实也可以将其转化为『多重背包』问题进行求解。
         *
         * @param candidates
         * @param target
         * @return
         */
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            dfs(candidates, 0, candidates.length, target, new ArrayDeque<>());
            return ans;
        }

        /**
         * Backtracking
         *
         * @param candidates
         * @param start
         * @param len
         * @param target
         * @param path
         */
        private void dfs(int[] candidates, int start, int len, int target, Deque<Integer> path) {
            if (target == 0) {
                ans.add(new ArrayList<>(path));
                return;
            }
            if (start == len) return; // 搜索失败

            // 计算连续相同的元素个数，start 指向该连续相同元素的最后一个元素的索引
            int count = 1;
            while (start < len - 1 && candidates[start] == candidates[start + 1]) {
                start++;
                count++;
            }
            count = Math.min(count, target / candidates[start]);
            if (count == 0) return; // 当前元素太大，后续的元素比当前元素更大，搜索直接停止

            // 对于多个相同元素，对选择的个数进行遍历
            int remained = target;

            // 一个都不添加
            dfs(candidates, start + 1, len, remained, path);
            // 添加至少一个
            for (int i = 0; i < count; i++) {
                path.offerLast(candidates[start]);
                remained -= candidates[start];
                dfs(candidates, start + 1, len, remained, path);
            }
            // 回溯
            for (int i = 0; i < count; i++) {
                path.pollLast();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
