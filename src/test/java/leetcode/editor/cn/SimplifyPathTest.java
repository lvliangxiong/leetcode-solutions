package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/8/2020
 */
class SimplifyPathTest {
    SimplifyPath.Solution solution = new SimplifyPath().new Solution();

    @Test
    void simplifyPath() {
        Assertions.assertEquals("/home", solution.simplifyPath("/home/"));
        Assertions.assertEquals("/home/foo", solution.simplifyPath("/home//foo/"));
        Assertions.assertEquals("/", solution.simplifyPath("/../"));
        Assertions.assertEquals("/c", solution.simplifyPath("/a/../../b/../c//.//"));
        Assertions.assertEquals("/a/b/c", solution.simplifyPath("/a//b////c/d//././/.."));
    }
}