package leetcode.editor.cn;

/**
 * <pre>
 * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
 *
 *     B.length >= 3
 *     There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 *
 * (Note that B could be any subarray of A, including the entire array A.)
 *
 * Given an array A of integers, return the length of the longest mountain.
 *
 * Return 0 if there is no mountain.
 *
 * Example 1:
 *
 * Input: [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 *
 * Example 2:
 *
 * Input: [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 *
 * Note:
 *
 *     0 <= A.length <= 10000
 *     0 <= A[i] <= 10000
 *
 * Follow up:
 *
 *     Can you solve it using only one pass?
 *     Can you solve it in O(1) space?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-mountain-in-array
 * </pre>
 */
class LongestMountainInArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestMountain(int[] A) {
            int start = 0, end = 0, n = A.length, max = 0;
            if (n < 3) return 0;
            while (start != n - 1) {
                while (end < n - 1 && A[end] < A[end + 1]) end++; // 上升
                int peek = end; // 顶点
                while (end < n - 1 && A[end] > A[end + 1]) end++; // 下降
                if (start < peek && peek < end) max = Math.max(end - start + 1, max); // 更新 max
                // flat case
                while (end < n - 1 && A[end] == A[end + 1]) end++;
                start = end;
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
