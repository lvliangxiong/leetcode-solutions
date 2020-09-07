package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

/**
 * <pre>
 * Invert a binary tree.
 *
 * Example:
 *
 * Input:
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * Output:
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * Trivia:
 * This problem was inspired by this original tweet by Max Howell:
 *
 *     Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a
 *     whiteboard so f*** off.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * </pre>
 */
class InvertBinaryTree {

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
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return root;
            TreeNode le = root.left;
            root.left = root.right;
            root.right = le;
            invertTree(root.left);
            invertTree(root.right);
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}