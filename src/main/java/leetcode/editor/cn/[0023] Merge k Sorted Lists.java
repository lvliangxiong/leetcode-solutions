package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <pre>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * </pre>
 */
class MergeKSortedLists {
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * <pre>
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     * </pre>
     */
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null) return null;
            int n = lists.length;
            if (n == 0) return null;
            if (n == 1) return lists[0];
            return mergeK(lists, 0, n - 1);
        }

        /**
         * 分治，两两合并，时间复杂度为 O(knlogk)，空间复杂度为 O(logk)
         *
         * @param lists
         * @param low
         * @param high
         * @return
         */
        private ListNode mergeK(ListNode[] lists, int low, int high) {
            if (low == high) return lists[low];
            if (low == high - 1) return mergeTwo(lists[low], lists[high]);
            int mid = (high + low) >>> 1;
            ListNode l1 = mergeK(lists, low, mid);
            ListNode l2 = mergeK(lists, mid + 1, high);
            return mergeTwo(l1, l2);
        }

        public ListNode mergeTwo(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            ListNode root;
            if (l1.val < l2.val) {
                root = l1;
                root.next = mergeTwo(l1.next, l2);
            } else {
                root = l2;
                root.next = mergeTwo(l1, l2.next);
            }
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 使用优先队列进行实现，也可以使用自己实现的 Heap 结构。
     * 时间复杂度为 O(knlogk)
     * 空间复杂度为 O(k)
     *
     * @see KthLargestElementInAStream.KthLargest#KthLargest(int, int[])
     */
    class HeapSolution {
        public ListNode mergeKLists(ListNode[] lists) {
            ListNode ans = null, current = null;
            PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
            for (ListNode head : lists) {
                if (head != null) heap.add(head);
            }
            if (heap.isEmpty()) return null;
            while (!heap.isEmpty()) {
                ListNode min = heap.poll(); // 当前 heap 中的最小节点
                if (ans == null) {
                    ans = min;
                    current = min;
                } else {
                    current.next = min; // 加入结果链表的后端
                    current = current.next; // 移动 current 指针
                }
                // 将 min 所在的链表的下一个节点加入 heap
                if (min.next != null) heap.add(min.next);
            }
            return ans;
        }
    }
}
