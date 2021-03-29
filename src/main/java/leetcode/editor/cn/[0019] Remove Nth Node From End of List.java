package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

/**
 * <pre>
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * </pre>
 */
class RemoveNthNodeFromEndOfList {

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
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode cur = head;
            for (int i = 0; i < n; i++) {
                cur = cur.next; // cur 是链表中的第 n+1 个节点
            }
            if (cur == null) return head.next; // remove the first node

            // 当 cur 移动到最后一个节点时，preN 刚好是倒数第 n+1 个节点
            ListNode preN = head;
            while (cur.next != null) {
                preN = preN.next;
                cur = cur.next;
            }
            // remove the node after preN
            preN.next = preN.next.next;
            return head;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
