package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

/**
 * <pre>
 * Find the sum of all left leaves in a given binary tree.
 *
 * Example:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-left-leaves
 * </pre>
 */
class SumOfLeftLeaves {

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
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) return 0;
            int ans = 0;
            if (root.left != null) {
                if (isLeaf(root.left)) {
                    ans += root.left.val;
                } else ans += sumOfLeftLeaves(root.left);
            }
            if (root.right != null) ans += sumOfLeftLeaves(root.right);
            return ans;
        }

        private boolean isLeaf(TreeNode root) {
            return root != null && root.left == null && root.right == null;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
