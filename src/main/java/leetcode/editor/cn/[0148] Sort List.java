package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

/**
 * <pre>
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 *
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * </pre>
 */
class SortList {

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
         * Merge Sort
         *
         * @param head
         * @return
         */
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null)
                return head;
            // search for the mid node.
            ListNode fast = head, slow = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            // Divide
            ListNode tmp = slow.next;
            slow.next = null;
            // Conquer by recursion
            ListNode left = sortList(head);
            ListNode right = sortList(tmp);
            // Merge two sorted list
            return mergeTwoOrderedList(left, right);
        }

        private ListNode mergeTwoOrderedList(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(0);
            ListNode cur = dummyHead;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    cur.next = l1;
                    l1 = l1.next;
                } else {
                    cur.next = l2;
                    l2 = l2.next;
                }
                cur = cur.next;
            }
            cur.next = l1 == null ? l2 : l1;
            return dummyHead.next;
        }


        /**
         * 自底而上的归并！
         *
         * @param head
         * @return
         */
        public ListNode mergeSortListOptimised(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            // 1. 首先从头向后遍历，统计链表长度
            int length = 0;
            ListNode node = head;
            while (node != null) {
                length++;
                node = node.next;
            }

            // 2. 初始化，引入 dummy head node
            ListNode dummyHead = new ListNode(0, head);

            // 3. 每次将链表拆分成若干个长度为 subLen 的子链表 , 并按照每两个子链表一组进行合并
            for (int subLen = 1; subLen < length; subLen <<= 1) {
                ListNode prev = dummyHead;
                ListNode cur = dummyHead.next;

                // 如果链表没有被拆完
                while (cur != null) {
                    // 3.1 拆分 subLen 长度的链表 1
                    ListNode h1 = cur; // 链表 1 的头
                    for (int i = 1; i < subLen && cur.next != null; i++) {
                        cur = cur.next; // 遍历结束后，cur 指向链表 1 的尾
                    }

                    // 3.2 拆分 subLen 长度的链表 2
                    ListNode h2 = cur.next; // 链表 2 的头
                    cur.next = null; // 断开链表 1 和链表 2，便于后面的处理
                    cur = h2;
                    for (int i = 1; i < subLen && cur != null && cur.next != null; i++) {
                        cur = cur.next; // 遍历结束后，cur 指向链表 2 的尾
                    }

                    // 3.3 提前保存 next，并断开链表 2 和后续的链表节点
                    ListNode next = null; // next 用于记录下一轮拆分的起始位置
                    if (cur != null) {
                        next = cur.next;
                        cur.next = null;
                    }

                    // 3.4 合并两个 subLen 长度的有序链表
                    ListNode merged = mergeTwoOrderedList(h1, h2);
                    prev.next = merged;
                    while (prev.next != null) {
                        prev = prev.next;
                    }
                    cur = next;
                }
            }
            // 返回新排好序的链表
            return dummyHead.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}