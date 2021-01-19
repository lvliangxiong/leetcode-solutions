package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

class IntersectionOfTwoLinkedLists {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * <pre>
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     * </pre>
     */
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode p = headA, q = headB;
            // A B 不相交时，p q 将会同时为 null，并退出循环
            while (p != q) {
                p = (p == null) ? headB : p.next;
                q = (q == null) ? headA : q.next;
            }
            return p;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}