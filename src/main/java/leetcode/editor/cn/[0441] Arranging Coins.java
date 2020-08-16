package leetcode.editor.cn;

/**
 * <pre>
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have
 * exactly k coins.
 *
 * Given n, find the total number of full staircase rows that can be formed.
 *
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 *
 * Example 1:
 *
 * n = 5
 *
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤
 *
 * Because the 3rd row is incomplete, we return 2.
 *
 * Example 2:
 *
 * n = 8
 *
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 *
 * Because the 4th row is incomplete, we return 3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arranging-coins
 * </pre>
 */
class ArrangingCoins {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int arrangeCoins(int n) {
            int ans = (int) Math.sqrt(2.0 * n);
            return (ans * (ans + 1) >>> 1 <= n) ? ans : ans - 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class BinarySearchSolution {
        public int arrangeCoins(int n) {
            int count = 0;
            while (n >= 0) { // 当还余有硬币时，循环
                count++;
                n -= count;       // 现有数目减去该层所需数目为余下硬币数目
            }
            return count - 1;
        }
    }
}
