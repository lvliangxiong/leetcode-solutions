package leetcode.editor.cn;

import leetcode.editor.cn.tree.binarytree.TreeNode;

class InsertIntoABinarySearchTree {

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
        public TreeNode insertIntoBST(TreeNode root, int val) {
            TreeNode newNode = new TreeNode(val);
            if (root == null) return newNode;
            TreeNode cur = root;
            while (true) {
                if (cur.val < val) {
                    // insert into the right child
                    if (cur.right == null) {
                        cur.right = newNode;
                        return root;
                    }
                    cur = cur.right;
                } else {
                    // insert nto the left child
                    if (cur.left == null) {
                        cur.left = newNode;
                        return root;
                    }
                    cur = cur.left;
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}