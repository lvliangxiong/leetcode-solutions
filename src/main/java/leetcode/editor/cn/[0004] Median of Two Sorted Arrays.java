package leetcode.editor.cn;

/**
 * <pre>
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * Follow up: The overall run time complexity should be O(log (m+n)).
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 *
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 * Example 3:
 *
 * Input: nums1 = [0,0], nums2 = [0,0]
 * Output: 0.00000
 *
 * Example 4:
 *
 * Input: nums1 = [], nums2 = [1]
 * Output: 1.00000
 *
 * Example 5:
 *
 * Input: nums1 = [2], nums2 = []
 * Output: 2.00000
 *
 *
 *
 * Constraints:
 *
 *     nums1.length == m
 *     nums2.length == n
 *     0 <= m <= 1000
 *     0 <= n <= 1000
 *     1 <= m + n <= 2000
 *     -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * </pre>
 */
class MedianOfTwoSortedArrays {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int totalLen = nums1.length + nums2.length;
            double median = getKthElement(nums1, nums2, (totalLen >> 1) + 1);
            if ((totalLen & 1) == 0) {
                median = (median + getKthElement(nums1, nums2, totalLen >> 1)) / 2;
            }
            return median;
        }

        /**
         * 求两个排序数组中的第 K 小数问题
         *
         * @param nums1
         * @param nums2
         * @param k
         * @return
         */
        private int getKthElement(int[] nums1, int[] nums2, int k) {
            int len1 = nums1.length, len2 = nums2.length;
            int startIndex1 = 0, startIndex2 = 0;
            while (true) {
                if (startIndex1 == len1)
                    return nums2[startIndex2 + k - 1];
                if (startIndex2 == len2)
                    return nums1[startIndex1 + k - 1];
                if (k == 1)
                    return Math.min(nums1[startIndex1], nums2[startIndex2]);

                // 逐步缩小 k
                int half = k >> 1;
                // [startIndex, newIndex] 区间最多包含了 half 个元素
                int newIndex1 = Math.min(startIndex1 + half, len1) - 1;
                int newIndex2 = Math.min(startIndex2 + half, len2) - 1;
                // pivot 之前（不包含 pivot）最多包含了 half-1 个元素
                int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
                if (pivot1 <= pivot2) {
                    // pivot1 不可能是第 k 小数，因为 (half - 1) + (half - 1) = 2*half -2 <= k - 2，即 pivot1 最多是第 k-1 小数
                    k -= (newIndex1 - startIndex1 + 1); // 排除 [startIndex1, newIndex1] 之间的所有元素，缩小 k 的值
                    startIndex1 = newIndex1 + 1;
                } else {
                    k -= (newIndex2 - startIndex2 + 1);
                    startIndex2 = newIndex2 + 1;
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
