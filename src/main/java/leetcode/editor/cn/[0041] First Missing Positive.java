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
         * 使用元素的正负记录信息：
         * 1. nums[i] < 0 表明 i 已出现过
         * 2. nums[i] > 0 表明 i 还未出现
         * 缺点是需要改变原数组，并且要额外处理本身就小于 0 的数字。
         *
         * @param nums
         * @return
         */
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                // 将原始数组中 <= 0 的数，全部设置为 n+1
                if (nums[i] <= 0) nums[i] = n + 1;
            }
            for (int num : nums) {
                num = num > 0 ? num : -num; // 这里 num 可能是负数，因为在循环的过程中对数组进行了修改
                if (num >= 1 && num <= n && nums[num - 1] > 0) {
                    // 原始数组中元素为 [1, n] 的元素是没有变化的，遇到这样的数
                    nums[num - 1] *= -1;
                }
            }
            // 找到第一个不是负数的元素的索引位置
            for (int i = 1; i <= n; i++) {
                if (nums[i - 1] > 0) {
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
                // 将 1 ~ n 之间的 num 移动到索引为 num-1 的位置上
                while (nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
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
