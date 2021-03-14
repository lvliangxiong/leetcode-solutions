package leetcode.editor.cn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi]
 * and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer
 * for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and
 * that there is no contradiction.
 *
 * Example 1:
 *
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * Example 2:
 *
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 *
 * Example 3:
 *
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 *
 * Constraints:
 *
 *     1 <= equations.length <= 20
 *     equations[i].length == 2
 *     1 <= Ai.length, Bi.length <= 5
 *     values.length == equations.length
 *     0.0 < values[i] <= 20.0
 *     1 <= queries.length <= 20
 *     queries[i].length == 2
 *     1 <= Cj.length, Dj.length <= 5
 *     Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/evaluate-division
 * </pre>
 */
class EvaluateDivision {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * key : 当前节点（方程的分母）
         * value : 当前节点的父节点（方程的分子）
         * 和 weights 始终维护这样一个关系：parents(key) / key  = weights(key)
         */
        private Map<String, String> parents = new HashMap<>();
        /**
         * key : 当前节点
         * value : 当前节点的父节点/当前节点
         */
        private Map<String, Double> weights = new HashMap<>();

        /**
         * 并查集（Union-find Sets）是一种树型的数据结构，用于处理一些不相交集合（Disjoint Sets）的合并及查询问题。
         * 常常在使用中以森林来表示。
         * <p>
         * 对于具有 a1 / a2 = k 关系的两个变量，可以认为是同一棵树中的两个节点，其中 a1 为父节点，a2 为子节点。这种父子关系
         * 可以使用一个 Map 进行表示。父子节点之间的商可以使用树状结构中的边的权值表示。
         *
         * @param equations
         * @param values
         * @param queries
         * @return
         */
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            for (int i = 0; i < equations.size(); i++) {
                union(equations.get(i).get(0) /*分子*/, equations.get(i).get(1)/*分母*/, values[i]/*商*/);
            }
            double[] result = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                result[i] = find(queries.get(i));
            }
            return result;
        }

        private void union(String parent, String child, double weight) {
            add(parent);
            add(child);
            /* Create relationship between the roots of given nodes, not nodes themselves.
             * Therefore, multiple parents relationship is trivial, such as a/b = m, c/b = n which means b has two
             * parents, but after union the relationship between c and b will become the relationship between c and
             * a = root(b). */
            String rp = root(parent);
            String rc = root(child);
            if (!rp.equals(rc)) { // 保证 parent 和 child 不在同一棵树内，否则这个操作就是无意义的
                /*
                 * rp / parent = pm(parent)
                 * rc / child = pm(child)
                 * ===>
                 * rp / rc = [pm(parent) * parent] / [pm(child) * child]
                 *         = [pm(parent) / pm(child)] * [parent / child]
                 *         = [pm(parent) / pm(child)] * weight
                 * */
                update(rp, rc, weight * (pm(parent) / pm(child)));
            }
        }

        private void update(String parent, String child, double weight) {
            parents.put(child, parent);
            weights.put(child, weight);
        }

        private double find(List<String> query) {
            String numerator = query.get(0); // 分子
            String denominator = query.get(1); // 分母
            if (!parents.containsKey(numerator) || !parents.containsKey(denominator)) return -1.0;
            if (numerator.equals(denominator)) return 1;
            String rn = root(numerator);
            String rd = root(denominator);
            // 如果两者不相等，说明两个节点不在同一个集合（同一棵树）中
            if (!rn.equals(rd)) {
                return -1.0;
            }
            // 在同一棵树中
            double pmd = pm(denominator);
            double pmn = pm(numerator);
            // path suppression to accelerate the next query
            update(rn, numerator, pmn);
            update(rd, denominator, pmd);
            /*
             * pmd = root(denominator) / denominator
             * pmn = root(numerator) / numerator
             * root(denominator) = root(numerator)
             * ===>
             * numerator * pmn = denominator * pmd
             * ===>
             * numerator / denominator =  pmd / pmn
             * */
            return pmd / pmn;
        }

        /**
         * 创建一个指向自身的 mapping，并设置此 mapping 的权值为 1。
         * <p>
         * 此方法保证了所有的节点，无论是子节点还是父节点在 Map 中均有相应的 key，方便了后续判断某个节点是否存在于『并查集』中。
         * 值得注意的是这个操作放入的数据，后续有很大一部分都会被覆盖。
         *
         * @param x
         */
        private void add(String x) {
            if (!parents.containsKey(x)) {
                parents.put(x, x);
                weights.put(x, 1.0);
            }
        }

        private String root(String x) {
            while (!parents.get(x).equals(x)) {
                x = parents.get(x);
            }
            return x;
        }

        /**
         * Path multiplication from x's root to x.
         * <p>
         * 即 root(x) / x 的值。
         *
         * @param x
         * @return
         */
        private double pm(String x) {
            double v = 1;
            while (!parents.get(x).equals(x)) {
                v *= weights.get(x);
                x = parents.get(x);
            }
            return v;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 使用单独的数据结构进行实现！
     */
    class UnionFindSetSolution {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            int equationsSize = equations.size();
            UnionFindSet unionFindSet = new UnionFindSet(2 * equationsSize); // 最多存在 2 * equationsSize 个变量（节点）

            // 第 1 步：预处理，将变量的名称与 id 进行映射，使得并查集的底层使用数组实现，方便编码
            Map<String, Integer> mapping = new HashMap<>(2 * equationsSize);
            int id = 0;
            for (int i = 0; i < equationsSize; i++) {
                List<String> equation = equations.get(i);
                String var1 = equation.get(0); // 分子
                String var2 = equation.get(1); // 分母

                if (!mapping.containsKey(var1)) {
                    mapping.put(var1, id);
                    id++;
                }
                if (!mapping.containsKey(var2)) {
                    mapping.put(var2, id);
                    id++;
                }
                // union 操作
                unionFindSet.union(mapping.get(var1), mapping.get(var2), values[i]);
            }

            // 第 2 步：做查询
            int queriesSize = queries.size();
            double[] res = new double[queriesSize];
            for (int i = 0; i < queriesSize; i++) {
                String var1 = queries.get(i).get(0); // 分子
                String var2 = queries.get(i).get(1); // 分母

                Integer id1 = mapping.get(var1);
                Integer id2 = mapping.get(var2);

                if (id1 == null || id2 == null) {
                    res[i] = -1.0d;
                } else {
                    res[i] = unionFindSet.compute(id1, id2);
                }
            }
            return res;
        }
    }

    /**
     * 并查集经常用来处理有传递性关系的问题！
     */
    class UnionFindSet {

        private int[] parent;

        /**
         * 指向的父结点的权值
         */
        private double[] weight;

        public UnionFindSet(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        public void union(int x, int y, double value) {
            // find 操作会进行路径压缩，因此后续用到 weight[x] 和 weight[y] 时，不用考虑树的高度超过 2 的问题。
            int rootX = find(x);
            int rootY = find(y);
            // x 和 y 在同一个集合中，则不需要 union
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 查询找到 id 为 x 的节点的 root 节点的 id，在这个过程当中进行路径压缩，
         * 使得 x 到 root 路径上的每一个节点都直接和 root 相连。因此可以使用递归。
         */
        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double compute(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                // 在同一颗树上
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }
}
