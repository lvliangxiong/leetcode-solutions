package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 *
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * </pre>
 */
class PathSumIi {
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
        private List<List<Integer>> paths = new ArrayList<>();

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            dfs(root, sum, new ArrayList<>());
            return paths;
        }

        private void dfs(TreeNode root, int target, ArrayList<Integer> path) {
            if (root == null) return; // end at a non-leaf node
            path.add(root.val); // add this node anyway

            if (root.left == null && root.right == null) {
                // if it is leaf node
                if (target == root.val)
                    paths.add(path);
            } else {
                // if it is a non-leaf node and non-null node
                int nextTarget = target - root.val;
                ArrayList<Integer> antherPath = new ArrayList<>(path);
                dfs(root.left, nextTarget, path);
                dfs(root.right, nextTarget, antherPath);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class OptimisedSolution {
        private List<Integer> path = new ArrayList<>();
        private List<List<Integer>> ans = new ArrayList<>();

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            if (root != null) backtrack(root, sum);
            return ans;
        }

        private void backtrack(TreeNode root, int target) {
            path.add(root.val); // add current node anyway

            if (root.left == null && root.right == null) {
                // leaf node
                if (root.val == target) {
                    // a right path found
                    ans.add(new ArrayList<>(path));
                }
            } else {
                // if root is a non-leaf node
                int newTarget = target - root.val;
                if (root.left != null) backtrack(root.left, newTarget); // try left path
                if (root.right != null) backtrack(root.right, newTarget); // try right path
            }
            path.remove(path.size() - 1); // remove the newly add node, backtrack
        }
    }
}
