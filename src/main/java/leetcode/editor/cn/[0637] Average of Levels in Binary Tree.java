package leetcode.editor.cn;

import javafx.util.Pair;
import leetcode.editor.cn.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * <pre>
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 *
 * Example 1:
 *
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 *
 * Note:
 *
 *     The range of node's value is in the range of 32-bit signed integer.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree
 * </pre>
 */
class AverageOfLevelsInBinaryTree {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * <pre>
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     * </pre>
     */
    class Solution {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> ans = new ArrayList<>();
            if (root == null) return ans;
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int count = queue.size();
                double sum = 0;
                for (int i = 0; i < count; i++) {
                    TreeNode cur = queue.poll();
                    sum += cur.val;
                    if (cur.left != null) queue.offer(cur.left);
                    if (cur.right != null) queue.offer(cur.right);
                }
                ans.add(sum / count);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class RecursionSolution {
        ArrayList<Pair<Double, Integer>> stats = new ArrayList<>();

        public List<Double> averageOfLevels(TreeNode root) {
            if (root != null) dfs(root, 0);
            // 1. 使用循环，从一个 Collection 构建出一个新的 Collection
            return new ArrayList<Double>() {{
                for (Pair<Double, Integer> stat : stats) {
                    add(stat.getKey() / stat.getValue());
                }
            }};
            // 2. 使用 JDK8 的新 stream() api，速度较第一种要慢一些，但是语法更优雅
            // return stats.stream().map(t -> t.getKey() / t.getValue()).collect(Collectors.toList());
        }

        private void dfs(TreeNode root, int depth) {
            if (depth < stats.size()) {
                Pair<Double, Integer> old = stats.get(depth);
                stats.set(depth, new Pair<>(old.getKey() + root.val, old.getValue() + 1));
            } else {
                stats.add(new Pair<>((double) root.val, 1));
            }
            if (root.left != null) dfs(root.left, depth + 1);
            if (root.right != null) dfs(root.right, depth + 1);
        }

    }

}
