package leetcode.editor.cn;

/**
 * <pre>
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color
 * are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Follow up:
 *
 *     1. A rather straight forward solution is a two-pass algorithm using counting sort.
 *     First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's,
 *     then 1's and followed by 2's.
 *     2. Could you come up with a one-pass algorithm using only constant space?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * </pre>
 */
class SortColors {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * <pre>
         * +------------------------------------------------------+
         * |      0       |      1       |    ?    |      2       |
         * +------------------------------------------------------+
         *               ^              ^         ^
         *               |              |         |
         *              zero           cur       two
         *
         * </pre>
         *
         * @param nums
         */
        public void sortColors(int[] nums) {
            int n = nums.length;
            if (n <= 1) return;
            int zero = 0, two = n - 1;
            int cur = 0;
            while (cur < two) { // 注意循环停止条件
                if (nums[cur] == 0) {
                    swap(nums, cur, zero);
                    zero++;
                    // 交换后，更新 cur，这是因为和 cur 交换的 zero 位置，要么是其自身，也就是 cur == zero，要么是 1
                    // 因此对 cur 和 zero 位置进行交换后，该位置的值就是正确的了，无需再进行判断
                    cur++;
                } else if (nums[cur] == 1) {
                    cur++;
                } else {
                    // 当 nums[cur] == 2 时，不需要 cur++
                    // 因为和 two 位置的交换需要重新对 cur 位置的值进行判断
                    swap(nums, cur, two);
                    two--;
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
