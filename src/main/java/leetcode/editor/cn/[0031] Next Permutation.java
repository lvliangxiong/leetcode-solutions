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
        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            // find the last pair which nums[i] < nums[i+1] (ascending)
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }
            // if found such a pair, swap the least larger ( > nums[i]) element in [i+1, len-1] with nums[i]
            // such an element must exist because at least element nums[i+1] meets this requirement.
            if (i >= 0) {
                // search for such an element starting from the end
                int j = nums.length - 1;
                while (j >= 0 && nums[j] <= nums[i]) {
                    j--;
                }
                swap(nums, i, j);
            }
            // 交换之后，[i+1, len-1] 索引范围内，仍然是递减的，需要将其翻转，变成递增序列
            // reverse the element in [i+1, len-1] to get a least sequence
            // if i == -1, which means original array are totally descending, so following operation will make it ascending
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
