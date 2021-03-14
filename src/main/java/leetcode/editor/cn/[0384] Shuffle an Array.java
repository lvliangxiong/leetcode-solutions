package leetcode.editor.cn;

import java.util.Random;

/**
 * <pre>
 * Given an integer array nums, design an algorithm to randomly shuffle the array.
 *
 * Implement the Solution class:
 *
 *     Solution(int[] nums) Initializes the object with the integer array nums.
 *     int[] reset() Resets the array to its original configuration and returns it.
 *     int[] shuffle() Returns a random shuffling of the array.
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * Output
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must be equally likely to be returned. Example: return [3, 1, 2]
 * solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
 * solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 200
 *     -10^6 <= nums[i] <= 10^6
 *     All the elements of nums are unique.
 *     At most 5 * 10^4 calls will be made to reset and shuffle.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shuffle-an-array
 * </pre>
 */
class ShuffleAnArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private int[] original;

        private int[] shuffle;

        private Random random;

        public Solution(int[] nums) {
            original = nums;
            shuffle = nums.clone();
            random = new Random();
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            shuffle = original;
            original = original.clone();
            return original;
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            for (int i = shuffle.length - 1; i > 0; i--) {
                int r = random.nextInt(i + 1); // generate random int in [0, i]
                swap(shuffle, i, r); // swap elements located at i and r which belongs to [0, i]
            }
            return shuffle;
        }

        private void swap(int[] shuffle, int i, int j) {
            if (i != j) {
                shuffle[i] ^= shuffle[j];
                shuffle[j] ^= shuffle[i];
                shuffle[i] ^= shuffle[j];
            }
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int[] param_1 = obj.reset();
     * int[] param_2 = obj.shuffle();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}