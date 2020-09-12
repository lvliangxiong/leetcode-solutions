package leetcode.editor.cn;

/**
 * <pre>
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 *
 * Input: [1,2,0]
 * Output: 3
 *
 * Example 2:
 *
 * Input: [3,4,-1,1]
 * Output: 2
 *
 * Example 3:
 *
 * Input: [7,8,9,11,12]
 * Output: 1
 *
 * Follow up:
 *
 * Your algorithm should run in O(n) time and uses constant extra space.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * </pre>
 */
class FirstMissingPositive {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 改变原数组的元素，将某个数字是否出现过和该数字相对应的索引位置的元素的正负性联系起来。
         *
         * @param nums
         * @return
         */
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= 0) nums[i] = n + 1;
            }
            for (int i = 0; i < n; i++) {
                int num = Math.abs(nums[i]);
                if (num > 0 && num <= n && nums[num - 1] > 0) {
                    // 原始数组中元素为 1 ~ n 的元素是没有变化的，也是我们需要进行记录的
                    nums[num - 1] *= -1;
                }
            }
            for (int i = 1; i <= n; i++) {
                if (nums[i - 1] > 0) {
                    // i 不存在于原始数组中
                    return i;
                }
            }
            return n + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class SortingSolution {
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; ++i) {
                // 将 1 ~ n 之间的 nums[i] 移动到索引为 nums[i]-1 的位置上
                while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                    int temp = nums[nums[i] - 1];
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = temp;
                }
            }
            for (int i = 0; i < n; ++i) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }
            return n + 1;
        }
    }

}
