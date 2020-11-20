package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

class RemoveDuplicateLettersTest {
    @Test
    void removeDuplicateLetters() {
        RemoveDuplicateLetters.StackSolution solution = new RemoveDuplicateLetters().new StackSolution();
        Assertions.assertEquals("abc", solution.removeDuplicateLetters("bcabc"));
    }
}