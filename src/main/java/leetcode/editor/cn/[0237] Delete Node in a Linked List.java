package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

class DeleteNodeInALinkedList {

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
        public void deleteNode(ListNode node) {
            while (node.next.next != null) {
                node.val = node.next.val;
                node = node.next;
            }
            // node 为倒数第二个节点
            node.val = node.next.val;
            node.next = null;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
