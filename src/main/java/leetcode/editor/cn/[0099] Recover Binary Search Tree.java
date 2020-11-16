package leetcode.editor.cn;

import leetcode.editor.cn.tree.binarytree.TreeNode;

/**
 * <pre>
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Example 1:
 *
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 * Example 2:
 *
 * Input: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * Output: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 *
 * Follow up:
 *
 *     A solution using O(n) space is pretty straight forward.
 *     Could you devise a constant space solution?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * </pre>
 */
class RecoverBinarySearchTree {
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
         * <pre>
         * 将 BST 的中序遍历转为一个数组，被调换顺序的两个节点在数组中与其前驱节点的大小关系必会出现异常。
         * 例如：
         *      [1, 2, 3, 4, 5, 6, 7] ==> [1, 2, 6, 4, 5, 3, 7]
         * 3 和 6 进行了调换，导致『6 和 4』以及『5 和 3』的大小出现问题。
         * </pre>
         *
         * @param root
         * @see BinaryTreeInorderTraversal.Solution#morrisInorderTraversal1(leetcode.editor.cn.tree.TreeNode)
         * @see BinaryTreeInorderTraversal.Solution#morrisInorderTraversal2(leetcode.editor.cn.tree.TreeNode)
         */
        public void recoverTree(TreeNode root) {
            TreeNode current = root;
            TreeNode predecessor = null; // Morris predecessor
            TreeNode pred = null; // predecessor in inorder traversal
            TreeNode x = null, y = null;
            while (current != null) {
                if (current.left != null) {
                    predecessor = current.left;
                    while (predecessor.right != null && predecessor.right != current) {
                        predecessor = predecessor.right;
                    }
                    if (predecessor.right == null) {
                        predecessor.right = current; // 建立链接
                        current = current.left; // 当前节点移动到左子树
                        continue;
                    }
                    if (predecessor.right == current) {
                        predecessor.right = null; // 消除链接
                    }
                }
                // 当『当前节点』没有左子树时，或者左子树已遍历过时（存在循环）
                if (pred != null && current != null && current.val < pred.val) {
                    y = current;
                    if (x == null) {
                        x = pred;
                    }
                    // 这里如果找到两对逆序对后，直接返回，可以能会在原树结构中留下未被清除的链接
                }
                pred = current;
                current = current.right;
            }
            swap(x, y);
        }

        private void swap(TreeNode x, TreeNode y) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
