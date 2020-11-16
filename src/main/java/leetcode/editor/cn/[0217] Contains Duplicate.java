package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * Given an array of integers, find if the array contains any duplicates.
 *
 * Your function should return true if any value appears at least twice in the array, and it should return false if
 * every element is distinct.
 *
 * Example 1:
 *
 * Input: [1,2,3,1]
 * Output: true
 *
 * Example 2:
 *
 * Input: [1,2,3,4]
 * Output: false
 *
 * Example 3:
 *
 * Input: [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate
 * </pre>
 */
class ContainsDuplicate {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean containsDuplicate(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                // add 方法在 set 中已经包含改元素时，返回 false
                if (!set.add(num)) return true;
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
