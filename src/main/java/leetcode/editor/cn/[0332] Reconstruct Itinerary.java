package leetcode.editor.cn;

import java.util.*;

/**
 * <pre>
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the
 * itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 *
 * Note:
 *
 *     If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order
 *     when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
 *     ["JFK", "LGB"].
 *     All airports are represented by three capital letters (IATA code).
 *     You may assume all tickets form at least one valid itinerary.
 *     One must use all the tickets once and only once.
 *
 * Example 1:
 *
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * Example 2:
 *
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reconstruct-itinerary
 * </pre>
 */
class ReconstructItinerary {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 如果图 G 中的一个路径 path 包括图 G 的每一条边恰好一次，那么该路径则被称为『欧拉路径』，即 Euler Path。
     * 如果图 G 中的一个回路（闭合的路径）是『欧拉路径』，该回路则被称为『欧拉回路』，即 Euler Circuit。
     * 具有欧拉回路的图称为『欧拉图』，简称 E 图，具有欧拉路径但不具有欧拉回路的图称为『半欧拉图』。
     * 图 G 中任意两个节点 x 和 y 之间均存在『路径』相连，那么则称 G 为『连通图』。
     * <p>
     * 无向图存在『欧拉回路』的充分必要条件：该图的所有节点的度数均为偶数，并且该图为连通图。
     * 有向图存在『欧拉回路』的充分必要条件：该图的所有节点的入度等于出度，并且该图为联通图。
     */
    class Solution {
        /**
         * 使用一个 Map 和 PriorityQueue 的复合数据结构，存储这个图的节点和边。
         */
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        List<String> itinerary = new LinkedList<>();

        /**
         * 本题的其实就是在一个 n 个节点，m 条边的图中，要求从指定的节点出发，经过所有的边恰好一次，并且节点的『字典序』最小。
         * <p>
         * 这个问题本质上就是一个求解『欧拉路径』的问题。而且本问题保证了机票形成的有向图是存在『欧拉路径』的，因此该图至少是一个
         * 『半欧拉图』。
         * <p>
         * 使用 Hierholzer 算法用于在联通图中寻找『欧拉路径』：
         * 1. 从起点出发，进行 DFS
         * 2. 沿着某条边从一个节点移动到下一个节点时，都需要删除这条边
         * 2. 如果没有可移动的路径，则将所在的节点加入到栈中，并返回
         *
         * @param tickets
         * @return
         */
        public List<String> findItinerary(List<List<String>> tickets) {
            for (List<String> ticket : tickets) {
                String src = ticket.get(0), dst = ticket.get(1);
                if (!map.containsKey(src)) {
                    map.put(src, new PriorityQueue<>());
                }
                map.get(src).offer(dst); // 机场名称字典序较小的排在前面
            }
            dfs("JFK");
            Collections.reverse(itinerary);
            return itinerary;
        }

        public void dfs(String cur) {
            while (map.containsKey(cur) && map.get(cur).size() > 0) {
                String next = map.get(cur).poll(); // 机场名称字典序较小的先出队
                dfs(next);
            }
            itinerary.add(cur);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
