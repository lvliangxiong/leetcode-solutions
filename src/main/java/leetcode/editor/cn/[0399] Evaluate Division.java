package leetcode.editor.cn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real
 * number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries,
 * where equations.size() == values.size(), and the values are positive. This represents the equations.
 * Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 *
 *
 *
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there
 * is no contradiction.
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
                union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
            }
            double[] result = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                result[i] = find(queries.get(i));
            }
            return result;
        }

        private double find(List<String> query) {
            String numerator = query.get(0); // 分子
            String denominator = query.get(1); // 分母
            if (!parents.containsKey(numerator) || !parents.containsKey(denominator)) return -1.0;
            if (numerator.equals(denominator)) return 1;
            String rn = root(numerator);
            String rd = root(denominator);
            if (!rn.equals(rd)) {
                // 如果两者不相等，说明两个节点不在同一个集合（同一棵树）中
                return -1.0;
            }
            double pmd = pm(denominator);
            double pmn = pm(numerator);
            // path suppression to accelerate the next query
            update(rn, numerator, pmn);
            update(rd, denominator, pmd);
            return pmd / pmn;
        }

        private void update(String parent, String child, double weight) {
            parents.put(child, parent);
            weights.put(child, weight);
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
            if (!rp.equals(rc)) {
                // 保证 parent 和 child 不在同一棵树内，否则这个操作就是无意义的
                update(rp, rc, weight * (pm(parent) / pm(child)));
            }
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

}
