package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubstringWithConcatenationOfAllWordsTest {
    @Test
    void findSubstring() {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        SubstringWithConcatenationOfAllWords.Solution solution =
                new SubstringWithConcatenationOfAllWords().new Solution();
        List<Integer> ans = solution.findSubstring(s, words);
        Assertions.assertEquals(2, ans.size());
        Assertions.assertTrue(ans.contains(0));
        Assertions.assertTrue(ans.contains(9));
    }
}