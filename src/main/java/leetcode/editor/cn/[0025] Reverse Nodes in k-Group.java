package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

class ReverseNodesInKGroup {

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
        public ListNode reverseKGroup(ListNode head, int k) {
            // return reverseKGroupIteratively(head, k);
            return reverseKGroupRecursively(head, k);
        }

        private ListNode reverseKGroupRecursively(ListNode head, int k) {
            ListNode header = new ListNode();
            header.next = head;

            ListNode current = header;
            for (int i = 0; i < k; i++) {
                current = current.next;
                // 尝试将 current 指向第 k 个节点，如果中途 current == null，说明整个链表不足 k 个节点
                if (current == null) return header.next;
            }

            // head 开头的链表至少有 k 个节点
            ListNode remaining = current.next; // remaining 是第 k+1 个节点
            current.next = null;
            reverseRecursively(head); // 反转后，current 变成了开头的节点，head 变成了末尾的节点
            head.next = reverseKGroupRecursively(remaining, k);
            return current;
        }

        /**
         * Reverse the entire list recursively.
         *
         * @param head
         * @return
         */
        private ListNode reverseRecursively(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode node = head.next;
            ListNode ans = reverseRecursively(node);
            node.next = head;
            head.next = null;
            return ans;
        }

        private ListNode reverseKGroupIteratively(ListNode head, int k) {
            ListNode header = new ListNode(0, head);

            ListNode current = header;
            while (current.next != null) {
                // 每个循环开始时，current 后续的 k 个节点需要进行翻转
                ListNode pre = current;
                head = current.next;
                for (int i = 0; i < k; i++) {
                    current = current.next;
                    if (current == null) return header.next;
                }
                ListNode remaining = current.next;

                // pre -> head -> ... -> current -> remaining，其中 head 到 current 为需要进行翻转处理的链表部分
                ListNode tail = head; // 先保存 head，翻转后，tail 将为末尾的节点
                pre.next = null;
                current.next = null;
                head = reverseIteratively(head);
                pre.next = head;
                tail.next = remaining;
                current = tail; // 更新 current 的位置
            }
            return header.next;
        }

        /**
         * Reverse the entire list iteratively.
         *
         * @param head
         * @return
         * @see ReverseLinkedList.Solution#reverseListIteratively(ListNode)
         */
        private ListNode reverseIteratively(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode current = head, pre = null;
            while (current != null) {
                ListNode nx = current.next;
                current.next = pre;
                pre = current;
                current = nx;
            }
            return pre;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
