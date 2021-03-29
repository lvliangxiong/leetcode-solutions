package leetcode.editor.cn;

/**
 * <pre>
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * Note:
 *
 *     Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output
 *     will be given as a signed integer type. They should not affect your implementation, as the integer's internal
 *     binary representation is the same, whether it is signed or unsigned.
 *
 *     In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2
 *     above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 *
 * Follow up:
 *
 * If this function is called many times, how would you optimize it?
 *
 *
 * Example 1:
 *
 * Input: n = 00000010100101000001111010011100
 * Output:    964176192 (00111001011110000010100101000000)
 * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596,
 * so return 964176192 which its binary representation is 00111001011110000010100101000000.
 *
 * Example 2:
 *
 * Input: n = 11111111111111111111111111111101
 * Output:   3221225471 (10111111111111111111111111111111)
 * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293,
 * so return 3221225471 which its binary representation is 10111111111111111111111111111111.
 *
 *
 * Constraints:
 *
 *     The input must be a binary string of length 32
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-bits
 * </pre>
 */
class ReverseBits {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int rev = 0;
            for (int i = 0; i < 32 && n != 0; ++i) {
                rev |= (n & 1) << (31 - i);
                n >>>= 1;
            }
            return rev;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    public class SwapSolution {
        public int reverseBits(int n) {
            int low = 0, high = 31;
            while (low < high) {
                int maskLow = 1 << low;
                int maskHigh = 1 << high;
                if ((n & maskLow) >>> low != (n & maskHigh) >>> high) {
                    n ^= maskLow;
                    n ^= maskHigh;
                }
                low++;
                high--;
            }
            return n;
        }
    }

    public class DivideAndConquerSolution {
        private static final int M1 = 0x55555555; // 01 01 01 01 01 01 01 01 01 01 01 01 01 01 01 01
        private static final int M2 = 0x33333333; // 00 11 00 11 00 11 00 11 00 11 00 11 00 11 00 11
        private static final int M4 = 0x0f0f0f0f; // 00 00 11 11 00 00 11 11 00 00 11 11 00 00 11 11
        private static final int M8 = 0x00ff00ff; // 00 00 00 00 11 11 11 11 00 00 00 00 11 11 11 11

        public int reverseBits(int n) {
            n = n >>> 1 & M1 | (n & M1) << 1;
            n = n >>> 2 & M2 | (n & M2) << 2;
            n = n >>> 4 & M4 | (n & M4) << 4;
            n = n >>> 8 & M8 | (n & M8) << 8;
            return n >>> 16 | n << 16;
        }
    }

}