package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/23/2020
 */
class PermutationInStringTest {
    @Test
    void checkInclusion() {
        PermutationInString.Solution solution = new PermutationInString().new Solution();
        // Assertions.assertTrue(solution.checkInclusion("ab", "eidbaooo"));
        // Assertions.assertTrue(solution.checkInclusion("ab", "eidbiabooo"));
        Assertions.assertTrue(solution.checkInclusion("trinitrophenylmethylnitramine",
                "dinitrophenylhydrazinetrinitrophenylmethylnitramine"));
    }
}