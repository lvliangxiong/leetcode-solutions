package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

/**
 * <pre>
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 *
 *     a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 *
 *
 * Example 1:
 *
 * Given the following tree [3,9,20,null,null,15,7]:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Return true.
 *
 * Example 2:
 *
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 *
 * Return false.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * </pre>
 */
class BalancedBinaryTree {
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
        /**
         * 自底而上的迭代
         *
         * @param root
         * @return
         */
        private TreeInfo computeTreeInfo(TreeNode root) {
            if (root == null) return new TreeInfo(true, 0);
            TreeInfo left = computeTreeInfo(root.left);
            TreeInfo right = computeTreeInfo(root.right);
            return new TreeInfo(left.isBalanced &&
                    right.isBalanced && Math.abs(left.height - right.height) <= 1,
                    Math.max(left.height, right.height) + 1);
        }

        public boolean isBalanced(TreeNode root) {
            return computeTreeInfo(root).isBalanced;
        }
    }

    final class TreeInfo {
        boolean isBalanced;
        int height;

        TreeInfo(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }

        int getHeight() {
            return height;
        }

        boolean isBalanced() {
            return isBalanced;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

}
