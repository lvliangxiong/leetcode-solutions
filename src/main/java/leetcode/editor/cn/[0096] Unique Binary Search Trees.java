package leetcode.editor.cn;

/**
 * <pre>
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *
 * Example:
 *
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *
 *
 * Constraints:
 *
 *     1 <= n <= 19
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * </pre>
 */
class UniqueBinarySearchTrees {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numTrees(int n) {
            if (n <= 1) return 1;
            if (n == 2) return 2;
            if (n == 3) return 5;
            int count = 0;
            for (int i = 1; i <= n; i++) {
                count += numTrees(i - 1) * numTrees(n - i);
            }
            return count;
        }

        public int numTreesIterative(int n) {
            int[] ret = new int[n > 3 ? n + 1 : 4];
            ret[0] = 1;
            ret[1] = 1;
            ret[2] = 2;
            ret[3] = 5;
            for (int i = 4; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    // [1, j-1]  j  [j+1, i]
                    ret[i] += ret[j - 1] * ret[i - j];
                }
            }
            return ret[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
