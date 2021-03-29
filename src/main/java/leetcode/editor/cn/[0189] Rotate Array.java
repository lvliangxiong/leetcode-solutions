package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

/**
 * <pre>
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Follow up:
 *
 *     Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 *     Could you do it in-place with O(1) extra space?
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 2 * 10^4
 *     -2^31 <= nums[i] <= 2^31 - 1
 *     0 <= k <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-array
 * </pre>
 */
class RotateArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * @param nums
         * @param k
         * @see RotateList.Solution#rotateRight(ListNode, int)
         */
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            int[] newArr = new int[n];
            for (int i = 0; i < n; i++) {
                // 原数组中下标为 i 的位置，应该移动到 (i+k) % n 位置
                newArr[(i + k) % n] = nums[i];
            }
            System.arraycopy(newArr, 0, nums, 0, n);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class CyclicSolution {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            int count = gcd(k, n);
            for (int start = 0; start < count; ++start) {
                int current = start;
                int prev = nums[start];
                do {
                    int next = (current + k) % n;
                    int temp = nums[next];
                    nums[next] = prev;
                    prev = temp;
                    current = next;
                } while (start != current);
            }
        }

        /**
         * Greatest Common Divisor 最大公约数
         *
         * @param x
         * @param y
         * @return
         */
        public int gcd(int x, int y) {
            // 辗转相除法
            return y > 0 ? gcd(y, x % y) : x;
        }
    }

    /**
     * 利用对称性，进行多次翻转！
     */
    class FlipSolution {
        /**
         * @param nums
         * @param k
         * @see RotateImage.Solution#rotate(int[][])
         */
        public void rotate(int[] nums, int k) {
            k %= nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }

        public void reverse(int[] nums, int i, int j) {
            while (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
    }
}