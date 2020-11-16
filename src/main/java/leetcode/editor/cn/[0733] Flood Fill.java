package leetcode.editor.cn;


import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <pre>
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image
 * (from 0 to 65535).
 *
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel
 * value newColor, "flood fill" the image.
 *
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the
 * starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those
 * pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned
 * pixels with the newColor.
 *
 * At the end, return the modified image.
 *
 * Example 1:
 *
 * Input:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation:
 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 * by a path of the same color as the starting pixel are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel.
 *
 * Note:
 * The length of image and image[0] will be in the range [1, 50].
 * The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 * The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flood-fill
 * </pre>
 */
class FloodFill {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int rows;
        int cols;
        int[][] image;
        boolean[][] visited;
        int sourceColor;
        int newColor;

        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            if (image[sr][sc] == newColor) return image;

            rows = image.length;
            cols = image[0].length;
            this.image = image;
            visited = new boolean[rows][cols];
            this.sourceColor = image[sr][sc];
            this.newColor = newColor;

            dfs(sr, sc);
            return image;
        }

        private void dfs(int row, int col) {
            if (visited[row][col]) return;
            // 还未访问过
            if (image[row][col] == sourceColor) {
                image[row][col] = newColor;
                if (row - 1 >= 0) dfs(row - 1, col);
                if (col - 1 >= 0) dfs(row, col - 1);
                if (row + 1 < rows) dfs(row + 1, col);
                if (col + 1 < cols) dfs(row, col + 1);
            }
            visited[row][col] = true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class BFSSolution {
        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            if (image[sr][sc] == newColor) return image;

            int rows = image.length;
            int cols = image[0].length;
            int color = image[sr][sc];

            Deque<Pair<Integer, Integer>> queue = new LinkedList<>();
            boolean[][] visited = new boolean[rows][cols];
            queue.offer(new Pair<>(sr, sc));
            visited[sr][sc] = true;

            while (!queue.isEmpty()) {
                Pair<Integer, Integer> pixel = queue.poll();
                int row = pixel.getKey();
                int col = pixel.getValue();
                if (image[row][col] == color) {
                    image[row][col] = newColor;
                    if (row - 1 >= 0) queue.offer(new Pair<>(row - 1, col));
                    if (col - 1 >= 0) queue.offer(new Pair<>(row, col - 1));
                    if (row + 1 < rows) queue.offer(new Pair<>(row + 1, col));
                    if (col + 1 < cols) queue.offer(new Pair<>(row, col + 1));
                }
            }
            return image;
        }
    }
}
