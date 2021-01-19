package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the
 * number of different integers in that subarray is exactly K.
 *
 * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 *
 * Return the number of good subarrays of A.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,2,1,2,3], K = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2],
 * [1,2,1,2].
 *
 * Example 2:
 *
 * Input: A = [1,2,1,3,4], K = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 *
 *
 *
 * Note:
 *
 *     1 <= A.length <= 20000
 *     1 <= A[i] <= A.length
 *     1 <= K <= A.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
 * </pre>
 */
class SubarraysWithKDifferentIntegers {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int subarraysWithKDistinct(int[] A, int K) {
            Window window1 = new Window();
            Window window2 = new Window();
            int ans = 0, left1 = 0, left2 = 0;

            for (int right = 0; right < A.length; ++right) {
                int num = A[right];
                window1.add(num);
                window2.add(num);

                while (window1.size() > K) // 直到 window1 的 size 恢复到 K
                    window1.remove(A[left1++]);
                while (window2.size() >= K) // 直到 window2 的 size 恢复到 K-1
                    window2.remove(A[left2++]);

                // 当 window2 的 size 第一次达到 K 之前，left2 和 left1 一直都是相等的
                ans += left2 - left1;
            }

            return ans;
        }
    }

    class Window {
        Map<Integer, Integer> count; // 窗口中各种数字的个数
        int size; // 窗口中数字的种类数量

        Window() {
            count = new HashMap();
            size = 0;
        }

        void add(int x) {
            count.put(x, count.getOrDefault(x, 0) + 1);
            if (count.get(x) == 1)
                size++;
        }

        void remove(int x) {
            count.put(x, count.get(x) - 1);
            if (count.get(x) == 0)
                size--;
        }

        int size() {
            return size;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class TwoPointerSolution {
        public int subarraysWithKDistinct(int[] A, int K) {
            int n = A.length, left = 0, right = 0, count = 0, ans = 0;
            int[] f = new int[n + 1];

            while (right < n) {
                if (f[A[right]]++ == 0) count++;
                // 维持 [left, right] 之间的数字种类为 K
                while (count > K) {
                    if (--f[A[left]] == 0) count--;
                    left++;
                }
                if (count == K) {
                    int le = left;
                    // 计算以 right 为右边界的满足题意的子数组个数
                    while (count == K) {
                        if (--f[A[le]] == 0) count--;
                        le++;
                        ans++;
                    }
                    // 恢复 count 数组
                    for (int i = left; i < le; i++)
                        if (f[A[i]]++ == 0) {
                            count++;
                        }
                }
                right++;
            }
            return ans;
        }
    }
}
