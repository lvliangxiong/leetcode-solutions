package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

class SwapNodesInPairs {

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
        public ListNode swapPairs(ListNode head) {
            return swapPairsIteratively(head);
        }

        private ListNode swapPairsRecursively(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode ret = head.next;
            head.next = swapPairs(ret.next);
            ret.next = head;
            return ret;
        }

        private ListNode swapPairsIteratively(ListNode head) {
            // 增加一个哨兵节点
            ListNode header = new ListNode();
            header.next = head;

            if (header.next == null || header.next.next == null) return header.next;

            ListNode current = header;
            while (current != null && current.next != null && current.next.next != null) {
                // 每次遍历，交换 current 后面的两个节点
                ListNode node = current.next;
                current.next = node.next;
                node.next = current.next.next;
                current.next.next = node;

                current = current.next.next; // 更新 current 的位置
            }

            return header.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
