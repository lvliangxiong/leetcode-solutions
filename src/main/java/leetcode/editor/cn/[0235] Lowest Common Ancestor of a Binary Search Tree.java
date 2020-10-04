package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

class LowestCommonAncestorOfABinarySearchTree {

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
         * 相对于求普通二叉树的 LCR，注意使用 BST 的特性
         *
         * @param root
         * @param p
         * @param q
         * @return
         * @see LowestCommonAncestorOfABinaryTree.Solution
         * @see LowestCommonAncestorOfABinaryTree.HashSolution
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            List<TreeNode> pathP = getPath(root, p);
            List<TreeNode> pathQ = getPath(root, q);
            TreeNode ancestor = null;
            // 两条 path 上最后一个相同节点，即为 p 和 q 的 LCR
            int last = 0;
            for (; last < pathP.size() && last < pathQ.size(); ++last) {
                if (pathP.get(last) != pathQ.get(last)) break;
            }
            return pathP.get(last - 1);
        }

        /**
         * Returns a list of TreeNode start from the root to the target.
         *
         * @param root
         * @param target
         * @return
         */
        public List<TreeNode> getPath(TreeNode root, TreeNode target) {
            List<TreeNode> path = new ArrayList<>();
            TreeNode node = root;
            while (node != target) {
                path.add(node);
                if (target.val < node.val) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            path.add(node);
            return path;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class SinglePathSolution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode ancestor = root;
            while (true) {
                if (p.val < ancestor.val && q.val < ancestor.val) {
                    // 如果 ancestor 的值大于 p 和 q 的值，那么 p 和 q 必在其左子树
                    ancestor = ancestor.left;
                } else if (p.val > ancestor.val && q.val > ancestor.val) {
                    // 如果 ancestor 的值小于 p 和 q 的值，那么 p 和 q 必在其左子树
                    ancestor = ancestor.right;
                } else {
                    // 后者，当前 ancestor 就是 LCR
                    break;
                }
            }
            return ancestor;
        }
    }

}
