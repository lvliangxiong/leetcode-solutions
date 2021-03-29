package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

class ReverseLinkedListIi {

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
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (head == null || head.next == null) return head;
            ListNode header = new ListNode(0, head);
            ListNode cur = header, leftPre = null;
            for (int i = 0; i < right; i++) {
                if (i == left - 1) leftPre = cur;
                cur = cur.next;
            }
            ListNode le = leftPre.next, ri = cur;
            ListNode remaining = ri.next;

            leftPre.next = null;
            ri.next = null;

            reverseList(le); // After reverse, le becomes tail and ri becomes head

            leftPre.next = ri;
            le.next = remaining;

            return header.next;
        }

        private ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode tail = head.next;
            ListNode realHead = reverseList(tail);
            tail.next = head;
            head.next = null;
            return realHead;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class OptimisedSolution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;
            ListNode pre = dummyNode;
            for (int i = 0; i < left - 1; i++) {
                pre = pre.next; // 循环结束后，pre 指向第 left 个节点的前一个节点
            }
            // 在反转区间内，对遍历的每一个节点使用『头插法』，插入到 pre 后面
            ListNode cur = pre.next;
            ListNode next;
            for (int i = 0; i < right - left; i++) {
                next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }
            return dummyNode.next;
        }
    }

}