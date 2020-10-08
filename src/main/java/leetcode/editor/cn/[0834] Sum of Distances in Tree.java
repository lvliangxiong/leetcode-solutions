package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.
 *
 * The ith edge connects nodes edges[i][0] and edges[i][1] together.
 *
 * Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.
 *
 * Example 1:
 *
 * Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation:
 * Here is a diagram of the given tree:
 *   0
 *  / \
 * 1   2
 *    /|\
 *   3 4 5
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
 *
 * Note: 1 <= N <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-distances-in-tree
 * </pre>
 */
class SumOfDistancesInTree {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] ans;
        /**
         * treeNodeNum[i] 表示以节点 i 为根节点的子树中节点的数量（包括自身）
         */
        int[] treeNodeNum;
        /**
         * dp[i] 表示以节点 i 为根节点的子树，它的根节点到其它所有后代节点的距离之和，这个值与树的形态有关
         */
        int[] dp;
        List<List<Integer>> graph;

        public int[] sumOfDistancesInTree(int N, int[][] edges) {
            ans = new int[N];
            treeNodeNum = new int[N];
            dp = new int[N];
            graph = new ArrayList<>();
            // 建立邻接表 graph
            for (int i = 0; i < N; ++i) {
                graph.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1];
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            dfs(0, -1);
            dfs2(0, -1);
            return ans;
        }

        /**
         * 以 u 为根节点，计算一次 dp 数组：
         * dp[u] = sum(dp[v] + sz[v]), v 是 u 的所有子节点，sz[v] 代表以 v 为根节点的子树的节点总数
         *
         * @param u
         * @param father 搜索到节点 u 时，其父节点的序号
         */
        public void dfs(int u, int father) {
            treeNodeNum[u] = 1;
            dp[u] = 0; // 初始化为 0
            for (int v : graph.get(u)) { // 遍历与 u 节点相邻的所有节点 v
                if (v == father) {
                    // 防止又搜索回父节点，出现死循环
                    continue;
                }
                dfs(v, u);
                dp[u] += dp[v] + treeNodeNum[v]; // 递推关系
                treeNodeNum[u] += treeNodeNum[v];
            }
        }

        /**
         * 利用已有的 dp 数组，计算出以其它节点为根的 dp 数组
         *
         * @param u
         * @param father
         */
        public void dfs2(int u, int father) {
            ans[u] = dp[u]; // 保存换根之前的结果
            for (int v : graph.get(u)) { // 遍历 u 的所有子节点，并进行该子节点和根节点的换根操作
                if (v == father) {
                    continue;
                }

                // 换根前后：
                // 对 dp 数组来说，仅仅会变化 dp[u] 和 dp[v]，这里先用 pu 和 pv 保存旧值
                // 对 treeNodeNum 来说，也仅仅会变化 treeNodeNum[u] 和 treeNodeNum[v]，这里先用 su 和 sv 保存旧值
                int pu = dp[u], pv = dp[v];
                int su = treeNodeNum[u], sv = treeNodeNum[v];

                // 计算以 v 为根时，dp 和 treeNodeNum 数组
                // 换根之后，以 u 为根节点的子树，少了原先 v 这个子节点，而 v 这个节点则多了 u 这个子节点
                dp[u] -= dp[v] + treeNodeNum[v];
                treeNodeNum[u] -= treeNodeNum[v];
                dp[v] += dp[u] + treeNodeNum[u];
                treeNodeNum[v] += treeNodeNum[u];

                // 此时 dp 和 treeNodeNum 均为以 v 为根节点时计算出来的值，
                // 因此可以用于计算以 v 的子节点为根时，dp 和 treeNodeNum 的值
                dfs2(v, u);

                // 恢复，继续进行下一个子节点的换根操作
                dp[u] = pu;
                dp[v] = pv;
                treeNodeNum[u] = su;
                treeNodeNum[v] = sv;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
