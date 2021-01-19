package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
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
 * return its zigzag level order traversal as:
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * </pre>
 */
class BinaryTreeZigzagLevelOrderTraversal {
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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> ret = new ArrayList<>();
            if (root == null) return ret;
            Deque<TreeNode> queue = new LinkedList<>(); // 队列中的元素都是从左到右
            LinkedList<Integer> level = new LinkedList<>(); // 层次列表中的元素在添加时次序有所不同
            TreeNode mark = new TreeNode(0);
            queue.add(root);
            queue.add(mark);
            boolean reversed = false;
            while (!queue.isEmpty()) {
                TreeNode node = queue.removeFirst();
                if (node != mark) {
                    if (reversed) {
                        level.addFirst(node.val);
                    } else {
                        level.addLast(node.val);
                    }
                    if (node.left != null) queue.addLast(node.left);
                    if (node.right != null) queue.addLast(node.right);
                } else {
                    // 当前层遍历完毕
                    ret.add(level);
                    // 准备下一层的遍历
                    level = new LinkedList<>();
                    reversed = !reversed;
                    if (queue.size() > 0) queue.add(mark);
                }
            }
            return ret;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
