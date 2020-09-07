package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

/**
 * <pre>
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 *
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * </pre>
 */
class SortList {

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
        /**
         * Merge Sort
         *
         * @param head
         * @return
         */
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null)
                return head;
            // search for the mid node.
            ListNode fast = head, slow = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode tmp = slow.next;
            slow.next = null;
            // divide and conquer by recursion.
            ListNode left = sortList(head);
            ListNode right = sortList(tmp);
            // 引入哨兵节点 h
            ListNode h = new ListNode(0);
            ListNode res = h;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    h.next = left;
                    left = left.next;
                } else {
                    h.next = right;
                    right = right.next;
                }
                h = h.next;
            }
            h.next = left == null ? right : left;
            return res.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}