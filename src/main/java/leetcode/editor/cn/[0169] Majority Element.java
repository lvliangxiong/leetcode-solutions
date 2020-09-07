package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <pre>
 * Given an array of size n, find the majority element. The majority element is the element that appears more than
 * ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: 3
 *
 * Example 2:
 *
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * </pre>
 */
class MajorityElement {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int majorityElement(int[] nums) {
            int half = nums.length >> 1;
            Map<Integer, Integer> counts = new HashMap<>();
            for (int num : nums) {
                counts.compute(num, (k, v) -> v == null ? 1 : v + 1);
                if (counts.get(num) > half) return num;
            }
            return 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 随机选择一个数组元素，计算其 count，判断是否是 majority element
     */
    class RandomSelectionSolution {
        private int randRange(Random rand, int min, int max) {
            return rand.nextInt(max - min) + min;
        }

        private int countOccurrences(int[] nums, int num) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == num) {
                    count++;
                }
            }
            return count;
        }

        public int majorityElement(int[] nums) {
            Random rand = new Random();

            int majorityCount = nums.length >> 1;

            while (true) {
                int candidate = nums[randRange(rand, 0, nums.length)];
                if (countOccurrences(nums, candidate) > majorityCount) {
                    return candidate;
                }
            }
        }
    }

    class DivideAndConquerSolution {
        private int countInRange(int[] nums, int num, int lo, int hi) {
            int count = 0;
            for (int i = lo; i <= hi; i++) {
                if (nums[i] == num) {
                    count++;
                }
            }
            return count;
        }

        private int majorityElementRecursion(int[] nums, int lo, int hi) {
            // base case; the only element in an array of size 1 is the majority
            // element.
            if (lo == hi) {
                return nums[lo];
            }

            // recurse on left and right halves of this slice.
            int mid = (hi - lo) / 2 + lo;
            int leftMajor = majorityElementRecursion(nums, lo, mid);
            int rightMajor = majorityElementRecursion(nums, mid + 1, hi);

            // if the two halves agree on the majority element, return it.
            if (leftMajor == rightMajor) {
                return leftMajor;
            }

            // otherwise, count each element and return the "winner".
            int leftCount = countInRange(nums, leftMajor, lo, hi);
            int rightCount = countInRange(nums, rightMajor, lo, hi);

            return leftCount > rightCount ? leftMajor : rightMajor;
        }

        public int majorityElement(int[] nums) {
            return majorityElementRecursion(nums, 0, nums.length - 1);
        }
    }

    /**
     * Boyer-Moore 投票算法
     */
    class BoyerMoorePollSolution {
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;

            for (int num : nums) {
                /* 如果 candidate 不是最终当选者，那么最终当选者和其他非当选者（除 candidate 之外）会一起反对 candidate 当选，
                 * 因此 candidate 必会下台，最终只有『最终当选者』获胜，即 count > 0 。*/
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }

            return candidate;
        }
    }

}