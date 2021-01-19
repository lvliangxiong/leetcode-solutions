package leetcode.editor.cn;

/**
 * <pre>
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 *
 *
 * 限制：
 *
 * 2 <= n <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * </pre>
 */
class ShuZuZhongChongFuDeShuZiLcof {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 将数字 i 放到索引为 i 的位置上，出现数字碰撞，则说明该数字是一个重复数字
         * @param nums
         * @return
         */
        public int findRepeatNumber(int[] nums) {
            if(nums != null && nums.length > 1){
                for (int i = 0; i < nums.length; i++) {
                    // 值为 nums[i] 的数组元素，应该放在数组的索引为 nums[i] 处
                    while (nums[i] != i) {
                        // 交换索引为 i 和 nums[i] 的两个元素
                        if (nums[i] == nums[nums[i]])
                            return nums[i];
                        swap(nums, i, nums[i]);
                    }
                }
            }
            throw new IllegalArgumentException();
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
