package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

/**
 * <pre>
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 *
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 * </pre>
 */
class RemoveDuplicatesFromSortedList {

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
                    cur.next = end; // 留一个
                    cur = end;
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
        /**
         * Recursion implementation.
         *
         * @param head
         * @return
         */
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) return head;
            else if (head.val == head.next.val) return deleteDuplicates(head.next);
            else {
                head.next = deleteDuplicates(head.next);
                return head;
            }
        }
    }

}
