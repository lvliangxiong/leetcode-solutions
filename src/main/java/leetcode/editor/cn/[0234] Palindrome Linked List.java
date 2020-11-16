package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

/**
 * <pre>
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 *
 * Example 2:
 *
 * Input: 1->2->2->1
 * Output: true
 *
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * </pre>
 */
class PalindromeLinkedList {

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

        public boolean isPalindrome(ListNode head) {

            if (head == null) return true;

            // Find the end of first half and reverse second half.
            ListNode firstHalfEnd = endOfFirstHalf(head);
            ListNode secondHalfStart = reverseList(firstHalfEnd.next);

            // Check whether or not there is a palindrome.
            ListNode p1 = head;
            ListNode p2 = secondHalfStart;
            boolean result = true;
            while (result && p2 != null) {
                if (p1.val != p2.val) result = false;
                p1 = p1.next;
                p2 = p2.next;
            }

            // Restore the list and return the result.
            firstHalfEnd.next = reverseList(secondHalfStart);
            return result;
        }

        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
            }
            return prev;
        }

        /**
         * If there are 2n nodes in the list, return the n-th (indexed from 1) node.
         * If there are 2n+1 nodes in the list, return the (n+1)-th (indexed from 1) node.
         *
         * @param head
         * @return
         */
        private ListNode endOfFirstHalf(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}