package leetcode.editor.cn;

import leetcode.editor.cn.tree.TreeNode;

class DistributeCoinsInBinaryTree {

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
        int ans;

        /**
         * 此问题可以从底向上进行分配，先整理底层的节点：如果底层的节点硬币少了，肯定需要向上面的节点借，如果底层的节点硬币多了，这些
         * 硬币也肯定要上交借给别的节点。直到回溯到根节点时，所有的节点都整理完毕。
         * <p>
         * 对于一个叶子节点：
         * 1. 如果节点上的硬币数为 0，那么它需要从它的父节点移动一个硬币过来，因此 ans 需要 +1
         * 2. 如果节点上的硬币数为 1，那么 ans 不变
         * 3. 如果节点上的硬币数大于 1，那么它需要移动多余硬币给它的父节点，如果该节点的硬币数为 n，那么它对 ans 的贡献就是 +(n-1)
         * 综上所述，当我们从子节点（该位置的硬币数量为 n）向上回溯时，对 ans 的贡献就是 +abs(1-n)。
         * <p>
         * 注意回溯后，父节点需要更新其节点上的硬币数量，因为它需要借给子节点硬币，或者收到子节点的硬币，而自身只需要 1 个，多余的都是
         * 要移动到别处的。
         * <p>
         * 这里需要理解的就是：如果向父节点借硬币，但是父节点又没有硬币，那么回溯的过程会一直追溯到有硬币的根节点为止（这个有硬币的根
         * 节点的硬币也可能是其有硬币比较多的子节点贡献的）。并且由于硬币总数就等于节点数量，最后总能保证每个节点都有一个硬币。
         *
         * @param root
         * @return
         */
        public int distributeCoins(TreeNode root) {
            dfs(root);
            return ans;
        }

        /**
         * 返回该子树需要多少个 Coin，如果结果为负数，表示有多余的 Coin
         *
         * @param root
         * @return
         */
        private int dfs(TreeNode root) {
            if (root == null) return 0;
            int lNeed = dfs(root.left); // 左子树需要的硬币数量
            int rNeed = dfs(root.right); // 右子树需要的硬币数量
            int need = 1 - (root.val - lNeed - rNeed); // root 节点需要向上借的硬币数量
            ans += Math.abs(need);
            return need;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
