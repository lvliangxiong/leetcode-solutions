package leetcode.editor.cn;

/**
 * <pre>
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 *
 * Note:
 *
 *     You may assume that all inputs are consist of lowercase letters a-z.
 *     All inputs are guaranteed to be non-empty strings.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * </pre>
 */
class ImplementTriePrefixTree {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Trie {
        private Node root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new Node();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Node current = root;
            for (char ch : word.toCharArray()) {
                current = current.put(ch);
            }
            current.setEnd(true);
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Node node = searchPrefix(word);
            return node != null && node.isEnd();
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Node node = searchPrefix(prefix);
            return node != null;
        }

        /**
         * 在 Trie 上搜索给定字符串前缀，如果存在此前缀，返回此前缀的最后一个节点。如果不存在，则返回 null。
         *
         * @param prefix
         * @return
         */
        private Node searchPrefix(String prefix) {
            Node current = root;
            for (char ch : prefix.toCharArray()) {
                if (current.containsKey(ch)) {
                    current = current.get(ch);
                } else {
                    return null;
                }
            }
            return current;
        }

        /**
         * 字符串中的字符存储在节点与节点之间的链接上，即 links 中。
         * <p>
         * 由于这个类位于一个 inner class 中，所以这个类无法声明为 static。事实上，这个类并没有使用到外部类的
         * 实例成员，因此声明为 static nested class 更好一些。
         */
        class Node {
            private final int R = 26;
            private Node[] links;
            private boolean isEnd;

            Node() {
                links = new Node[R];
            }

            public boolean containsKey(char ch) {
                return links[ch - 'a'] != null;
            }

            public Node get(char ch) {
                return links[ch - 'a'];
            }

            /**
             * 如果该字符已存在，直接返回该链接的下一个节点，如果不存在，则新建并返回。
             *
             * @param ch
             * @return
             */
            public Node put(char ch) {
                if (containsKey(ch)) {
                    return get(ch);
                } else {
                    Node node = new Node();
                    links[ch - 'a'] = node;
                    return node;
                }
            }

            public boolean isEnd() {
                return isEnd;
            }

            public void setEnd(boolean end) {
                isEnd = end;
            }
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}
