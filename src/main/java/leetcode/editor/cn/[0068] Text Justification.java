package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters
 * and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra
 * spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not
 * divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * Note:
 *
 *     A word is defined as a character sequence consisting of non-space characters only.
 *     Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 *     The input array words contains at least one word.
 *
 * Example 1:
 *
 * Input:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 *
 * Example 2:
 *
 * Input:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be",
 *              because the last line must be left-justified instead of fully-justified.
 *              Note that the second line is also left-justified becase it contains only one word.
 *
 * Example 3:
 *
 * Input:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/text-justification
 * </pre>
 */
class TextJustification {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            int n = words.length;
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int start = i, end = i, len = words[i].length(); // 至少可以容纳一个单词
                while (end + 1 < n && len + words[end + 1].length() + 1 <= maxWidth) {
                    end++;
                    len += words[end].length() + 1;
                }
                int remainedSpaces = maxWidth - len;
                StringBuilder sb = new StringBuilder();
                if (start == end) {
                    // 单个单词
                    sb.append(words[start]);
                } else {
                    // 多个单词
                    int wordCount = end - start + 1;
                    int basicSpaceCount = 1 + remainedSpaces / (wordCount - 1);
                    int extraSpaceCount = remainedSpaces % (wordCount - 1);
                    for (int j = start, k = 0; j <= end; j++, k++) {
                        sb.append(words[j]);
                        if (j == end) break;
                        if (end == n - 1) {
                            sb.append(" ");
                        } else {
                            for (int p = 0; p < basicSpaceCount; p++) sb.append(" ");
                            if (k < extraSpaceCount) sb.append(" "); // 多余的空格分布在靠左边的单词间隔处
                        }
                    }
                }
                if (end == n - 1 || end == start) {
                    for (int j = 0; j < remainedSpaces; j++) sb.append(" ");
                }
                ans.add(sb.toString());
                i = end;
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}