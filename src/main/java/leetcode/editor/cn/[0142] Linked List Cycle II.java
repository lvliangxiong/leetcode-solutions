package leetcode.editor.cn;

class LinkedListCycleIi {

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
        /**
         * Floyd 算法
         *
         * @param head
         * @return
         */
        public Node detectCycle(Node head) {
            // 1. 判断是否有环
            if (head == null || head.next == null) return null;
            Node tortoise = head.next;
            Node rabbit = head.next.next;
            while (rabbit != null && rabbit.next != null && tortoise != rabbit) {
                tortoise = tortoise.next;
                rabbit = rabbit.next.next;
            }
            if (tortoise != rabbit) return null; // no loop
            // 2. 获得环的位置
            Node be = head;
            while (be != tortoise) {
                tortoise = tortoise.next;
                be = be.next;
            }
            return be;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
