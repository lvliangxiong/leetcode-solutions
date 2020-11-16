package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 9/1/2020
 */
class FindAllAnagramsInAStringTest {
    @Test
    void findAnagrams() {
        FindAllAnagramsInAString.Solution solution = new FindAllAnagramsInAString().new Solution();
        Assertions.assertIterableEquals(Arrays.asList(new Integer[]{3, 4, 6}),
                solution.findAnagrams("abaacbabc", "abc"));
    }
}