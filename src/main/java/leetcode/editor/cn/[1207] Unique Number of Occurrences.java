package leetcode.editor.cn;

import java.util.*;

/**
 * <pre>
 * Given an array of integers arr, write a function that returns true if and only if the number of occurrences of each
 * value in the array is unique.
 *
 * Example 1:
 *
 * Input: arr = [1,2,2,1,1,3]
 * Output: true
 * Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
 *
 * Example 2:
 *
 * Input: arr = [1,2]
 * Output: false
 *
 * Example 3:
 *
 * Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * Output: true
 *
 *
 * Constraints:
 *
 *     1 <= arr.length <= 1000
 *     -1000 <= arr[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
 * </pre>
 */
class UniqueNumberOfOccurrences {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean uniqueOccurrences(int[] arr) {
            Map<Integer, Integer> counts = new HashMap<>();
            Set<Integer> set = new HashSet<>();
            for (int i : arr) {
                counts.compute(i, (k, v) -> v == null ? 1 : v + 1);
            }
            for (Integer count : counts.values()) {
                if (set.contains(count)) return false;
                set.add(count);
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
