package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

/**
 * <pre>
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 *
 * Example 1:
 *
 * Input: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * Output: 1
 *
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * Output: 3
 *
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 * How would you optimize the kthSmallest routine?
 *
 *
 *
 * Constraints:
 *
 *     The number of elements of the BST is between 1 to 10^4.
 *     You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 * </pre>
 */
class KthSmallestElementInABst {

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
        int ans = -1;
        int cur = 0;

        /**
         * 二叉搜索树的中序遍历为递增序列，题意即为求该序列的第 k 个元素
         *
         * @param root
         * @param k
         * @return
         */
        public int kthSmallest(TreeNode root, int k) {
            dfs(root, k);
            return ans;
        }

        private void dfs(TreeNode root, int k) {
            // 停止搜索的条件
            if (cur == k) return;

            // 先搜索左子树
            if (root.left != null) dfs(root.left, k);
            if (cur == k) return;

            // 自身
            cur++;
            if (cur == k) {
                ans = root.val;
                return;
            }

            // 搜索右子树
            if (root.right != null) {
                dfs(root.right, k);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
