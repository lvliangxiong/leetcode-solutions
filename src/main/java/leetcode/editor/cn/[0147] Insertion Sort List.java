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
                ListNode cur = dummyHead; // 使用 cur 遍历已排序链表
                while (cur.next != null && head.val > cur.next.val) {
                    cur = cur.next; // 将 cur 更新到需要插入的位置的前一个节点处
                }
                // update ListNode structure
                ListNode tmp = head;
                head = head.next; // head 指向下一个需要插入排序链表的节点
                tmp.next = cur.next;
                cur.next = tmp;
            }
            return dummyHead.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
