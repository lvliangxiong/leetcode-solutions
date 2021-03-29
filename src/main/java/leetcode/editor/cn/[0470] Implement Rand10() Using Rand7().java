package leetcode.editor.cn;

import java.util.Random;

/**
 * <pre>
 * Given the API rand7() that generates a uniform random integer in the range [1, 7], write a function rand10() that
 * generates a uniform random integer in the range [1, 10]. You can only call the API rand7(), and you shouldn't call
 * any other API. Please do not use a language's built-in random API.
 *
 * Each test case will have one internal argument n, the number of times that your implemented function rand10() will
 * be called while testing. Note that this is not an argument passed to rand10().
 *
 * Follow up:
 *
 *     What is the expected value for the number of calls to rand7() function?
 *     Could you minimize the number of calls to rand7()?
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: [2]
 *
 * Example 2:
 *
 * Input: n = 2
 * Output: [2,8]
 *
 * Example 3:
 *
 * Input: n = 3
 * Output: [3,8,10]
 *
 *
 *
 * Constraints:
 *
 *     1 <= n <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7
 * </pre>
 */
class ImplementRand10UsingRand7 {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * The rand7() API is already defined in the parent class SolBase.
     * public int rand7();
     *
     * @return a random integer in the range 1 to 7
     */
    class Solution extends SolBase {

        /**
         * 拒绝采样法！
         *
         * @return
         */
        public int rand10() {
            while (true) {
                int rnd = (rand7() - 1) * 7 + (rand7() - 1); // [0, 48]
                if (rnd <= 39) {
                    // [0, 39]
                    return (rnd % 10) + 1;
                }
            }
        }
    }


    //leetcode submit region end(Prohibit modification and deletion)

    class OptimisedSolution extends SolBase {
        public int rand10() {
            int a, b, idx;
            while (true) {
                a = rand7();
                b = rand7();
                idx = b + (a - 1) * 7; // [1,49]
                if (idx <= 40)
                    return 1 + (idx - 1) % 10;
                // idx belongs to [41, 49]
                a = idx - 40; // [1,9]
                b = rand7();
                // get uniform dist from 1 - 63
                idx = b + (a - 1) * 7;
                if (idx <= 60)
                    return 1 + (idx - 1) % 10;
                a = idx - 60; // [1,3]
                b = rand7();
                // get uniform dist from 1 - 21
                idx = b + (a - 1) * 7;
                if (idx <= 20)
                    return 1 + (idx - 1) % 10;
            }
        }
    }

    private class SolBase {
        Random random = new Random();

        public int rand7() {
            return random.nextInt(7) + 1;
        }
    }
}