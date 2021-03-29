package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Random;

/**
 * <pre>
 * 输入整数数组 arr，找出其中最小的 k 个数。例如，输入 4、5、1、6、2、7、3、8 这 8 个数字，则最小的 4 个数字是 1、2、3、4。
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 *
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 *
 *
 * 限制：
 *
 *     0 <= k <= arr.length <= 10000
 *     0 <= arr[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 * </pre>
 */
class KSmallestNumber {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Random random = new Random();

        public int[] getLeastNumbers(int[] arr, int k) {
            quickSelect(arr, 0, arr.length - 1, k);
            return Arrays.copyOf(arr, k);
        }

        private void quickSelect(int[] nums, int left, int right, int k) {
            if (left >= right || k == 1 || nums == null || nums.length <= 1 || left < 0 || right >= nums.length) return;
            /* 以 pivotIdx 拆分 [left, right]：
             * 拆分后，[left, pivotIdx) 内的元素均小于等于 pivot = nums[pivotIdx]，(pivotIdx, right] 内的元素均大于 pivot。*/
            int pivotIdx = partition(nums, left, right);
            if (pivotIdx == k || pivotIdx == k - 1)
                /*[0, k-1] 即为前 k 小的数所在的区间*/
                return;
            else if (pivotIdx < k - 1)
                quickSelect(nums, pivotIdx + 1, right, k);
            else
                quickSelect(nums, left, pivotIdx - 1, k);
        }

        private int partition(int[] nums, int left, int right) {
            int ref = left + random.nextInt(right - left + 1); // generate a random num between [left, right]
            int pivot = nums[ref];
            swap(nums, ref, right); // swap the pivot to the rightmost index

            int i = left - 1; // 已知小于等于 pivot 的元素的索引的最大值
            for (int j = left; j < right; j++) { // 遍历 [left, right)
                if (nums[j] <= pivot) {
                    i++; // 已知大于 pivot 的元素的索引的最小值
                    swap(nums, i, j); // 交换后，i 又变成了：已知小于等于 pivot 的元素的索引的最大值
                }
            }
            swap(nums, ++i, right);
            return i;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
