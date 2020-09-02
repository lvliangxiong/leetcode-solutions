package leetcode.editor.cn;

import leetcode.editor.cn.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent
 * nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Example:
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 */
class PathSumIii {

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
        int ans = 0;

        public int pathSum(TreeNode root, int sum) {
            if (root != null) dfs(root, sum);
            return ans;
        }

        /**
         * 返回给定 root 节点的所有子节点到该 root 节点的路径和集合
         *
         * @param root
         * @param sum
         * @return
         */
        private List<Integer> dfs(TreeNode root, int sum) {
            if (root.val == sum) ans++;
            int target = sum - root.val;
            List<Integer> list = new ArrayList<Integer>() {{
                add(root.val);
            }};
            if (root.left != null) {
                for (Integer le : dfs(root.left, sum)) {
                    if (le == target) ans++;
                    list.add(le + root.val);
                }
            }
            if (root.right != null) {
                for (Integer ri : dfs(root.right, sum)) {
                    if (ri == target) ans++;
                    list.add(ri + root.val);
                }
            }
            return list;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class PrefixSumSolution {
        int ans = 0;

        /**
         * 前缀和之差得到区间和
         *
         * @param root
         * @param sum
         * @return
         */
        public int pathSum(TreeNode root, int sum) {
            if (root != null) {
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                dfs(root, 0, sum, map);
            }
            return ans;
        }

        /**
         * @param curr
         * @param sum
         * @param target
         * @param prefixSumMap 存放了 root 到『root 到 curr 的路径上的所有节点』的路径和（前缀和）
         * @return
         */
        private void dfs(TreeNode curr, int sum, int target, Map<Integer, Integer> prefixSumMap) {
            if (curr != null) {
                sum += curr.val; // root 到 curr 的路径和为 sum
                ans += prefixSumMap.getOrDefault(sum - target, 0);
                prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0) + 1); // 更新『前缀和 Map』
                if (curr.left != null) dfs(curr.left, sum, target, prefixSumMap);
                if (curr.right != null) dfs(curr.right, sum, target, prefixSumMap);
                prefixSumMap.put(sum, prefixSumMap.get(sum) - 1);
            }
        }
    }
}
