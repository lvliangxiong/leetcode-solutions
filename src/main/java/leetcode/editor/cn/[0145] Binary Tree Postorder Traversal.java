package leetcode.editor.cn;

import leetcode.editor.cn.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class BinaryTreePostorderTraversal {

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
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> snapshot = new ArrayList<>();
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode cur = root, lastVisited = null;
            while (cur != null || !stack.isEmpty()) {
                // 如果 cur 不是 null，那么将 cur 及其所有的左子节点压栈，直到 cur 为 null，此时输出到 snapshot
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                cur = stack.peek(); // 取栈顶元素，暂时不 pop，需要判断其左右节点是否都已经输出过了
                if (cur.right == lastVisited || cur.right == null) {
                    snapshot.add(stack.pop().val);
                    lastVisited = cur;
                    cur = null; // 这里将 cur 设置为 null，是为了避免前面的循环重复入栈
                } else {
                    cur = cur.right;
                }
            }
            return snapshot;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class RecursionSolution {
        public List<Integer> postorderTraversal(TreeNode root) {
            return postorderTraversalByRecursion(root);
        }

        private List<Integer> postorderTraversalByRecursion(TreeNode root) {
            List<Integer> snapshot = new ArrayList<>();
            postorderTraversalByRecursionCore(root, snapshot);
            return snapshot;
        }

        private void postorderTraversalByRecursionCore(TreeNode root, List<Integer> snapshot) {
            if (root != null) {
                postorderTraversalByRecursionCore(root.left, snapshot);
                postorderTraversalByRecursionCore(root.right, snapshot);
                snapshot.add(root.val);
            }
        }
    }

}