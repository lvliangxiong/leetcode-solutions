package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

class OddEvenLinkedList {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * <pre>
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     * </pre>
     */
    class Solution {
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) return head;
            // At least three nodes
            ListNode insertPosition = head, pre = head.next;
            while (pre != null && pre.next != null) {
                // insert pre.next behind the insertPosition
                ListNode cur = pre.next;
                pre.next = cur.next;
                cur.next = null;

                ListNode next = insertPosition.next;
                insertPosition.next = cur;
                cur.next = next;

                insertPosition = insertPosition.next;
                pre = pre.next;
            }
            return head;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class AnotherSolution {
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) return head;
            ListNode oddListHead = head, evenListHead = head.next;
            ListNode oddListTail = oddListHead, evenListTail = evenListHead;

            ListNode cur = head.next.next; // 第 3 个节点
            int count = 1;

            oddListTail.next = null;
            evenListTail.next = null;

            while (cur != null) {
                ListNode next = cur.next;
                if ((count & 1) == 1) {
                    // odd
                    oddListTail.next = cur;
                    oddListTail = cur;
                } else {
                    // even
                    evenListTail.next = cur;
                    evenListTail = cur;
                }
                cur.next = null;
                cur = next;
                count++;
            }
            oddListTail.next = evenListHead;
            return oddListHead;
        }
    }

}