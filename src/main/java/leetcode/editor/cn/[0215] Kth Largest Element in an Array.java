package leetcode.editor.cn;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * <pre>
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 *
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * </pre>
 */
class KthLargestElementInAnArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 使用了 Java 的内置『堆』结构，当然也可以自己手动实现，可参考 {@link KthLargestElementInAStream.KthLargest}。
         *
         * @param nums
         * @param k
         * @return
         */
        public int findKthLargest(int[] nums, int k) {
            /* The head of this queue is the least element with respect to the specified ordering.
             * The heap maintains the biggest k elements in the array, and the head is the least one among
             * these k elements. */
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            for (int num : nums) {
                if (heap.size() < k) {
                    heap.add(num);
                } else if (num > heap.peek()) {
                    heap.poll();
                    heap.add(num);
                }
            }
            return heap.peek();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 快速选择算法，类似于『快排』，可用于快速从给定数据量的数据中，确定第 K 个具有特定特征的数据。
     */
    class QuickSelectSolution {
        Random random = new Random();

        public int findKthLargest(int[] nums, int k) {
            return quickSelect(nums, 0, nums.length - 1, nums.length - k);
        }

        public int quickSelect(int[] arr, int left, int right, int target) {
            if (left < right) {
                int index = randomPartition(arr, left, right);
                if (index != target) {
                    return index < target ? quickSelect(arr, index + 1, right, target)
                            : quickSelect(arr, left, index - 1, target);
                }
            }
            return arr[target];
        }

        public int randomPartition(int[] arr, int left, int right) {
            // generate a random int between [left, right] as the reference value's index
            int i = random.nextInt(right - left + 1) + left;
            swap(arr, i, right);
            return partition(arr, left, right);
        }

        /**
         * Partition the given array with the right boundary value as the reference value.
         *
         * @param arr
         * @param left
         * @param right
         * @return
         */
        public int partition(int[] arr, int left, int right) {
            int ref = arr[right], i = left;
            for (int j = left; j < right; j++) {
                if (arr[j] <= ref) {
                    swap(arr, i++, j);
                }
            }
            swap(arr, i, right);
            return i;
        }

        public void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

}
