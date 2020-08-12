package leetcode.editor.cn;

import leetcode.editor.cn.tree.binarytree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <pre>
 * Given two binary trees, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 *
 * Example 1:
 *
 * Input:     1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * Output: true
 *
 * Example 2:
 *
 * Input:     1         1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * Output: false
 *
 * Example 3:
 *
 * Input:     1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * Output: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/same-tree
 * </pre>
 */
class SameTree {
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
        public boolean isSameTree(TreeNode p, TreeNode q) {
            // if (p == null ^ q == null) return false;
            // if (p == null && q == null) return true;
            // if (p.val != q.val) return false;
            // if (p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right))
            //     return true;
            // return false;
            return isSameTreeIterative(p, q);
        }

        public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
            if (p == null & q == null) return true;
            if (p == null ^ q == null) return false;
            Deque<TreeNode> queueP = new LinkedList<>();
            Deque<TreeNode> queueQ = new LinkedList<>();
            queueP.add(p);
            queueQ.add(q);
            while (!queueP.isEmpty() && !queueQ.isEmpty()) {
                TreeNode nodeP = queueP.remove();
                TreeNode nodeQ = queueQ.remove();
                if (!check(nodeP, nodeQ)) return false;
                if (nodeP == null & nodeQ == null) {
                    continue;
                } else {
                    // java Deque forbid null elements
                    if (nodeP.left != null && nodeQ.left != null) {
                        queueP.add(nodeP.left);
                        queueQ.add(nodeQ.left);
                    }
                    if (nodeP.right != null && nodeQ.right != null) {
                        queueP.add(nodeP.right);
                        queueQ.add(nodeQ.right);
                    }
                }
            }
            return true;
        }

        private boolean check(TreeNode p, TreeNode q) {
            if (p.val != q.val) return false;
            if (p.left == null ^ q.left == null) return false;
            if (p.right == null ^ q.right == null) return false;
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
