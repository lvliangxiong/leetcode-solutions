package leetcode.editor.cn;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * <pre>
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 *
 *
 * Note:
 *
 *     1 <= K <= points.length <= 10000
 *     -10000 < points[i][0] < 10000
 *     -10000 < points[i][1] < 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
 * </pre>
 */
class KClosestPointsToOrigin {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] kClosest(int[][] points, int K) {
            // 大根堆，维护给定数组中最小的 K 个点的信息，堆顶是该堆中最大的元素
            PriorityQueue<Pair<int[], Integer>> queue =
                    new PriorityQueue<>(Comparator.comparingInt(
                            (ToIntFunction<Pair<int[], Integer>>) p -> (Integer) p.getValue()).reversed());
            int topK = Integer.MIN_VALUE; // 堆顶的点的距离原点的距离
            for (int[] point : points) {
                int distance = point[0] * point[0] + point[1] * point[1];
                // 堆未满时，直接加入堆
                if (queue.size() < K) {
                    queue.offer(new Pair<>(point, distance));
                    // 更新堆顶元素的距离
                    topK = distance > topK ? distance : topK;
                    continue;
                }
                // 堆已满时，仅在当前的 point 与原点的距离比堆顶元素的距离小时，才将该点加入到堆中
                if (distance < topK) {
                    queue.poll(); // 移除堆顶元素
                    queue.offer(new Pair<>(point, distance)); // 加入新元素
                    topK = queue.peek().getValue(); // 更新 topK
                }
            }
            // 使用 Java8 的 stream api 返回
            return queue.stream().map(Pair::getValue).collect(Collectors.toList()).toArray(new int[0][0]);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * @see KSmallestNumber.Solution#getLeastNumbers(int[], int)
     */
    class QuickSelectSolution {
        Random random = new Random();

        public int[][] kClosest(int[][] points, int K) {
            quickSelect(points, K, 0, points.length - 1);
            return Arrays.copyOf(points, K);
        }

        private void quickSelect(int[][] points, int K, int left, int right) {
            // 保证 [left, right] 区间至少有两个元素
            if (left >= right || points == null || points.length <= 1 || left < 0 || right >= points.length) return;

            int pivot = partition(points, left, right);

            if (pivot == K || pivot == K - 1)
                return;
            else if (pivot < K - 1)
                quickSelect(points, pivot + 1, right, K);
            else
                quickSelect(points, left, pivot - 1, K);
        }

        private int partition(int[][] points, int left, int right) {
            int pivotId = left + random.nextInt(right - left + 1);
            int pivotDistance = dist(points[pivotId]);
            swap(points, right, pivotId); // 将随机选取的 pivot 放在最右侧

            int i = left - 1;
            for (int j = left; j < right; j++) {
                if (dist(points[j]) <= pivotDistance) {
                    i++;
                    swap(points, i, j);
                }
            }
            swap(points, ++i, right); // 注意是 ++i，不是 i++
            return i;
        }

        private int dist(int[] point) {
            return point[0] * point[0] + point[1] * point[1];
        }

        private void swap(int[][] points, int i, int j) {
            int[] tmp = points[i];
            points[i] = points[j];
            points[j] = tmp;
        }
    }

}
