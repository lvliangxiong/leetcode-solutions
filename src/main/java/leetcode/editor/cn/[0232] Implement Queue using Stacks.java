package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <pre>
 * Implement the following operations of a queue using stacks.
 *
 *     push(x) -- Push element x to the back of queue.
 *     pop() -- Removes the element from in front of queue.
 *     peek() -- Get the front element.
 *     empty() -- Return whether the queue is empty.
 *
 * Example:
 *
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // returns 1
 * queue.pop();   // returns 1
 * queue.empty(); // returns false
 *
 * Notes:
 *
 *     You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size,
 *     and is empty operations are valid.
 *
 *     Depending on your language, stack may not be supported natively. You may simulate a stack by using a list
 *     or deque (double-ended queue), as long as you use only standard operations of a stack.
 *
 *     You may assume that all operations are valid (for example, no pop or peek operations will be called on an
 *     empty queue).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
 * </pre>
 */
class ImplementQueueUsingStacks {
    //leetcode submit region begin(Prohibit modification and deletion)
    class MyQueue {
        Deque<Integer> pushStack;
        Deque<Integer> popStack;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            pushStack = new ArrayDeque();
            popStack = new ArrayDeque();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            pushStack.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            checkAndFix();
            return popStack.pop();
        }

        /**
         * make sure the popStack has element to be peeked or popped out
         */
        private void checkAndFix() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }

        /**
         * Get the front element.
         */
        public int peek() {
            checkAndFix();
            return popStack.peekFirst();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return popStack.isEmpty() && pushStack.isEmpty();
        }
    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}
