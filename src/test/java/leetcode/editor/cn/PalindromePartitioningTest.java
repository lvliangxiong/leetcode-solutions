package leetcode.editor.cn;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/18/2020
 */
class PalindromePartitioningTest {
    @Test
    void partition() {
        PalindromePartitioning.Solution solution = new PalindromePartitioning().new Solution();
        List<List<String>> list = solution.partition("aab");
        for (List<String> strings : list) {
            System.out.println(strings);
        }
    }
}