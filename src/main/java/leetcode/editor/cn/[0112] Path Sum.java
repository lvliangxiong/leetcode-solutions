package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

/**
 * <pre>
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values
 * along the path equals the given sum.
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
 *  /  \      \
 * 7    2      1
 *
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * </pre>
 */
class PathSum {
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
        public boolean hasPathSum(TreeNode root, int sum) {
            return dfs(root, sum);
        }

        private boolean dfs(TreeNode root, int target) {
            if (root == null) return false; // end at a non-leaf node
            // if it is leaf node
            if (root.left == null && root.right == null) return root.val == target;
            int nextTarget = target - root.val;
            return dfs(root.left, nextTarget) || dfs(root.right, nextTarget);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
