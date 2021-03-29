package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

class HouseRobberIii {
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
         * 保存中间结果，key 为树中的节点，而 value 是一个整形数组。
         * [0] 代表在以 key 为根节点的树中，不 rob 根节点能获得的最大收益。
         * [1] 代表在以 key 为根节点的树中，rob 根节点能获得的最大收益。
         */
        Map<TreeNode, int[]> memo = new HashMap<>();

        /**
         * dp[root][1] = root.val + (dp[root.left][0] + dp[root.right][0])
         * dp[root][0] = max(dp[root.left][0] + dp[root.left][1]) + max(dp[root.right][0], dp[root.right][1])
         * where 1 represents rob the house, 0 represents not.
         * <p>
         * 『记忆化递归』实现！
         *
         * @param root
         * @return
         */
        public int rob(TreeNode root) {
            return Math.max(rob(root, true), rob(root, false));
        }

        private int rob(TreeNode root, boolean robTheRoot) {
            if (root == null) {
                return 0;
            }
            if (memo.containsKey(root)) {
                int[] ret = memo.get(root);
                return robTheRoot ? ret[1] : ret[0];
            } else {
                int r = root.val + rob(root.left, false) + rob(root.right, false);
                int nr = Math.max(rob(root.left, true), rob(root.left, false)) +
                        Math.max(rob(root.right, true), rob(root.right, false));
                memo.put(root, new int[]{nr, r});
                return robTheRoot ? r : nr;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * O(n)，相当于同时返回了两种情况，即包含了 rob，也包含了 notRob 的情况！
     */
    class RecursionSolution {
        public int rob(TreeNode root) {
            int[] rootStatus = helper(root);
            return Math.max(rootStatus[0], rootStatus[1]);
        }

        public int[] helper(TreeNode node) {
            if (node == null) {
                return new int[]{0, 0};
            }
            int[] l = helper(node.left);
            int[] r = helper(node.right);
            int selected = node.val + l[1] + r[1];
            int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
            return new int[]{selected, notSelected};
        }
    }

    /**
     * 后序遍历实现
     */
    class PostorderSolution {
        public int rob(TreeNode root) {
            if (root == null) {
                return 0;
            }
            postorder(root);
            return root.val;
        }

        public void postorder(TreeNode root) {
            if (root.left != null) {
                postorder(root.left);
            }
            if (root.right != null) {
                postorder(root.right);
            }
            int nr = 0; // not rob the root
            int r = root.val; // rob the root
            if (root.left != null) {
                nr = nr + root.left.val;
                if (root.left.left != null) {
                    r = r + root.left.left.val;
                }
                if (root.left.right != null) {
                    r = r + root.left.right.val;
                }
            }
            if (root.right != null) {
                nr = nr + root.right.val;
                if (root.right.left != null) {
                    r = r + root.right.left.val;
                }
                if (root.right.right != null) {
                    r = r + root.right.right.val;
                }
            }
            root.val = Math.max(nr, r);
        }
    }
}
