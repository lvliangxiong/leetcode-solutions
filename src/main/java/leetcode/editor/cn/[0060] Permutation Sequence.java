package leetcode.editor.cn;

import java.util.Arrays;

/**
 * <pre>
 * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
 *
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 *
 *     "123"
 *     "132"
 *     "213"
 *     "231"
 *     "312"
 *     "321"
 *
 * Given n and k, return the kth permutation sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 3
 * Output: "213"
 *
 * Example 2:
 *
 * Input: n = 4, k = 9
 * Output: "2314"
 *
 * Example 3:
 *
 * Input: n = 3, k = 1
 * Output: "123"
 *
 *
 *
 * Constraints:
 *
 *     1 <= n <= 9
 *     1 <= k <= n!
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 * </pre>
 */
class PermutationSequence {


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String getPermutation(int n, int k) {
            int[] factorial = new int[n];
            boolean[] selected = new boolean[n];
            Arrays.setAll(factorial, i -> i == 0 ? 1 : i * factorial[i - 1]);
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) { // 确定第 i 位
                for (int j = 1; j <= n; j++) { // 遍历可选的数字
                    if (!selected[j - 1]) {
                        if (k < factorial[n - i]) {
                            selected[j - 1] = true;
                            sb.append(j);
                            break;
                        } else if (k == factorial[n - i]) {
                            // 加速
                            selected[j - 1] = true;
                            sb.append(j);
                            for (int m = n; m >= 1; m--) {
                                if (!selected[m - 1]) sb.append(m);
                            }
                            return sb.toString();
                        } else {
                            k -= factorial[n - i];
                        }
                    }
                }
            }
            return sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}