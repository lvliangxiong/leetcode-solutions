package leetcode.editor.cn;

/**
 * <pre>
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the
 * non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * Note:
 *
 *     You must do this in-place without making a copy of the array.
 *     Minimize the total number of operations.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * </pre>
 */
class MoveZeroes {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void moveZeroes(int[] nums) {
            int i = 0, n = nums.length;
            for (int j = 0; j < n; j++) {
                if (nums[j] != 0) nums[i++] = nums[j];
            }
            while (i < n) {
                nums[i++] = 0;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class OptimalSolution {
        public void moveZeroes(int[] nums) {
            for (int lastNonZeroElementFoundAt/* 慢指针前面所有元素都不是 0 */ = 0,
                 cur = 0/* 当前指针和慢指针之间的值都是 0 */; cur < nums.length; cur++) {
                if (nums[cur] != 0) {
                    swap(nums, lastNonZeroElementFoundAt, cur);
                    lastNonZeroElementFoundAt++;
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
