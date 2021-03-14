package leetcode.editor.cn;


import leetcode.editor.cn.tree.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class LowestCommonAncestorOfABinaryTree {

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
        TreeNode ans;

        /**
         * LCR 的特征：
         * 1. p 和 q 分别在 LCR 的左子树和右子树中
         * OR
         * 2. p 和 q 中一个就是 LCR，另一个在 LCR 的左子树或者右子树中
         *
         * @param root
         * @param p
         * @param q
         * @return
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            helper(root, p, q);
            return ans;
        }

        /**
         * Return true if anyone of p or q locates in the subtree of root.
         *
         * @param root
         * @param p
         * @param q
         * @return
         */
        private boolean helper(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return false;

            // ans 不是 null 意味着已经找到 LCR，无需继续递归查找，root 必定是 LCA 的祖先节点，因此直接返回 true!
            if (ans != null) return true;
            // 进入左子树进行查找
            boolean inLeft = helper(root.left, p, q);
            // 这里如果 ans 不为 null，说明是 p, q 都在其左子树中找到的，因此直接返回 true 即可！
            if (ans != null) return true;
            // 进入右子树进行查找
            boolean inRight = helper(root.right, p, q);
            // 这里如果 ans 不为 null，说明是 p, q 都在其右子树中找到的，因此直接返回 true 即可！
            if (ans != null) return true;

            // 判断当前节点是不是目标 LCA
            boolean isItself = root.val == p.val || root.val == q.val;
            if (ans == null)
                if (inLeft && inRight || (isItself && (inLeft || inRight)))
                    ans = root;
            // 任何一个条件满足，都会返回 true
            return inLeft || inRight || isItself;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * 使用一个 HashMap 存储『子节点的值-父节点』的 key-value 对
     */
    class HashMapSolution {
        Map<Integer, TreeNode> parent = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        /**
         * populate HashMap parent
         *
         * @param root
         */
        public void helper(TreeNode root) {
            if (root.left != null) {
                parent.put(root.left.val, root);
                helper(root.left);
            }
            if (root.right != null) {
                parent.put(root.right.val, root);
                helper(root.right);
            }
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            helper(root);
            // find all the parents of p and add them into Set visited
            while (p != null) {
                visited.add(p.val);
                p = parent.get(p.val);
            }
            // iterating the parents of q, find the common nearest parent by searching in visited
            while (q != null) {
                if (visited.contains(q.val)) {
                    return q;
                }
                q = parent.get(q.val);
            }
            return null;
        }
    }

}
