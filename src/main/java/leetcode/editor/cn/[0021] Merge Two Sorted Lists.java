package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

/**
 * <pre>
 * Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together
 * the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * </pre>
 */
class MergeTwoSortedLists {
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
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            ListNode root;
            if (l1.val < l2.val) {
                root = l1;
                root.next = mergeTwoLists(l1.next, l2);
            } else {
                root = l2;
                root.next = mergeTwoLists(l1, l2.next);
            }
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
