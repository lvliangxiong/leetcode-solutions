package leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <pre>
 * Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest
 * element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 *
 * Example 1:
 *
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 *
 * Example 2:
 *
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 *
 * Constraints:
 *
 *     n == matrix.length
 *     n == matrix[i].length
 *     1 <= n <= 300
 *     -10^9 <= matrix[i][j] <= -10^9
 *     All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 *     1 <= k <= n^2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 * </pre>
 */
class KthSmallestElementInASortedMatrix {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 1. 将二维数组转为一维数组后，进行排序
     * 2. 将矩阵的每一行看做一个排序的链表，尝试将这些有序链表合并，找到第 k 个数即为所求
     * 3.
     */
    class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // 小根堆
            int n = matrix.length; // matrix rows count
            for (int i = 0; i < n; i++) {
                pq.offer(new int[]{matrix[i][0] /*first element of each row*/, i/*row index*/, 0/*element index*/});
            }
            for (int i = 0; i < k - 1; i++) {
                int[] now = pq.poll(); // 每次弹出的都是矩阵中的最小值，一共弹出 k-1 次
                if (now[2] != n - 1) {
                    pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
                }
            }
            return pq.poll()[0];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class BinarySearchSolution {
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            int left = matrix[0][0]; // 矩阵中的最小值
            int right = matrix[n - 1][n - 1]; // 矩阵中的最大值
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (check(matrix, mid, k, n)) {
                    // matrix 中 <= mid 的值的数量 >= k
                    right = mid;
                } else {
                    // matrix 中 <= mid 的值的数量 < k
                    left = mid + 1;
                }
            }
            return left;
        }

        public boolean check(int[][] matrix, int mid, int k, int n) {
            // 从左下角 (n-1, 0) 开始进行搜索
            int i = n - 1;
            int j = 0;
            int count = 0; // matrix 中 <= mid 的元素的数量
            while (i >= 0 && j < n) {
                if (matrix[i][j] <= mid) {
                    count += i + 1; // 从 [0,j] 到 [i,j] 都 <= mid
                    j++;
                } else {
                    i--;
                }
            }
            return count >= k;
        }
    }

}