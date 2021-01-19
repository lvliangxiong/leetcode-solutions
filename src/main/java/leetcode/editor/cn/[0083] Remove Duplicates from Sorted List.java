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
            ListNode cur = head, ans = null, pre = null;
            while (cur != null) {
                // 移动 cur 到与当前节点相同的所有重复节点中最后一个节点
                while (cur != null && cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                if (ans == null) ans = cur;
                if (pre != null) pre.next = cur;
                pre = cur;
                cur = cur.next;
            }
            return ans == null ? head : ans;
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
