package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

/**
 * <pre>
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
 * the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 *
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * </pre>
 */
class BinaryTreeMaximumPathSum {

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
        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            helper(root);
            return max;
        }

        /**
         * 返回该 root 节点的所有子节点与 root 节点之间的路径和的最大值
         *
         * @param root
         * @return
         */
        private int helper(TreeNode root) {
            int sum = 0;
            if (root == null) sum = 0;
            else {
                int ri = Math.max(helper(root.right), 0);
                int le = Math.max(helper(root.left), 0);
                sum = Math.max(ri, le) + root.val;
                // 更新以 root 为转折点的最大路径和
                int pathSum = ri + le + root.val;
                if (max < pathSum) {
                    max = pathSum;
                }
            }
            return sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
