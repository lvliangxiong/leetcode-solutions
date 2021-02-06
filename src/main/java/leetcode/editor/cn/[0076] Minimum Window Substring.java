package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Given two strings s and t, return the minimum window in s which will contain all the characters in t. If there is no
 * such window in s that covers all characters in t, return the empty string "".
 *
 * Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 *
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length, t.length <= 10^5
 *     s and t consist of English letters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * </pre>
 */
class MinimumWindowSubstring {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minWindow(String s, String t) {
            Map<Character, Integer> counts = new HashMap<>();
            Map<Character, Integer> slidingCounts = new HashMap<>();
            for (char ch : t.toCharArray()) {
                counts.put(ch, counts.getOrDefault(ch, 0) + 1);
            }
            int start = 0, end = 0;
            int count = counts.size(), slidingCount = 0, min = Integer.MAX_VALUE;
            int ansStart = -1, ansEnd = -1;
            while (end < s.length()) {
                // 移动 end，使得 [start, end] 之间的子串包含 t 中的所有字符
                while (end < s.length()) {
                    char ch = s.charAt(end);
                    if (counts.containsKey(ch)) {
                        slidingCounts.put(ch, slidingCounts.getOrDefault(ch, 0) + 1);
                        // 注意这里 Integer 之间的比较，需要使用 equals 方法
                        if (slidingCounts.get(ch).equals(counts.get(ch))) {
                            slidingCount++;
                            if (slidingCount == count) break;
                        }
                    }
                    end++;
                }
                if (end == s.length()) break;
                // 移动 start，得到最小的 [start, end] 区间
                while (start <= end) {
                    char ch = s.charAt(start);
                    if (counts.containsKey(ch)) {
                        if (slidingCounts.get(ch).equals(counts.get(ch))) break; // 不能剔除 ch
                        slidingCounts.put(ch, slidingCounts.get(ch) - 1);
                    }
                    start++;
                }
                // 记录 start 和 end 的值
                if (end - start + 1 < min) {
                    min = end - start + 1;
                    ansStart = start;
                    ansEnd = end;
                }

                // 移除子串首字符，重新进行查找
                char ch = s.charAt(start);
                slidingCounts.put(ch, slidingCounts.get(ch) - 1);
                slidingCount--;
                start++;
                end++;
            }
            return ansStart == -1 ? "" : s.substring(ansStart, ansEnd + 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class OptimisedSolution {
        public String minWindow(String s, String t) {
            int tLen = t.length();
            int sLen = s.length();
            char[] sArr = s.toCharArray();
            char[] tArr = t.toCharArray();
            int right = 0, left = 0;
            int distance = 0;
            int min = sLen + 1;
            // ASCII 码表只需要 128 个位置即可存储，况且数组操作比 HashMap 要快很多
            int[] winFreq = new int[128];
            int[] tFreq = new int[128];
            int begin = 0;
            for (char c : tArr) {
                tFreq[c]++;
            }

            while (right < sLen) {
                if (tFreq[sArr[right]] == 0) {
                    right++;
                    continue;
                }
                if (winFreq[sArr[right]] < tFreq[sArr[right]]) {
                    // 可以将 distance 理解为『窗口中的有效字符数量』
                    distance++;
                }
                winFreq[sArr[right]]++;
                right++;
                // 当前窗口为 [left, right)
                while (distance == tLen) {
                    if (right - left < min) {
                        min = right - left;
                        begin = left;
                    }
                    // 向右移动 left，减小窗口大小
                    if (tFreq[sArr[left]] == 0) {
                        left++;
                        continue;
                    }
                    if (winFreq[sArr[left]] == tFreq[sArr[left]]) {
                        // 当前 left 位置为一个有效字符位置
                        distance--;
                    }
                    winFreq[sArr[left]]--;
                    left++;
                }
            }
            if (min > sLen) {
                return "";
            }
            return s.substring(begin, begin + min);
        }
    }


}