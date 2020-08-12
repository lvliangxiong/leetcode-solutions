package leetcode.editor.cn;


import leetcode.editor.cn.tree.TreeNode;

import java.util.*;

/**
 * <pre>
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * </pre>
 */
class BinaryTreeInorderTraversal {
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
        public List<Integer> inorderTraversal(TreeNode root) {
            return morrisInorderTraversal2(root);
        }

        /**
         * 递归中序遍历
         *
         * @param root
         * @param snapshot
         */
        private void inorderRecursive(TreeNode root, List<Integer> snapshot) {
            if (root.left != null) {
                inorderRecursive(root.left, snapshot);
            }
            snapshot.add(root.val);
            if (root.right != null) {
                inorderRecursive(root.right, snapshot);
            }
        }

        /**
         * 迭代中序遍历
         *
         * @param root
         * @return
         */
        public List<Integer> inorderIterative1(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            TreeNode current = root;
            Deque<TreeNode> stack = new ArrayDeque<>();
            Set<TreeNode> visited = new HashSet<>();
            stack.push(current);
            while (!stack.isEmpty()) {
                // 循环压入当前栈顶节点的所有未遍历左节点
                current = stack.peek();
                while (current.left != null && !visited.contains(current.left)) {
                    stack.push(current.left);
                    current = current.left;
                }
                // 弹出栈顶元素，加入遍历列表，并将栈顶元素的右节点压栈
                list.add(current.val);
                visited.add(stack.pop());
                if (current.right != null) {
                    stack.push(current.right);
                }
            }
            return list;
        }

        /**
         * 迭代中序遍历
         *
         * @param root
         * @return
         */
        public List<Integer> inorderIterative2(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            TreeNode current = root;
            Deque<TreeNode> stack = new ArrayDeque<>();
            while (!stack.isEmpty() || current != null) {
                // 循环压入当前栈顶节点及其所有左子节点
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                // 弹出栈顶元素，加入遍历列表中，并将『当前节点』指向栈顶元素的右节点
                if (!stack.isEmpty()) {
                    current = stack.pop();
                    list.add(current.val);
                    current = current.right;
                }
            }
            return list;
        }

        /**
         * Morris Traversal, this implementation destroys the original tree structure.
         *
         * @param root
         * @return
         */
        public List<Integer> morrisInorderTraversal1(TreeNode root) {
            TreeNode current = root;
            TreeNode predecessor;
            List<Integer> ret = new ArrayList<>();
            while (current != null) {
                if (current.left != null) {
                    /* 当前节点的左节点不为 null，即当前节点具有左子树时，左子树需要先被遍历，通过如下方式
                     * 可以找到当前节点的前驱节点：左子树的最右节点。
                     * 1. 找到左子树的最右子节点，该节点即为当前节点 current 的前驱节点 predecessor
                     * 2. 将 current 节点连接到 predecessor 节点的右子节点
                     * 3. 解除 current 节点的左子树链接
                     * 4. 当前节点指向被上抬的左子树根节点，也就是原先的 current.left
                     * 整个过程完成了左子树 predecessor 的『上抬』 */
                    predecessor = current.left;
                    while (predecessor.right != null) {
                        predecessor = predecessor.right;
                    }
                    predecessor.right = current;
                    TreeNode leftChild = current.left;
                    current.left = null;
                    current = leftChild;
                } else {
                    /* 当前节点的左节点为 null，即没有左子树的情况，将当前节点加入遍历结果中 */
                    ret.add(current.val);
                    current = current.right; // 将当前节点指向其右子节点
                }
            }
            // 上述循环结束后，原树结构受到了破坏
            return ret;
        }


        /**
         * Morris Traversal, this implementation doesn't destroy the original tree structure.
         *
         * @param root
         * @return
         */
        public List<Integer> morrisInorderTraversal2(TreeNode root) {
            TreeNode current = root;
            TreeNode predecessor; // 当前节点的前驱节点
            List<Integer> ret = new ArrayList<>();
            while (current != null) {
                if (current.left != null) {
                    predecessor = current.left;
                    while (predecessor.right != null && predecessor.right != current) {
                        predecessor = predecessor.right;
                    }
                    if (predecessor.right == null) {
                        predecessor.right = current; // 建立与前驱节点的新链接
                        current = current.left;
                    }
                    if (predecessor.right == current) {
                        predecessor.right = null; // 解除链接
                        ret.add(current.val);
                        current = current.right;
                    }
                } else {
                    /* 当前节点的左节点为 null，即没有左子树的情况，将当前节点加入遍历结果中 */
                    ret.add(current.val);
                    current = current.right; // 将当前节点指向其右子节点
                }
            }
            // 上述循环结束后，整个树的所有节点均只有『右子节点』，并且是按照原树结构的中序遍历顺序排列的
            return ret;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
