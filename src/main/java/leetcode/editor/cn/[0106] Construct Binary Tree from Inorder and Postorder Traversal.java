package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 *
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * </pre>
 */
class ConstructBinaryTreeFromInorderAndPostorderTraversal {
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
        private Map<Integer, Integer> mapping;

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if (inorder == null || postorder == null) return null;
            if (inorder.length != postorder.length) throw new IllegalArgumentException();
            mapping = new HashMap<Integer, Integer>() {
                {
                    for (int i = 0; i < inorder.length; i++) {
                        put(inorder[i], i);
                    }
                }
            };
            return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        }

        private TreeNode helper(int[] inorder, int i, int j, int[] postorder, int m, int n) {
            if (i > j) return null;
            TreeNode root = new TreeNode(postorder[n]);
            if (i == j) return root;
            int split = mapping.get(root.val);
            root.left = helper(inorder, i, split - 1, postorder, m, m + (split - 1) - i);
            root.right = helper(inorder, split + 1, j, postorder, n - (j - split), n - 1);
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
