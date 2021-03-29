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
        /**
         * 1. 计算出链表长度 len
         * 2. 如果 len <= k，则 k %= len
         * 3. 如果 k == 0，直接返回 head
         * 4. 此时必有 len > k，因此找到倒数第 k+1 个节点，并将其后面的节点移动到前面来！
         *
         * @param head
         * @param k
         * @return
         */
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || k == 0) return head;
            ListNode cur = head;
            int len = 0;
            while (cur != null) {
                cur = cur.next;
                len++;
            }
            if (len <= k) k %= len;
            if (k == 0) return head;
            cur = head;
            for (int i = 0; i < k; i++) {
                cur = cur.next; // 循环完成后 head ==> 0-th，cur ==> k-th
            }
            ListNode slow = head, fast = cur;
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            ListNode tmp = slow.next;
            slow.next = null;
            fast.next = head;
            return tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}