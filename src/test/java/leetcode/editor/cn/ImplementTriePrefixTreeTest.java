package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/7/2020
 */
class ImplementTriePrefixTreeTest {
    @Test
    void trie() {
        ImplementTriePrefixTree.Trie trie = new ImplementTriePrefixTree().new Trie();
        trie.insert("apple");
        Assertions.assertTrue(trie.search("apple"));
        Assertions.assertFalse(trie.search("app"));
        Assertions.assertTrue(trie.startsWith("app"));
        trie.insert("app");
        Assertions.assertTrue(trie.search("app"));
    }
}