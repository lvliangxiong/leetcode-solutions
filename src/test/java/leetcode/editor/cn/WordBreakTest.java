package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/16/2020
 */
class WordBreakTest {
    @Test
    void wordBreak() {
        WordBreak.Solution solution = new WordBreak().new Solution();
        Assertions.assertEquals(true, solution.wordBreak("leetcode",
                Arrays.asList(new String[]{"leet", "code"})));
    }
}