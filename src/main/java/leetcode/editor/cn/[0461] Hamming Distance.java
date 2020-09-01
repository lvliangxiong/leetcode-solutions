package leetcode.editor.cn;

/**
 * <pre>
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, calculate the Hamming distance.
 *
 * Note:
 * 0 ≤ x, y < 231.
 *
 * Example:
 *
 * Input: x = 1, y = 4
 *
 * Output: 2
 *
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * The above arrows point to positions where the corresponding bits are different.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hamming-distance
 * </pre>
 */
class HammingDistance {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int hammingDistance(int x, int y) {
            int ret = x ^ y;
            // 计算 ret 的二进制表示中，1 的位数即可
            int z = 1, count = 0;
            for (int i = 0; i < 32; i++) {
                if ((z & ret) != 0) count++;
                z <<= 1;
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
