package leetcode.editor.cn;

import java.util.*;

/**
 * <pre>
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Note:
 *
 *     You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 *     Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *     It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 *     You can return the answer in any order.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * </pre>
 */
class TopKFrequentElements {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int num : nums) {
                freqMap.compute(num, (key, value) -> freqMap.containsKey(key) ? value + 1 : 1);
            }
            // 堆顶存放频次最多的 k 个元素中频次最少的元素
            PriorityQueue<Map.Entry<Integer, Integer>> heap =
                    new PriorityQueue<>(Comparator.comparingInt(e -> e.getValue()));
            for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
                if (heap.size() < k) {
                    heap.add(entry);
                } else if (entry.getValue() > heap.peek().getValue()) {
                    heap.remove();
                    heap.add(entry);
                }
            }
            int[] ans = new int[k];
            int i = k - 1;
            for (Map.Entry<Integer, Integer> entry : heap) {
                ans[i--] = entry.getKey();
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class BucketSortSolution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> freqMap = new HashMap<>();
            int maxFreq = -1;
            for (int num : nums) {
                freqMap.compute(num, (key, value) -> freqMap.getOrDefault(key, 0) + 1);
                maxFreq = Math.max(maxFreq, freqMap.get(num));
            }
            List<Integer>[] buckets = new List[maxFreq + 1];
            int[] ans = new int[k];
            for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
                int freq = entry.getValue();
                if (buckets[freq] == null) buckets[freq] = new ArrayList<>();
                buckets[freq].add(entry.getKey());
            }
            for (int freq = buckets.length - 1, j = 0; freq >= 0; freq--) {
                if (buckets[freq] != null) {
                    for (Integer v : buckets[freq]) {
                        ans[j++] = v;
                        if (j == k) return ans;
                    }
                }
            }
            return ans;
        }
    }

}