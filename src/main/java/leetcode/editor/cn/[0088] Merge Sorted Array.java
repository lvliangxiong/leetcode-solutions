package leetcode.editor.cn;

/**
 * <pre>
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 *
 *     The number of elements initialized in nums1 and nums2 are m and n respectively.
 *     You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2.
 *
 * Example:
 *
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 *
 *
 *
 * Constraints:
 *
 *     -10^9 <= nums1[i], nums2[i] <= 10^9
 *     nums1.length == m + n
 *     nums2.length == n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * </pre>
 */
class MergeSortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 从后向前进行合并！
         *
         * @param nums1
         * @param m
         * @param nums2
         * @param n
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            // two get pointers for comparing elements in nums1 and nums2
            int p1 = m - 1;
            int p2 = n - 1;
            // set storage pointer for nums1
            int p = m + n - 1;

            // while there are still elements to compare
            while ((p1 >= 0) && (p2 >= 0))
                // compare two elements from nums1 and nums2
                // and add the largest one in nums1
                nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

            // add missing elements from nums2
            System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
