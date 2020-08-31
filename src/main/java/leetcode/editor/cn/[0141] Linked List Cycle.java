package leetcode.editor.cn;

class LinkedListCycle {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * <pre>
     * Definition for singly-linked list.
     * class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     * </pre>
     */
    public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) return false;
            ListNode tortoise = head.next;
            ListNode rabbit = head.next.next;
            while (rabbit != null && rabbit.next != null && rabbit != tortoise) {
                tortoise = tortoise.next;
                rabbit = rabbit.next.next;
            }
            return rabbit == tortoise ? true : false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
