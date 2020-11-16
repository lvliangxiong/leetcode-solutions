package leetcode.editor.cn;

import java.util.Iterator;

/**
 * <pre>
 * Design a HashSet without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 *     add(value): Insert a value into the HashSet.
 *     contains(value) : Return whether the value exists in the HashSet or not.
 *     remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.
 *
 *
 * Example:
 *
 * MyHashSet hashSet = new MyHashSet();
 * hashSet.add(1);
 * hashSet.add(2);
 * hashSet.contains(1);    // returns true
 * hashSet.contains(3);    // returns false (not found)
 * hashSet.add(2);
 * hashSet.contains(2);    // returns true
 * hashSet.remove(2);
 * hashSet.contains(2);    // returns false (already removed)
 *
 *
 * Note:
 *
 *     All values will be in the range of [0, 1000000].
 *     The number of operations will be in the range of [1, 10000].
 *     Please do not use the built-in HashSet library.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-hashset
 * </pre>
 */

//leetcode submit region begin(Prohibit modification and deletion)

class MyHashSet {
    private static int STOP = -1;
    SimpleSet[] table;
    int size;
    int capacity;

    /**
     * Initialize your data structure here.
     */
    @SuppressWarnings("unchecked")
    public MyHashSet() {
        this(17);
    }

    public MyHashSet(int capacity) {
        this.capacity = capacity;
        table = new SimpleSet[capacity];
    }

    private void resize(int newCapacity) {
        capacity = newCapacity;
        SimpleSet[] buffer = table;
        table = new SimpleSet[newCapacity];
        size = 0;
        for (SimpleSet set : buffer) {
            if (set != null) {
                for (Integer num : set) {
                    add(num);
                }
            }
        }
    }

    public void add(int key) {
        int h = hash(key);
        SimpleSet set = table[h];
        if (set == null) set = table[h] = new SimpleSet();
        if (set.add(key)) {
            size++;
            if (size == (capacity >>> 1))
                resize((capacity << 1) - 1);
        }
    }


    public void remove(int key) {
        int h = hash(key);
        SimpleSet set = table[h];
        if (set == null) return;
        if (set.remove(key))
            size--;
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int h = hash(key);
        SimpleSet set = table[h];
        if (set == null) return false;
        return set.contains(key);
    }

    private int hash(int key) {
        return key % capacity;
    }
}

/**
 * Bucket 的实现可以使用内置的 LinkedList，ArrayList 等容器，还可以自己创建一个 BST 作为 Bucket。
 * 这里自己实现了一个简单的 Set 容器，实现类似于 ArrayList。
 */
class SimpleSet implements Iterable<Integer> {
    int[] arr;
    int size;
    int capacity;

    public SimpleSet() {
        this(8);
    }

    public SimpleSet(int capacity) {
        this.capacity = capacity > 4 ? capacity : 4;
        arr = new int[capacity];
    }

    public boolean remove(int num) {
        if (size == 0) return false;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arr[i] == num) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            // num 存在
            swap(index, size - 1);
            size--;
            return true;
        }
        return false;
    }

    private void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public boolean add(int num) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == num) return false; // 已存在，不做任何操作
        }
        arr[size++] = num;
        if (size == capacity) resize(capacity << 1);
        return true;
    }

    private void resize(int newCapacity) {
        capacity = newCapacity;
        int[] buff = arr;
        arr = new int[newCapacity];
        System.arraycopy(buff, 0, arr, 0, size);
    }

    public boolean contains(int num) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == num) return true;
        }
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Itr();
    }

    class Itr implements Iterator<Integer> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Integer next() {
            return arr[i++];
        }
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
//leetcode submit region end(Prohibit modification and deletion)