package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/7/2020
 */
class AddAndSearchWordDataStructureDesignTest {
    AddAndSearchWordDataStructureDesign.WordDictionary dic =
            new AddAndSearchWordDataStructureDesign().new WordDictionary();

    @Test
    void search() {
        dic.addWord("bad");
        dic.addWord("dad");
        dic.addWord("mad");
        Assertions.assertFalse(dic.search("pad"));
        Assertions.assertTrue(dic.search("bad"));
        Assertions.assertTrue(dic.search(".ad"));
        Assertions.assertTrue(dic.search("b.."));
    }
}