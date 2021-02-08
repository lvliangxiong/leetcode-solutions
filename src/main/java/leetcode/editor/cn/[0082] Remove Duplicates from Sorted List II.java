package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

class RemoveDuplicatesFromSortedListIi {

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
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode ans = null, pre = null;
            while (head != null) {
                if (head.next != null && head.val == head.next.val) {
                    // Find duplicates, then remove all nodes whose val equals to cur.val
                    ListNode cur = head;
                    while (cur != null && cur.val == head.val) {
                        ListNode temp = cur.next;
                        cur.next = null; // 方便垃圾回收
                        cur = temp;
                    }
                    head = cur;
                    continue;
                }
                // Unique node found
                if (ans == null) {
                    ans = head;
                }
                if (pre != null) {
                    pre.next = head;
                }
                pre = head;
                head = head.next;
                pre.next = null;
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class RecursionSolution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) return head;
            if (head.val == head.next.val) {
                ListNode cur = head;
                while (cur != null && cur.val == head.val) {
                    cur = cur.next;
                }
                return deleteDuplicates(cur);
            } else {
                head.next = deleteDuplicates(head.next);
                return head;
            }
        }
    }

}