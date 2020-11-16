package leetcode.editor.cn;

/**
 * <pre>
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 *
 * search(word) can search a literal word or a regular expression string containing only letters a-z
 * or ..
 *
 * A . means it can represent any one letter.
 *
 * Example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 *
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-and-search-word-data-structure-design
 * </pre>
 */
class AddAndSearchWordDataStructureDesign {
    //leetcode submit region begin(Prohibit modification and deletion)
    class WordDictionary {
        TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new TrieNode();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            TrieNode current = root;
            for (char ch : word.toCharArray()) {
                current = current.put(ch);
            }
            current.setEnd(true);
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to
         * represent any one letter.
         */
        public boolean search(String word) {
            return search(root, word, 0);
        }

        private boolean search(TrieNode root, String word, int index) {
            if (index == word.length()) {
                return root.isEnd();
            }
            char ch = word.charAt(index);
            if (ch == '.') {
                for (TrieNode link : root.links) {
                    if (link != null && search(link, word, index + 1)) {
                        return true;
                    }
                }

            } else {
                if (root.containsKey(ch)) {
                    return search(root.get(ch), word, index + 1);
                }
            }
            return false;
        }
    }

    class TrieNode {
        private static final int R = 26;
        private TrieNode[] links;
        private boolean isEnd;

        public TrieNode() {
            this.links = new TrieNode[R];
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public TrieNode put(char ch) {
            if (containsKey(ch)) {
                return get(ch);
            } else {
                TrieNode node = new TrieNode();
                links[ch - 'a'] = node;
                return node;
            }
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}
