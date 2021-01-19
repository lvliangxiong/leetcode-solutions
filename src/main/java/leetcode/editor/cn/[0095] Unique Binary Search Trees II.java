package leetcode.editor.cn;

import leetcode.editor.cn.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

class UniqueBinarySearchTreesIi {
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
        public List<TreeNode> generateTrees(int n) {
            if (n == 0) return new ArrayList<>();
            return generateTrees(1, n);
        }

        private List<TreeNode> generateTrees(int low, int high) {
            List<TreeNode> list = new ArrayList<>();
            if (low == high) {
                list.add(new TreeNode(low));
                return list;
            }
            if (low > high) {
                list.add(null);
                return list;
            }
            for (int i = low; i <= high; i++) {
                for (TreeNode left : generateTrees(low, i - 1)) { // 左子树
                    for (TreeNode right : generateTrees(i + 1, high)) { // 右子树
                        TreeNode root = new TreeNode(i);
                        list.add(root);
                        root.left = left;
                        root.right = right;
                    }
                }
            }
            return list;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
