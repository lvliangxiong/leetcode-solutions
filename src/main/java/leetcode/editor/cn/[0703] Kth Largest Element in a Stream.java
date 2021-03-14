package leetcode.editor.cn;

/**
 * <pre>
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the
 * sorted order, not the kth distinct element.
 *
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which
 * contains initial elements from the stream. For each call to the method KthLargest.add, return the element
 * representing the kth largest element in the stream.
 *
 * Example:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 *
 * Note:
 * You may assume that nums' length ≥ k-1 and k ≥ 1.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 * </pre>
 */
class KthLargestElementInAStream {
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Heap:
     * 1. Relational property
     * In a heap T, for every position p other than the root, the key stored at p is greater
     * than or equal to the key stored at p's parent.
     * 2. Structural property
     * A heap T with height h is a complete binary tree.
     */
    class KthLargest {

        /**
         * 最小堆，堆顶保存第 k 大的数，对于该 Heap，所有节点的值都比其父节点的值要大。
         * Smaller elements are located at higher positions.
         */
        int[] heap;

        int size; // 堆中元素的数量

        int capacity; // 堆的最大容量

        public KthLargest(int k, int[] nums) {
            capacity = k;
            heap = new int[k];
            for (int num : nums) {
                insert(num);
            }
        }

        /**
         * left child index.
         * <pre>
         *                0
         *          1          2
         *       3    4     5     6
         *     7  8  9 10 11 12 13 14
         * </pre>
         *
         * @param i
         * @return
         */
        private int left(int i) {
            return (i << 1) + 1;
        }

        /**
         * right child index.
         *
         * @param i
         * @return
         */
        private int right(int i) {
            return (i + 1) << 1;
        }

        /**
         * parent node index.
         *
         * @param i
         * @return
         */
        private int parent(int i) {
            return (i - 1) >> 1;
        }

        private boolean hasLeft(int i) {
            return left(i) < size;
        }

        private boolean hasRight(int i) {
            return right(i) < size;
        }

        /**
         * swap the heap locate at index i and j.
         *
         * @param i
         * @param j
         */
        private void swap(int i, int j) {
            int tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }

        /**
         * @param i
         */
        private void upHeap(int i) {
            while (i > 0) {
                int p = parent(i);
                // 正常的堆节点之间的关系：父 <= 子
                if (heap[p] <= heap[i]) break;
                swap(i, p);
                i = p;
            }
        }

        private void downHeap(int i) {
            while (hasLeft(i)) {
                // compare its left and right child, get the smaller one's index
                int left = left(i);
                int smaller = left;
                if (hasRight(i)) {
                    int right = right(i);
                    smaller = heap[left] > heap[right] ? right : left;
                }
                if (heap[i] <= heap[smaller]) break;
                swap(i, smaller);
                i = smaller;
            }
        }

        private void insert(int num) {
            if (size == capacity) {
                // 如果堆已满，并且尝试加入的新元素的值 <= 堆顶元素，则说明该元素必不是第 k 大元素，因此可以直接丢弃。
                // 如果堆已满，并且尝试加入的新元素的值 > 堆顶元素，则说明第 k 大元素需要进行更新，此时移除堆顶元素，并插入新元素。
                if (num <= peek()) return;
                else removeTop();
            }
            // 插入新元素
            heap[size] = num;
            size++;
            upHeap(size - 1); // 恢复堆的 relational property
        }

        /**
         * remove the heap top and returns the original top element.
         *
         * @return
         */
        private int removeTop() {
            int ans = heap[0];
            swap(0, size - 1); // 交换堆顶和堆末尾元素
            size--; // 移除堆末尾元素
            downHeap(0); // 恢复堆的 relational property
            return ans;
        }

        private int peek() {
            return heap[0];
        }

        public int add(int val) {
            insert(val);
            return peek();
        }
    }

    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}
