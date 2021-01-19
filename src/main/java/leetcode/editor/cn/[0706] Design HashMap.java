package leetcode.editor.cn;

/**
 * <pre>
 * Design a HashMap without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 *     put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap,
 *     update the value.
 *
 *     get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping
 *     for the key.
 *
 *     remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 *
 *
 * Example:
 *
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);
 * hashMap.put(2, 2);
 * hashMap.get(1);            // returns 1
 * hashMap.get(3);            // returns -1 (not found)
 * hashMap.put(2, 1);          // update the existing value
 * hashMap.get(2);            // returns 1
 * hashMap.remove(2);          // remove the mapping for 2
 * hashMap.get(2);            // returns -1 (not found)
 *
 *
 * Note:
 *
 *     All keys and values will be in the range of [0, 1000000].
 *     The number of operations will be in the range of [1, 10000].
 *     Please do not use the built-in HashMap library.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-hashmap
 * </pre>
 */

//leetcode submit region begin(Prohibit modification and deletion)
class MyHashMap {
    static final double LOAD_FACTOR = 0.5;

    Node[] table;
    int size;
    int capacity;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        this(17);
    }

    public MyHashMap(int capacity) {
        this.capacity = capacity;
        table = new Node[capacity];
    }

    private void grow(int newCapacity) {
        Node[] buffer = table;
        capacity = newCapacity;
        table = new Node[capacity];
        int old = size;
        size = 0; // 重置 size
        for (Node head : buffer) {
            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int h = hash(key);
        Node head = table[h];
        if (head == null) {
            // insert
            table[h] = new Node(key, value);
        } else {
            Node tail = head;
            while (head != null) {
                // found same key, then update value
                if (head.key == key) {
                    head.value = value;
                    return;
                }
                tail = head;
                head = head.next;
            }
            // not found, then insert at the tail
            Node node = new Node(key, value);
            tail.next = node;
            node.pre = tail;
        }
        size++;
        if (size > LOAD_FACTOR * capacity) {
            grow((capacity << 1) - 1);
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int h = hash(key);
        Node head = table[h];
        if (head == null) return -1;
        while (head != null) {
            if (head.key == key) return head.value;
            head = head.next;
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int h = hash(key);
        Node head = table[h];
        if (head == null) return; // not found
        boolean found = false;
        Node target = head;
        while (target != null) {
            if (target.key == key) {
                found = true;
                break; // found
            }
            target = target.next;
        }
        if (found) {
            // remove the node
            if (target.next != null) {
                target.next.pre = target.pre;
            }
            if (target.pre != null) {
                target.pre.next = target.next;
            } else {
                table[h] = target.next;
            }
            target.pre = null;
            target.next = null;
            size--;
        }
    }

    private int hash(int key) {
        // return (key ^ (key >>> 16)) & (capacity - 1);
        return key % capacity;
    }
}

class Node {
    int key;
    int value;
    Node pre;
    Node next;

    public Node() {
    }

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
//leetcode submit region end(Prohibit modification and deletion)

