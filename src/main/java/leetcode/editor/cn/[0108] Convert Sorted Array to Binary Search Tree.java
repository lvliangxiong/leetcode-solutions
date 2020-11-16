package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

/**
 * <pre>
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of
 * the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * </pre>
 */
class ConvertSortedArrayToBinarySearchTree {
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
        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums == null) return null;
            return helper(nums, 0, nums.length - 1);
        }

        private TreeNode helper(int[] nums, int low, int high) {
            if (low > high) return null;
            if (low == high) return new TreeNode(nums[low]);
            int split = (high + low + 1) >>> 1;
            TreeNode root = new TreeNode(nums[split]);
            root.left = helper(nums, low, split - 1);
            root.right = helper(nums, split + 1, high);
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
