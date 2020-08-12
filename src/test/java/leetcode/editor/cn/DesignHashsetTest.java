package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/12/2020
 */
class DesignHashsetTest {
    @Test
    void myHashSet() {
        MyHashSet set = new MyHashSet();
        set.add(1);
        set.add(2);
        Assertions.assertEquals(true, set.contains(1));
        Assertions.assertEquals(false, set.contains(3));
        set.add(2);
        Assertions.assertEquals(true, set.contains(2));
        set.remove(2);
        Assertions.assertEquals(false, set.contains(2));
    }
}