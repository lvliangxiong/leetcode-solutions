package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

/**
 * <pre>
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 *
 * Example 2:
 *
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 * </pre>
 */
class RotateList {

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
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || k == 0) return head;
            ListNode curr = head;
            int i = 0;
            // Find the k-th node start from the head.
            while (i < k && curr != null) {
                curr = curr.next;
                i++;
            }
            // If the number of nodes equals to k or 1, just return head.
            if ((i == 1 || i == k) && curr == null) return head;
            // the number of nodes is lager than k, reduce the scale of k and retry.
            if (i < k) {
                k %= i;
                if (k == 0) return head;
                i = 0;
                curr = head;
                while (i < k && curr != null) {
                    curr = curr.next;
                    i++;
                }
            }
            // Find the k-th node start from the end.
            ListNode slow = head, fast = curr;
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            // slow is the k-th node from the end, and fast the is the end.
            ListNode tmp = slow.next;
            slow.next = null;
            fast.next = head;
            return tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}