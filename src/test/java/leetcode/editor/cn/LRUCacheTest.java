package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/10/2020
 */
class LRUCacheTest {
    @Test
    void lru() {
        LRUCache cache = new LRUCache(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        Assertions.assertEquals(1, cache.get(1));
        cache.put(3, 3);    // evicts key 2
        Assertions.assertEquals(-1, cache.get(2));
        cache.put(4, 4);    // evicts key 1
        Assertions.assertEquals(-1, cache.get(1));
        Assertions.assertEquals(3, cache.get(3));
        Assertions.assertEquals(4, cache.get(4));
    }
}