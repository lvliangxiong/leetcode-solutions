package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

/**
 * <pre>
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点。
 *
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 *
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * </pre>
 */
class KthLargestNodeInBinarySearchTree {

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
        int ans = -1;
        int count = 0;
        int k;

        public int kthLargest(TreeNode root, int k) {
            if (root == null || k == 0) return ans;
            this.k = k;
            helper(root);
            return ans;
        }

        void helper(TreeNode root) {
            if (root.right != null) helper(root.right);
            if (count == k) return; // already found in the right child
            if (++count == k) ans = root.val; // taking self into account and incr count
            if (root.left != null) helper(root.left);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}