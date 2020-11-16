package leetcode.editor.cn;


import leetcode.editor.cn.tree.TreeNode;

/**
 * <pre>
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 *     The left subtree of a node contains only nodes with keys less than the node's key.
 *     The right subtree of a node contains only nodes with keys greater than the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 *
 *
 *
 * Example 1:
 *
 *     2
 *    / \
 *   1   3
 *
 * Input: [2,1,3]
 * Output: true
 *
 * Example 2:
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 *
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * </pre>
 */
class ValidateBinarySearchTree {
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
        public boolean isValidBST(TreeNode root) {
            if (root == null || (root.left == null && root.right == null))
                return true;
            return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        /**
         * 这里使用 long 类型，使得算法允许 BST 中出现 Integer.MAX_VALUE 和 Integer.MIN_VALUE。
         * 另外还可以使用 Integer 类型作为参数，这样就能使用 null 代表没有边界。
         *
         * @param root
         * @param minValue
         * @param maxValue
         * @return
         */
        private boolean helper(TreeNode root, long minValue, long maxValue) {
            if (root == null) return true;
            if (root.val <= minValue || root.val >= maxValue) {
                return false;
            }
            return helper(root.left, minValue, root.val) && helper(root.right, root.val, maxValue);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
