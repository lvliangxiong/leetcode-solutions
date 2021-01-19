package leetcode.editor.cn;

import java.util.Arrays;

/**
 * <pre>
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's
 * in their binary representation and return them as an array.
 *
 * Example 1:
 *
 * Input: 2
 * Output: [0,1,1]
 *
 * Example 2:
 *
 * Input: 5
 * Output: [0,1,1,2,1,2]
 *
 * Follow up:
 *
 *     It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time
 *     O(n) /possibly in a single pass?
 *     Space complexity should be O(n).
 *     Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any
 *     other language.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * </pre>
 */
class CountingBits {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] countBits(int num) {
            int[] result = new int[num >= 4 ? num + 1 : 4];
            result[0] = 0;
            result[1] = 1;
            result[2] = 1;
            result[3] = 2;
            for (int start = 4; start <= num; ) {
                // cur 遍历 [start, 2*start-1]，而 pre 遍历 [0, start-1]
                int cur = start, pre = 0;
                int end = start << 1;
                while (cur < end && cur <= num) {
                    result[cur] = result[pre] + 1;
                    cur++;
                    pre++;
                }
                start = end;
                if (cur > num) break;
            }
            return num >= 3 ? result : Arrays.copyOf(result, num + 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}