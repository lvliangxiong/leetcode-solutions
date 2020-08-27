package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the
 * first k digits 0, 1, ..., k-1.
 *
 * While entering a password, the last n digits entered will automatically be matched against the correct password.
 *
 * For example, assuming the correct password is "345", if you type "012345", the box will open because the correct
 * password matches the suffix of the entered password.
 *
 * Return any password of minimum length that is guaranteed to open the box at some point of entering it.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 *
 * Example 2:
 *
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 *
 *
 *
 * Note:
 *
 *     n will be in the range [1, 4].
 *     k will be in the range [1, 10].
 *     k^n will be at most 4096.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cracking-the-safe
 * </pre>
 */
class CrackingTheSafe {


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Set<String> seen = new HashSet<>();
        StringBuilder ans = new StringBuilder();

        /**
         * 密码的每一位有 k 种选择，一共 n 位，因此密码的可能性有 k ^ n 种。
         * <p>
         * 将所有的 k 种选择的 n-1 位数，作为图的节点，每个节点有 k 条入边和 k 条出边。每条边上，有一个数字，同样是 k 种选择。
         * 如果当前节点上的数字为 a(1)a(2)....a(n-1)，那么第其 i 条出边上的数字是 i-1，因此该出边连接的节点数字是
         * a(2)....a(n-1) (i-1)。
         * <p>
         * 原问题，转化为从任意节点出发，找出经过图中所有边的一条路径，也就是『欧拉路径』问题。
         *
         * @param n
         * @param k
         * @return
         */
        public String crackSafe(int n, int k) {
            if (n == 1 && k == 1) return "0";

            // 构造起始节点
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n - 1; ++i)
                sb.append("0");
            String start = sb.toString();

            dfs(start, k);
            ans.append(start);
            return new String(ans);
        }

        public void dfs(String node, int k) {
            for (int i = 0; i < k; ++i) {
                // 遍历 k 条出边，每条边对应一个密码的最后一位数字
                String pw = node + i;
                if (!seen.contains(pw)) {
                    seen.add(pw);
                    dfs(pw.substring(1) /*该边对应的下一个节点*/, k);
                    ans.append(i);
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
