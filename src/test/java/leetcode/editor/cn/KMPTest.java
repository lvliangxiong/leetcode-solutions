package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/24/2020
 */
class KMPTest {
    @Test
    void search() {
        KMP solution = new KMP("ABABC");
        Assertions.assertEquals(7, solution.search("ABABDABABABC"));
    }
}