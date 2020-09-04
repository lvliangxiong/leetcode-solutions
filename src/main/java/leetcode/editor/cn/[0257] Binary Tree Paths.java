package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Output: ["1->2->5", "1->3"]
 *
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * </pre>
 */
class BinaryTreePaths {

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
        List<String> ans = new ArrayList<>();

        public List<String> binaryTreePaths(TreeNode root) {
            if (root != null) backtrack(root, new StringBuilder());
            return ans;
        }

        private void backtrack(TreeNode root, StringBuilder sb) {
            int oldLen = sb.length();
            sb.append(root.val);
            if (root.left != null) {
                sb.append("->");
                backtrack(root.left, sb);
                sb.setLength(sb.length() - 2);
            }
            if (root.right != null) {
                sb.append("->");
                backtrack(root.right, sb);
                sb.setLength(sb.length() - 2);
            }
            if (root.left == null && root.right == null) ans.add(sb.toString());
            sb.setLength(oldLen);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
