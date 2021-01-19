package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/6/2020
 */
class PalindromePairsTest {
    @Test
    void palindromePairs() {
        PalindromePairs.Solution solution = new PalindromePairs().new Solution();
        Assertions.assertEquals(4,
                solution.palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"}).size());
    }
}