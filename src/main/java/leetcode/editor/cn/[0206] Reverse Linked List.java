package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

class ReverseLinkedList {

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
        public ListNode reverseList(ListNode head) {
            // return reverseListRecursively(head);
            return reverseListIteratively(head);
        }

        private ListNode reverseListRecursively(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode tail = head.next;
            ListNode realHead = reverseListRecursively(tail);
            tail.next = head;
            head.next = null;
            return realHead;
        }

        private ListNode reverseListIteratively(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode cur = head, pre = null;
            while (cur.next != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            cur.next = pre;
            return cur;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}