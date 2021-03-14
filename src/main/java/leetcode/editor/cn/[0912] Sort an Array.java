package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

import java.util.Random;

/**
 * <pre>
 * Given an array of integers nums, sort the array in ascending order.
 *
 * Example 1:
 *
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 *
 * Example 2:
 *
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 *
 * Constraints:
 *
 *     1 <= nums.length <= 50000
 *     -50000 <= nums[i] <= 50000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-an-array
 * </pre>
 */
class SortAnArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private Random random = new Random();

        public int[] sortArray(int[] nums) {
            randomQuickSort(nums, 0, nums.length - 1);
            return nums;
        }

        /**
         * 随机快排
         *
         * @param nums
         * @param low
         * @param high
         */
        private void randomQuickSort(int[] nums, int low, int high) {
            if (low < high) {
                int pos = randomPartition(nums, low, high);
                randomQuickSort(nums, low, pos - 1);
                randomQuickSort(nums, pos + 1, high);
            }
        }

        private int randomPartition(int[] nums, int low, int high) {
            int rnd = random.nextInt(high - low + 1) + low; // rnd belongs to [low, high]
            if (rnd != high) swap(nums, rnd, high); // 先将 rnd 移动到最高处
            return partition(nums, low, high);
        }

        private int partition(int[] nums, int low, int high) {
            int pivot = nums[high];
            int i = low - 1;
            for (int j = low; j <= high - 1; j++) {
                // j 遍历 [low, high-1]，将 nums[j] <= pivot 的位置移动到数组前端，保证 [low, i] 之间的值均 <= pivot
                if (nums[j] <= pivot) {
                    i++;
                    swap(nums, i, j);
                }
            }
            swap(nums, i + 1, high); // 将 pivot 移动到正确的中间位置
            return i + 1; // i 为最终的拆分位置
        }

        /**
         * 普通快排
         *
         * @param nums
         * @param low
         * @param high
         */
        private void quickSort(int[] nums, int low, int high) {
            if (low < high) {
                // [low, high] 至少两个元素
                int lo = low, hi = high;
                int pivot = nums[low]; // 基准值
                while (low < high) {
                    while (low < high && nums[high] >= pivot) high--;
                    if (low < high) swap(nums, low++, high); // 交换之后，high 指向的值就是 pivot
                    while (low < high && nums[low] < pivot) low++;
                    if (low < high) swap(nums, low, high--); // 交换之后，low 指向得值就是 pivot
                }
                // 最终，low == high，指向的值就是 pivot
                quickSort(nums, lo, low - 1);
                quickSort(nums, high + 1, hi);
            }
        }

        /**
         * 下面使用『异或』交换数组中的两个数存在一个坑：
         * <p>
         * 当 i != j 时，假设 nums[i] = a，nums[j] = b，那么：
         * 1. nums[i] ^= nums[j]   ===>  nums[i] = a ^ b
         * 2. nums[j] ^= nums[i]   ===>  nums[j] = b ^ (a ^ b) = a
         * 3. nums[i] ^= nums[j]   ===>  nums[i] = (a ^ b) ^ (b ^ (a ^ b)) = (a ^ a) ^ (b ^ b ^ b) = b
         * <p>
         * 当 i == j 时，在第一步对 nums[i] 进行赋值时，也修改了 nums[j]，因为它们引用的是同一个地址。最后会导致该位置为 0。
         * <p>
         * 但是如果只是交换两个基本数据类型的变量，那么就不会有这个坑了！
         *
         * @param nums
         * @param i
         * @param j
         */
        private void swap(int[] nums, int i, int j) {
            if (i != j) {
                nums[i] ^= nums[j];
                nums[j] ^= nums[i];
                nums[i] ^= nums[j];
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class MergeSortSolution {
        int[] temp;


        public int[] sortArray(int[] nums) {
            temp = new int[nums.length];
            mergeSort(nums, 0, nums.length - 1);
            return nums;
        }

        /**
         * 和快排很相似，快排是拆分的时候就已经将两个区间进行了有序化，而归并则是合并的时候再进行有序化！
         *
         * @param nums
         * @param low
         * @param high
         * @see MergeTwoSortedLists.Solution#mergeTwoLists(ListNode, ListNode)
         */
        private void mergeSort(int[] nums, int low, int high) {
            if (low < high) {
                int mid = (low + high) >>> 1;
                mergeSort(nums, low, mid);
                mergeSort(nums, mid + 1, high);
                // 合并两个有序的区间 [low, mid] 和 [mid+1, high] 到 [low, high]
                // 这里使用一个 temp 数组，用来暂存排序的结果！需要保证 temp 已经初始化！
                int i = low, j = mid + 1;
                int cnt = 0;
                while (i <= mid && j <= high) {
                    if (nums[i] <= nums[j]) {
                        temp[cnt++] = nums[i++];
                    } else {
                        temp[cnt++] = nums[j++];
                    }
                }
                while (i <= mid) {
                    temp[cnt++] = nums[i++];
                }
                while (j <= high) {
                    temp[cnt++] = nums[j++];
                }
                for (int k = 0; k < high - low + 1; ++k) {
                    nums[k + low] = temp[k];
                }
            }
        }
    }

    class HeapSortSolution {

        public int[] sortArray(int[] nums) {
            heapSort(nums);
            return nums;
        }

        private void heapSort(int[] nums) {
            // 先将 nums 数组构造成一个最大堆，堆顶是堆中最大的元素
            buildMaxHeap(nums);
            // 依次移出堆顶元素，该元素为堆中最大的元素
            int size = nums.length;
            for (int i = size - 1; i > 0; i--) {
                swap(nums, 0, i); // 将堆顶元素移动到 i 位置
                size--;
                // restore the heap characteristics by downHeap
                downHeap(nums, 0, size - 1);
            }
        }

        /**
         * 将一个无序的数组转为一个最大堆！
         *
         * @param nums
         */
        private void buildMaxHeap(int[] nums) {
            buildMaxHeap(nums, nums.length - 1);
        }

        /**
         * 这里重复使用了 downHeap 方法进行最大堆的初始构建！避免了写 upHeap 方法！
         *
         * @param nums
         * @param end
         */
        private void buildMaxHeap(int[] nums, int end) {
            // 从最后一个节点的父节点开始，进行堆的调整
            for (int i = (end - 1) >> 1; i >= 0; --i) {
                downHeap(nums, i, end);
            }
        }

        /**
         * <pre>
         * Swap down the element located at i to restore the characteristics of the Heap.
         *
         *      0
         *    1   2
         *   3 4 5 6
         *
         * parent(i) ====> (i-1) >> 1
         * left(i)   ====> (i << 1) + 1
         * right(i)  ====> (i+1) << 1
         * </pre>
         *
         * @param nums
         * @param i
         * @param end
         */
        private void downHeap(int[] nums, int i, int end) {
            while ((i << 1) + 1 <= end) { // if has left child
                int larger = (i << 1) + 1;
                int right = larger + 1;
                if (right <= end && nums[larger] < nums[right]) larger = right;
                // ensure that parent node is larger than all its children
                if (nums[i] >= nums[larger]) break;
                else {
                    swap(nums, i, larger);
                    i = larger;
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }

}