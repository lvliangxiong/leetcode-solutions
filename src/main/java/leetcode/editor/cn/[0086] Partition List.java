package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

class PartitionList {

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
        public ListNode partition(ListNode head, int x) {
            if (head == null || head.next == null) return head;
            // At least exist two nodes
            ListNode cur = head, pos = null, pre = null, ans = head;
            while (cur != null) {
                if (cur.val < x) {
                    // Found, then swap cur to position behind pos
                    if (pos != null && pos.next != cur) {
                        // 至少已经发现了一个值小于 x 的节点和一个值不小于 x 的节点
                        ListNode next = pos.next;
                        pos.next = cur;
                        pre.next = cur.next;
                        cur.next = next;
                        // Update pos and cur
                        pos = cur;
                        cur = pre.next;
                    } else if (pos == null && pre != null) {
                        // 目前遇到的节点（至少一个节点）的值都不小于 x（即 pos 仍然处于初始状态）
                        ans = cur;
                        pre.next = cur.next;
                        cur.next = head;
                        // Update pos and cur
                        pos = cur;
                        cur = pre.next;
                    } else {
                        // 1. 目前遇到的节点的值都小于 x（至少 1 个节点）
                        // 2. 初始位置
                        ans = cur;
                        pos = cur;
                        pre = cur;
                        cur = cur.next;
                    }
                } else {
                    // No ops except update pre and cur
                    pre = cur;
                    cur = cur.next;
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}