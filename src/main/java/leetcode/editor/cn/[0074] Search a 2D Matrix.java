package leetcode.editor.cn;

class SearchA2dMatrix {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            int low = 0, high = m * n - 1;
            while (low < high) {
                int mid = (low + high) >>> 1;
                int row = mid / n, col = mid % n;
                if (matrix[row][col] == target) {
                    return true;
                } else if (matrix[row][col] > target) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return matrix[low / n][low % n] == target;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}