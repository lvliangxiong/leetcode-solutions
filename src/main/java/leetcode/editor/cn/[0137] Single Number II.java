package leetcode.editor.cn;

/**
 * <pre>
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once.
 * Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,3,2]
 * Output: 3
 *
 * Example 2:
 *
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-ii
 * </pre>
 */
class SingleNumberIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 1. counting and find out
         * 2. sorting and find out
         * 3. bit manipulation
         * <p>
         * 统计所有数字在每一位（共 32 位）上的 1 的个数，这个次数要么是 3k+1 或者是 3k，而出现 3k+1 次的位组合起来就是我们需要
         * 的 target。
         *
         * @param nums
         * @return
         */
        public int singleNumber(int[] nums) {
            int ans = 0;
            // 考虑每一位
            for (int i = 0; i < 32; i++) {
                int count = 0;
                // 考虑每一个数
                for (int j = 0; j < nums.length; j++) {
                    // 当前位是否是 1
                    if ((nums[j] >>> i & 1) == 1) {
                        count++;
                    }
                }
                // 1 的个数是否是 3 的倍数
                if (count % 3 != 0) {
                    ans = ans | 1 << i;
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class OptimisedSolution {
        public int singleNumber(int[] nums) {
            /* 我们仅仅关心每一位上 1 出现的次数是不是 3 的倍数。因此可以对于每一位，使用一个 2 bit 表示数据已经出现的次数。
             *
             * 1. 对于二进制位为 1 的情况，出现 0 次，记为 00，出现 1 次，记为 01，出现 2 次，记为 10，出现 3 次，重新记为 00。
             * 2. 二进制位为 0 直接忽略
             *
             * 32 位的 int 类型，需要 64 位进行统计，因此我们可以使用两个 int 或者一个 long 类型。
             *
             * 使用 A 和 B 代替这两个二进制位，C 为新加入统计的二进制位，那么可以作出真值表如下：
             *
             *      A       B           C               Anew    Bnew
             *      0       0           0                0       0
             *      0       1           0                0       1
             *      1       0           0                1       0

             *      0       0           1                0       1
             *      0       1           1                1       0
             *      1       0           1                0       0
             *
             * 利用逻辑代数的方法，我们可以很简单的写出逻辑函数：
             *      Anew = AB'C' + A'BC
             *      Bnew = A'BC' + A'B'C
             * 亦或的定义如下：
             *      A ^ B = A'B + AB'
             * 因此我们可以将上述方程进行化简：
             *      Bnew = A'(B ^ C)
             *      Anew = Bnew'(A ^ C)
             * */
            int A = 0, B = 0;
            for (int num : nums) {
                B = (~A) & (B ^ num);
                A = (~B) & (A ^ num);

                /*
                // 以下的方式和上述的方式是相同的，只不过上述的变换是化简后的
                int newA = (A & (~B) & (~num)) | ((~A) & B & num);
                int newB = ((~A) & B & (~num)) | ((~A) & (~B) & num);
                A = newA;
                B = newB;
                */

            }
            return B;
        }
    }

}
