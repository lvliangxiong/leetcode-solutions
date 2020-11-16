package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 *
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra
 * space.
 *
 * Example:
 *
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [5,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
 * </pre>
 */
class FindAllNumbersDisappearedInAnArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                // on the i-th element, it should be i+1
                // element j should be (j-1)-th element
                while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                    swap(nums, nums[i] - 1, i);
                }
            }
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (nums[i] != i + 1) ans.add(i + 1);
            }
            return ans;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class InPlaceSolution {
        /**
         * 使用数组中的数字的正负性表示该索引位置应该存储的元素是否存在
         *
         * @param nums
         * @return
         */
        public List<Integer> findDisappearedNumbers(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                int p = Math.abs(nums[i]) - 1;
                if (nums[p] > 0) nums[p] *= -1;
            }
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (nums[i] > 0) ans.add(i + 1);
            }
            return ans;
        }
    }

}
