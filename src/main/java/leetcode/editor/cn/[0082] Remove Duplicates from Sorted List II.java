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
            ListNode dummyHead = new ListNode(0, head);
            ListNode cur = dummyHead;
            while (cur.next != null) {
                if (cur.next.next != null && cur.next.val == cur.next.next.val) {
                    // 存在重复值，则找到相同的所有节点中的最后一个
                    ListNode end = cur.next;
                    int num = end.val;
                    while (end.next != null && end.next.val == num) {
                        end = end.next;
                    }
                    cur.next = end.next; // 直接删除所有重复的节点
                } else {
                    // 下一个数不存在重复值
                    cur = cur.next;
                }
            }
            return dummyHead.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class RecursionSolution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) return head;
            if (head.val == head.next.val) {
                // 出现重复，直接跳过所有和 head 的值一样的节点
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