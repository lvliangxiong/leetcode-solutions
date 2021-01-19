package leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of this person who have a height greater
 * than or equal to h. Write an algorithm to reconstruct the queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 *
 * Example
 *
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * </pre>
 */
class QueueReconstructionByHeight {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] reconstructQueue(int[][] people) {
            // 按照身高进行降序排列，身高相同则按照 k 进行升级序排列
            Arrays.sort(people, (a1, a2) -> a1[0] == a2[0] ? a1[1] - a2[1] : a2[0] - a1[0]);

            List<int[]> output = new LinkedList<>();
            for (int[] p : people) {
                output.add(p[1], p);
            }
            int n = people.length;
            return output.toArray(new int[n][2]);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}