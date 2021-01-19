package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

/**
 * <pre>
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * </pre>
 */
class AddTwoNumbers {
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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            return helper(l1, l2, 0);
        }

        private ListNode helper(ListNode l1, ListNode l2, int carry) {
            if (l1 == null && l2 == null && carry == 0) {
                return null;
            }
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            ListNode cur = new ListNode(sum % 10);
            cur.next = helper(l1 == null ? null : l1.next, l2 == null ? null : l2.next, sum / 10);
            return cur;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
