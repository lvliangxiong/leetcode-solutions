package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScrambleStringTest {
    @Test
    void isScramble() {
        ScrambleString.Solution solution = new ScrambleString().new Solution();
        Assertions.assertEquals(true, solution.isScramble("abc", "bca"));
    }
}