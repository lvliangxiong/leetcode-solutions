package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent"
 * cells are those horizontally or vertically neighboring. The same letter cell may not be used
 * more than once in a word.
 *
 *
 *
 * Example:
 *
 * Input:
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 *
 * Output: ["eat","oath"]
 *
 *
 *
 * Note:
 *
 *     All inputs are consist of lowercase letters a-z.
 *     The values of words are distinct.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search-ii
 * </pre>
 */
class WordSearchIi {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int rows;
        int cols;

        TrieNode root = new TrieNode();
        List<String> results = new ArrayList<>();
        char visited = '#';

        void insert(String word) {
            TrieNode current = root;
            for (char ch : word.toCharArray()) {
                current = current.put(ch);
            }
            current.setEnd(true);
        }


        void remove(String word) {
            TrieNode current = root;
            for (char ch : word.toCharArray()) {
                current = current.put(ch);
            }
            current.setEnd(true);
        }

        public List<String> findWords(char[][] board, String[] words) {
            // 将给定的单词数组全部添加到『前缀树』中
            for (String word : words) {
                insert(word);
            }

            this.rows = board.length;
            this.cols = board[0].length;
            // 以 board 的每一个单元格为起始字符，去『前缀树』上匹配单词
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    StringBuilder sb = new StringBuilder();
                    backtrack(board, row, col, root, sb);
                }
            }
            return results;
        }

        private void backtrack(char[][] board, int row, int col, TrieNode node, StringBuilder sb) {
            if (node.isEnd()) {
                node.setEnd(false);
                results.add(sb.toString());
            }
            if (row < 0 || col < 0 || row >= rows || col >= cols) {
                return;
            }
            char ch = board[row][col];
            if (ch != visited && node.containsKey(ch)) {
                sb.append(ch);
                board[row][col] = visited;

                backtrack(board, row + 1, col, node.get(ch), sb);
                backtrack(board, row - 1, col, node.get(ch), sb);
                backtrack(board, row, col + 1, node.get(ch), sb);
                backtrack(board, row, col - 1, node.get(ch), sb);

                board[row][col] = ch;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    class TrieNode {
        private static final int R = 26;
        private TrieNode[] links;
        private boolean isEnd;

        public TrieNode() {
            this.links = new TrieNode[R];
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public TrieNode put(char ch) {
            if (containsKey(ch)) {
                return get(ch);
            } else {
                TrieNode node = new TrieNode();
                links[ch - 'a'] = node;
                return node;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
