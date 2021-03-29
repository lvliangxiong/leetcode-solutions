package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

class InsertionSortList {

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
        public ListNode insertionSortList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode dummyHead = new ListNode(0);
            while (head != null) {
                // 每一个循环都寻找 head 在排序链表中应该被插入的位置，然后将 head 指向下一个节点
                ListNode cur = dummyHead;
                while (cur.next != null && head.val > cur.next.val) {
                    cur = cur.next; // 将 cur 更新到 head 需要被插入的位置的前一个节点处
                }
                // 将 head 插入到排序链表中
                ListNode tmp = head;
                // head 指向下一个需要插入排序链表的节点
                head = head.next;
                tmp.next = cur.next;
                cur.next = tmp;
            }
            return dummyHead.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
