package leetcode.editor.cn;

import leetcode.editor.cn.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class BinaryTreePreorderTraversal {

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
        /**
         * @param root
         * @return
         *
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> snapshot = new ArrayList<>();
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode cur = root;
            while (cur != null || !stack.isEmpty()) {
                // 将 cur 的所有左子节点压栈，知道 cur 为 null
                while (cur != null) {
                    snapshot.add(cur.val);
                    stack.push(cur);
                    cur = cur.left;
                }
                // 压栈的过程中就已经把所有的节点添加到了 snapshot，因此出栈的时候就不重复进行输出了！而是转为其右子节点！
                // 而且这里不需要判断 stack 是否为空，因为如果这里 stack 为空，说明 cur 必定不是 null，那么在上面的循环中就会
                // 将 cur 添加到 stack 中，使得 stack 不是空，相互矛盾，因此这里的 stack 必定不是空。
                cur = stack.pop();
                cur = cur.right;
            }
            return snapshot;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class RecursionSolution {
        public List<Integer> preorderTraversal(TreeNode root) {
            return preorderTraversalByRecursion(root);
        }

        private List<Integer> preorderTraversalByRecursion(TreeNode root) {
            List<Integer> snapshot = new ArrayList<>();
            preorderTraversalByRecursionCore(root, snapshot);
            return snapshot;
        }

        private void preorderTraversalByRecursionCore(TreeNode root, List<Integer> snapshot) {
            if (root != null) {
                snapshot.add(root.val);
                preorderTraversalByRecursionCore(root.left, snapshot);
                preorderTraversalByRecursionCore(root.right, snapshot);
            }
        }
    }

    /**
     * 可以在常数空间内进行遍历！
     * 核心思想是利用树的大量空闲指针，实现空间开销的极限缩减！
     */
    class MorrisTraversalSolution {

        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> snapshot = new ArrayList<Integer>();
            if (root == null) {
                return snapshot;
            }

            // 1. 新建临时节点 cur，令该节点为 root
            TreeNode cur = root;

            while (cur != null) {
                TreeNode leftChild = cur.left;
                if (leftChild == null) {
                    // 2.1 如果当前节点的左子节点为空，将当前节点加入答案
                    snapshot.add(cur.val);
                } else {
                    // 2.2 如果当前节点的左子节点不为空，在当前节点的左子树中找到当前节点在『中序遍历』下的『前驱节点』
                    TreeNode predecessor = leftChild;
                    while (predecessor.right != null && predecessor.right != cur) {
                        predecessor = predecessor.right;
                    }
                    if (predecessor.right == null) {
                        // 如果前驱节点的右子节点为空，将前驱节点的右子节点设置为当前节点。
                        predecessor.right = cur;
                        // 然后将当前节点加入 snapshot。当前节点更新为当前节点的左子节点。
                        snapshot.add(cur.val);
                        cur = cur.left;
                        continue;
                    } else {
                        // 如果前驱节点的右子节点为当前节点，将它的右子节点重新设为空。
                        predecessor.right = null;
                    }
                }
                // 3. 将当前节点设置为其右子节点
                cur = cur.right;
            }
            return snapshot;
        }
    }
}