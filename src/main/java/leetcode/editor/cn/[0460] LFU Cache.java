package leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * <pre>
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 *     LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 *     int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 *     void put(int key, int value) Update the value of the key if present, or inserts the key if not already present.
 *      When the cache reaches its capacity, it should invalidate and remove the least frequently used key before
 *      inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency),
 *      the least recently used key would be invalidated.
 *
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the
 * smallest use counter is the least frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter
 * for a key in the cache is incremented either a get or put operation is called on it.
 *
 *
 * Example 1:
 *
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 *                  // cache=[3,4], cnt(4)=2, cnt(3)=3
 *
 *
 * Constraints:
 *
 *     0 <= capacity, key, value <= 10^4
 *     At most 10^5 calls will be made to get and put.
 *
 *
 * Follow up: Could you do both operations in O(1) time complexity?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lfu-cache
 * </pre>
 */
class LfuCache {

    //leetcode submit region begin(Prohibit modification and deletion)
    class LFUCache {
        Map<Integer, Node> cache = new HashMap<>(); // 存储缓存的内容
        Map<Integer, LinkedHashSet<Node>> freqMap = new HashMap<>(); // 存储每个频次对应的双向链表

        int size;
        int capacity;
        int min; // 存储当前最小频次

        public LFUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            freqInc(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            // 如果 key 已经存在， 那么将更新 key 对应的 node 的 value，并更新频次数据
            Node node = cache.get(key);
            if (node != null) {
                node.value = value;
                freqInc(node);
                return;
            }
            // 插入 key - value 对
            if (size == capacity) {
                // LFU 已满，先清理 LFU
                removeNode();
            }
            addNode(new Node(key, value));
            min = 1; // 由于 removeNode 都是在 addNode 的情况下才会调用的，因此这里总是 1
        }

        void freqInc(Node node) {
            // 从原 freq 对应的链表里移除
            int freq = node.freq;
            LinkedHashSet<Node> set = freqMap.get(freq);
            set.remove(node);

            // 更新 min
            if (freq == min && set.size() == 0) {
                min = freq + 1;
            }

            // 加入新 freq 对应的链表
            node.freq++;
            LinkedHashSet<Node> newSet = freqMap.computeIfAbsent(node.freq, k -> new LinkedHashSet<>());
            newSet.add(node);
        }

        void addNode(Node node) {
            LinkedHashSet<Node> set = freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>());
            set.add(node);
            cache.put(node.key, node);
            size++;
        }

        void removeNode() {
            LinkedHashSet<Node> set = freqMap.get(min);
            Node deadNode = set.iterator().next(); // LinkedHashSet 中的第一个节点，即为 LRU node
            set.remove(deadNode);
            cache.remove(deadNode.key);
            size--;
        }
    }

    static class Node {
        int key;
        int value;
        int freq = 1;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Your LFUCache object will be instantiated and called as such:
     * LFUCache obj = new LFUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}