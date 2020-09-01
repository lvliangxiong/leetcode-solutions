package leetcode.editor.cn;

import leetcode.editor.cn.tree.binarytree.TreeNode;

/**
 * <pre>
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to
 * the original key plus sum of all keys greater than the original key in BST.
 *
 * Example:
 *
 * Input: The root of a Binary Search Tree like this:
 *               5
 *             /   \
 *            2     13
 *
 * Output: The root of a Greater Tree like this:
 *              18
 *             /   \
 *           20     13
 *
 * Note: This question is the same as 1038: https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
 * </pre>
 */
class ConvertBstToGreaterTree {

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
        int sum = 0;

        public TreeNode convertBST(TreeNode root) {
            if (root != null) reverseOrder(root);
            return root;
        }

        private void reverseOrder(TreeNode root) {
            if (root.right != null)
                reverseOrder(root.right);
            root.val += sum;
            sum = root.val;
            if (root.left != null)
                reverseOrder(root.left);

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}