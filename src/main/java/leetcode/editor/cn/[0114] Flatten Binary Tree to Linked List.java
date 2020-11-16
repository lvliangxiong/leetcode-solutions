package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

/**
 * <pre>
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * The flattened tree should look like:
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * </pre>
 */
class FlattenBinaryTreeToLinkedList {
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * <pre>
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     * </pre>
     */
    class Solution {
        /**
         * 题意为按照『前序遍历』的顺序将该树 flatten 成一个『单链表』。
         * <p>
         * A Morris Traversal implementation.
         *
         * @param root
         * @see BinaryTreeInorderTraversal.Solution#morrisInorderTraversal2(TreeNode)
         */
        public void flatten(TreeNode root) {
            TreeNode current = root;
            while (current != null) {
                if (current.left != null) {
                    // has left child
                    TreeNode next = current.left;
                    TreeNode rightMost = next;
                    while (rightMost.right != null) {
                        rightMost = rightMost.right;
                    }
                    rightMost.right = current.right;
                    // move left child to right and set left to null
                    current.right = next;
                    current.left = null;
                }
                // doesn't have left child or after restructuring
                current = current.right;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
