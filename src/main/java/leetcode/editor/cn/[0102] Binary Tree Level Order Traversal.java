package leetcode.editor.cn;

import leetcode.editor.cn.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class BinaryTreeLevelOrderTraversal {

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
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ans = new ArrayList<>();
            Deque<TreeNode> level = new ArrayDeque<>();
            if (root == null) return ans;
            level.offer(root);
            while (!level.isEmpty()) {
                int size = level.size();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = level.poll();
                    list.add(poll.val);
                    if (poll.left != null) level.offer(poll.left);
                    if (poll.right != null) level.offer(poll.right);
                }
                ans.add(list);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}