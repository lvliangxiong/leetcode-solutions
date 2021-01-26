package leetcode.editor.cn;

/**
 * <pre>
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending
 * order).
 *
 * The replacement must be in-place and use only constant extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
 * column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * </pre>
 */
class NextPermutation {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 为了完成题目的要求，需要交换一对逆序对，并且保证交换的逆序对中，较小数尽量大，较大数尽量大。
         * 这个问题使用作图的方式可以更好地进行理解：i+1 索引位置的点其实是该数组的最后一个极大值点。
         *
         * @param nums
         */
        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            // find the last pair which nums[i] < nums[i+1] (descending)
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }
            // if found such a pair, swap the least larger element in [i+1, len-1] with nums[i]
            // such an element must exist because at least element nums[i+1] meets this requirement.
            if (i >= 0) {
                int j = nums.length - 1;
                while (j >= 0 && nums[j] <= nums[i]) {
                    j--;
                }
                swap(nums, i, j);
            }
            // 交换之后，[i+1, len-1] 索引范围内，仍然是递减的，需要将其翻转，变成递增序列
            // reverse the element in [i+1, len-1] to get a least sequence
            reverse(nums, i + 1);
        }

        private void reverse(int[] nums, int start) {
            int i = start, j = nums.length - 1;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
