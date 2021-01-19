package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 */
//leetcode submit region begin(Prohibit modification and deletion)

/**
 * To implement O(1) with get, you need a HashMap data structure, and to implement O(1) with push,
 * you might need a Doubly-linked list like structure to get things done.
 */
class LRUCache {
    private DoublyLinkedListNode head;
    private DoublyLinkedListNode tail;
    private int capacity;
    private int size;

    private Map<Integer, DoublyLinkedListNode> nodes = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DoublyLinkedListNode();
        tail = new DoublyLinkedListNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DoublyLinkedListNode node = nodes.get(key);
        if (node != null) {
            update(node, node.value);
            return node.value;
        }
        return -1;
    }

    /**
     * update the node's value, and move the node to the head of the list.
     *
     * @param node
     * @param value
     */
    private void update(DoublyLinkedListNode node, int value) {
        node.value = value;
        unlink(node);
        link(node, head);
    }

    /**
     * unlink the given node from the list.
     *
     * @param node
     */
    private void unlink(DoublyLinkedListNode node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
        node.next = null;
        node.pre = null;
    }

    /**
     * insert a given node after the given previous node.
     *
     * @param node
     * @param previous
     */
    private void link(DoublyLinkedListNode node, DoublyLinkedListNode previous) {
        node.next = previous.next;
        node.pre = previous;
        previous.next.pre = node;
        previous.next = node;
    }

    public void put(int key, int value) {
        if (nodes.containsKey(key)) {
            // set new value, and update the ordering
            DoublyLinkedListNode node = nodes.get(key);
            update(node, value);
        } else {
            // insert a new node, take care of the capacity
            if (size == capacity) {
                invalidate();
            }
            insert(key, value);
        }
    }

    /**
     * insert the new node at the head.
     *
     * @param key
     * @param value
     */
    private void insert(int key, int value) {
        DoublyLinkedListNode node = new DoublyLinkedListNode(key, value);
        nodes.put(key, node);
        link(node, head);
        size++;
    }

    /**
     * invalidate the node at the tail, which is exactly the LRU, i.e. the least recently used node.
     */
    private void invalidate() {
        DoublyLinkedListNode lru = tail.pre;
        nodes.remove(lru.key);
        unlink(lru);
        size--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DoublyLinkedListNode current = head.next;
        while (current != tail) {
            sb.append(current).append("->");
            current = current.next;
        }
        if (sb.length() > 2) {
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    static class DoublyLinkedListNode {
        int key;
        int value;
        DoublyLinkedListNode pre;
        DoublyLinkedListNode next;

        public DoublyLinkedListNode() {
        }

        public DoublyLinkedListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "DoublyLinkedListNode{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
