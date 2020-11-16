package leetcode.editor.cn;

import leetcode.editor.cn.tree.binarytree.TreeNode;

/**
 * <pre>
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is
 * the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 * </pre>
 */
class DiameterOfBinaryTree {

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
        int max = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            if (root != null) dfs(root);
            return max;
        }

        /**
         * Returns the length of the path from the deepest leaf node to the root.
         * <p>
         * If the root is a leaf node, returns 0.
         *
         * @param root
         * @return
         */
        private int dfs(TreeNode root) {
            if (root == null) return -1;
            int le = dfs(root.left);
            int ri = dfs(root.right);
            max = Math.max(max, le + ri + 2); // update max
            return le > ri ? le + 1 : ri + 1;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}