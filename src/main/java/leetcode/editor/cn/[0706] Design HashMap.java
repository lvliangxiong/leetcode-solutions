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
    public static final double LOAD_FACTOR = 0.5;

    private Node[] table;
    private int size;
    private int capacity;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int capacity) {
        this.capacity = capacity;
        table = new Node[capacity];
    }

    private void grow(int newCapacity) {
        capacity = newCapacity;
        Node[] oldTable = table;
        table = new Node[capacity];
        size = 0; // 重置 size
        for (Node head : oldTable) {
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
            Node cur = head, tail = null;
            while (cur != null) {
                // found same key, then update value
                if (cur.key == key) {
                    cur.value = value;
                    return;
                }
                if (cur.next == null) tail = cur;
                cur = cur.next;
            }
            // not found, then insert behind the tail (cur)
            Node node = new Node(key, value);
            tail.next = node;
            node.pre = tail;
        }
        size++;
        if (size > LOAD_FACTOR * capacity) {
            grow(capacity << 1);
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int h = hash(key);
        Node head = table[h];
        if (head == null) return -1;
        Node cur = head;
        while (cur != null) {
            if (cur.key == key) return cur.value;
            cur = cur.next;
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
        Node cur = head;
        while (cur != null) {
            if (cur.key == key) {
                found = true;
                break; // found
            }
            cur = cur.next;
        }

        if (found) {
            // remove the node
            if (cur.next != null) {
                cur.next.pre = cur.pre;
            }
            if (cur.pre != null) {
                cur.pre.next = cur.next;
            } else {
                table[h] = cur.next;
            }
            cur.pre = null;
            cur.next = null;
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

