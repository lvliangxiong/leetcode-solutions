package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Given an array equations of strings that represent relationships between variables, each string equations[i] has
 * length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not
 * necessarily different) that represent one-letter variable names.
 *
 * Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given
 * equations.
 *
 *
 * Example 1:
 *
 * Input: ["a==b","b!=a"]
 * Output: false
 * Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
 * There is no way to assign the variables to satisfy both equations.
 *
 * Example 2:
 *
 * Input: ["b==a","a==b"]
 * Output: true
 * Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 *
 * Example 3:
 *
 * Input: ["a==b","b==c","a==c"]
 * Output: true
 *
 * Example 4:
 *
 * Input: ["a==b","b!=c","c==a"]
 * Output: false
 *
 * Example 5:
 *
 * Input: ["c==c","b==d","x!=z"]
 * Output: true
 *
 * Note:
 *
 *     1 <= equations.length <= 500
 *     equations[i].length == 4
 *     equations[i][0] and equations[i][3] are lowercase letters
 *     equations[i][1] is either '=' or '!'
 *     equations[i][2] is '='
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
 * </pre>
 */
class SatisfiabilityOfEqualityEquations {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean equationsPossible(String[] equations) {
            Map<Character, Integer> mapping = new HashMap<>();
            int length = equations.length;
            UnionFindSet unionFindSet = new UnionFindSet(length << 1);
            int id = 0;
            for (int i = 0; i < length; i++) {
                String equation = equations[i];
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                if (!mapping.containsKey(x)) mapping.put(x, id++);
                if (!mapping.containsKey(y)) mapping.put(y, id++);
                if (equation.charAt(1) == '=')
                    // 相等的变量属于同一棵树
                    unionFindSet.union(mapping.get(x), mapping.get(y));
            }
            // 对不相等的条件进行校验
            for (int i = 0; i < length; i++) {
                String equation = equations[i];
                if (equation.charAt(1) == '!') {
                    char x = equation.charAt(0);
                    char y = equation.charAt(3);
                    if (mapping.containsKey(x) && mapping.containsKey(y) && unionFindSet.find(mapping.get(x)) ==
                            unionFindSet.find(mapping.get(y))) return false;
                }
            }
            return true;
        }
    }

    /**
     * 相等关系，是一种『可以传递』的关系，因此可以使用『并查集』进行表示，同一颗树上的节点都是相等的！
     * 相反的，不想等关系，则不是一种传递性关系。适合用来做最后的校验！
     */
    class UnionFindSet {
        int[] parents;
        boolean[] equals;

        public UnionFindSet(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public void union(int x, int y) {
            // find 会自动压缩路径，保证了 x 到 rootX 最多只有一条边，y 到 rootY 也是如此！
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            else parents[rootX] = rootY;
        }

        /**
         * 查找根节点，并进行路径压缩！
         *
         * @param x
         * @return
         */
        public int find(int x) {
            if (parents[x] != x) {
                int root = find(parents[x]);
                parents[x] = root;
            }
            return parents[x];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}