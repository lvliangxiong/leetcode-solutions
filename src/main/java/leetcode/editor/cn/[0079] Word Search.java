package leetcode.editor.cn;

/**
 * <pre>
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 *
 *
 * Constraints:
 *
 *     board and word consists only of lowercase and uppercase English letters.
 *     1 <= board.length <= 200
 *     1 <= board[i].length <= 200
 *     1 <= word.length <= 10^3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * </pre>
 */
class WordSearch {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int rows;
        private int cols;

        public boolean exist(char[][] board, String word) {
            rows = board.length;
            cols = board[0].length;
            char[] chars = word.toCharArray();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (dfs(board, chars, 0, i, j)) {
                        return true;
                    }
                }
            }
            return false;
        }

        boolean dfs(char[][] board, char[] word, int index, int row, int col) {
            if (index == word.length) return true;
            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                return false;
            }
            if (board[row][col] == word[index]) {
                char tmp = board[row][col];
                board[row][col] = '#'; // 修改被匹配成功的格子

                if (dfs(board, word, index + 1, row, col + 1)) return true;
                if (dfs(board, word, index + 1, row, col - 1)) return true;
                if (dfs(board, word, index + 1, row + 1, col)) return true;
                if (dfs(board, word, index + 1, row - 1, col)) return true;

                board[row][col] = tmp; // 还原
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
