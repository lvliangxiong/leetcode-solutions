package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array,
 * and the length of an increasing subsequence should be at least 2.
 *
 *
 *
 * Example:
 *
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 *
 *
 *
 * Constraints:
 *
 *     The length of the given array will not exceed 15.
 *     The range of integer in the given array is [-100,100].
 *     The given array may contain duplicates, and two equal integers should also be considered as a special case of
 *     increasing sequence.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-subsequences
 * </pre>
 */
class IncreasingSubsequences {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<Integer> seq;
        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        public List<List<Integer>> findSubsequences(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < (1 << n); i++) {
                findSubsequences(nums, i, n);
                int h = hash(263, (int) (1E9 + 7));
                if (check() && !set.contains(h)) {
                    ans.add(seq);
                    set.add(h);
                }
            }
            return ans;
        }

        /**
         * 判断当前子序列是否为递增子序列
         *
         * @return
         */
        private boolean check() {
            int n = seq.size();
            if (n < 2) return false;
            for (int i = 1; i < n; i++) {
                if (seq.get(i - 1) > seq.get(i)) return false;
            }
            return true;
        }

        /**
         * 计算当前子序列的 hash 值，用于避免结果中出现重复子序列。
         *
         * @param base
         * @param mod
         * @return
         */
        private int hash(int base, int mod) {
            int h = 0;
            for (Integer i : seq) {
                h = (h * base) % mod + (i + 101);
                h %= mod;
            }
            return h;
        }

        /**
         * 根据给定的 mask 的二进制表示，获得相应的子序列
         *
         * @param nums
         * @param mask
         * @param n
         */
        private void findSubsequences(int[] nums, int mask, int n) {
            seq = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((mask & 1) != 0) {
                    seq.add(nums[j]);
                }
                mask >>= 1;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class DFSSolution {
        List<Integer> seq = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        public List<List<Integer>> findSubsequences(int[] nums) {
            dfs(0, Integer.MIN_VALUE, nums);
            return ans;
        }

        /**
         * @param cur  当前被选择的元素的索引
         * @param last 上一个被选择的元素
         * @param nums
         */
        public void dfs(int cur, int last, int[] nums) {
            if (cur == nums.length) {
                if (seq.size() >= 2) {
                    ans.add(new ArrayList<Integer>(seq));
                }
                return;
            }
            // 选择当前元素
            if (nums[cur] >= last) {
                seq.add(nums[cur]);
                dfs(cur + 1, nums[cur], nums);
                seq.remove(seq.size() - 1);
            }
            // 不选择当前元素
            if (nums[cur] != last) {
                dfs(cur + 1, last, nums);
            }
        }
    }
}
