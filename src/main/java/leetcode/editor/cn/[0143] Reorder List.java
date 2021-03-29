package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

class ReorderList {

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
        /**
         * 1. 找到中间节点
         * 2. 翻转后一半的链表
         * 3. 合并两个链表
         *
         * @param head
         */
        public void reorderList(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) return;
            ListNode mid = middleNode(head);
            ListNode l1 = head;
            ListNode l2 = mid.next;
            mid.next = null;
            l2 = reverseList(l2);
            mergeList(l1, l2);
        }

        /**
         * 返回中间节点，如果节点数为奇数，那么返回中间节点，如果节点数为偶数，返回中间两个节点中靠前的一个！
         *
         * @param head
         * @return
         */
        public ListNode middleNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }

        public void mergeList(ListNode l1, ListNode l2) {
            ListNode next1;
            ListNode next2;
            while (l1 != null && l2 != null) {
                next1 = l1.next;
                next2 = l2.next;

                l1.next = l2;
                l1 = next1;

                l2.next = l1;
                l2 = next2;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}