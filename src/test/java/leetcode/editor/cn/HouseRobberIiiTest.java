package leetcode.editor.cn;

import com.joey.learning.oj.util.tree.TreeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/5/2020
 */
class HouseRobberIiiTest {
    @Test
    void rob() {
        Assertions.assertEquals(7,
                new HouseRobberIii().new Solution().rob(TreeUtils.toTree(new Integer[]{3, 2, 3, null, 3, null, 1})));
    }
}