package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermutationSequenceTest {
    @Test
    void getPermutation() {
        PermutationSequence.Solution solution = new PermutationSequence().new Solution();
        Assertions.assertEquals("2314", solution.getPermutation(4, 9));
        Assertions.assertEquals("123", solution.getPermutation(3, 1));
        Assertions.assertEquals("213", solution.getPermutation(3, 3));
    }
}