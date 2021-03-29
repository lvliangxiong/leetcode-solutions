package leetcode.editor.cn;


interface MountainArray {
    public int get(int index);

    public int length();
}

/**
 * <pre>
 * (This problem is an interactive problem.)
 *
 * You may recall that an array A is a mountain array if and only if:
 *
 *     A.length >= 3
 *     There exists some i with 0 < i < A.length - 1 such that:
 *         A[0] < A[1] < ... A[i-1] < A[i]
 *         A[i] > A[i+1] > ... > A[A.length - 1]
 *
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.
 * If such an index doesn't exist, return -1.
 *
 * You can't access the mountain array directly.  You may only access the array using a MountainArray interface:
 *
 *     MountainArray.get(k) returns the element of the array at index k (0-indexed).
 *     MountainArray.length() returns the length of the array.
 *
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.  Also, any solutions
 * that attempt to circumvent the judge will result in disqualification.
 *
 *
 *
 * Example 1:
 *
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 *
 * Example 2:
 *
 * Input: array = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 *
 *
 *
 * Constraints:
 *
 *     3 <= mountain_arr.length() <= 10000
 *     0 <= target <= 10^9
 *     0 <= mountain_arr.get(index) <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-in-mountain-array
 * </pre>
 */
class FindInMountainArray {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * <pre>
     * // This is MountainArray's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface MountainArray {
     *     public int get(int index) {}
     *     public int length() {}
     * }
     * </pre>
     */

    class Solution {
        public int findInMountainArray(int target, MountainArray mountainArr) {
            int low = 0, high = mountainArr.length() - 1;
            int peak = getPeak(mountainArr);
            int ans = -1;
            if (mountainArr.get(peak) >= target) {
                if (mountainArr.get(low) <= target) ans = binarySearch(mountainArr, low, peak, target);
                if (ans == -1 && mountainArr.get(high) <= target) ans = binarySearch(mountainArr, peak, high, target);
            }
            return ans;
        }

        private int getPeak(MountainArray mountain) {
            int low = 0, high = mountain.length() - 1;
            while (low < high) {
                int mid = (low + high) >>> 1;
                if (mountain.get(mid) < mountain.get(mid + 1)) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        private int binarySearch(MountainArray mountain, int low, int high, int target) {
            boolean isIncreasingArr = mountain.get(low) < mountain.get(high);
            while (low < high) {
                int mid = (low + high) >>> 1;
                int midValue = mountain.get(mid);
                if (midValue == target) return mid;
                if (midValue < target) {
                    if (isIncreasingArr) low = mid + 1;
                    else high = mid;
                } else {
                    if (isIncreasingArr) high = mid;
                    else low = mid + 1;
                }
            }
            return mountain.get(low) == target ? low : -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
