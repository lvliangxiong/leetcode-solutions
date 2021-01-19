package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

import java.util.*;

/**
 * <pre>
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * return its bottom-up level order traversal as:
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * </pre>
 */
class BinaryTreeLevelOrderTraversalIi {
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
        /**
         * 可以采用类似于 {@link BinaryTreeZigzagLevelOrderTraversal} 的方式进行实现。
         * 但是事实上，直接在『层次遍历』的基础上，对最终结果进行一次反转即可，速度会更快。
         *
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrderBottom(TreeNode root) {

            List<List<Integer>> ret = new ArrayList<>();
            if (root == null) return ret;
            Deque<TreeNode> queue = new ArrayDeque<>();
            List<Integer> level = new ArrayList<>();
            TreeNode mark = new TreeNode(0);
            queue.add(root);
            queue.add(mark);
            while (!queue.isEmpty()) {
                TreeNode node = queue.removeFirst();
                if (node != mark) {
                    level.add(node.val);
                    if (node.left != null) queue.addLast(node.left);
                    if (node.right != null) queue.addLast(node.right);
                } else {
                    ret.add(level);
                    level = new ArrayList<>();
                    if (queue.size() > 0) queue.add(mark);
                }
            }
            Collections.reverse(ret);
            return ret;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
