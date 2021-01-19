package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/19/2020
 */
class PalindromePartitioningIiTest {
    @Test
    void minCut() {
        PalindromePartitioningIi.DPSolution solution = new PalindromePartitioningIi().new DPSolution();
        Assertions.assertEquals(1, solution.minCut("aab"));
        Assertions.assertEquals(452, solution.minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpb" +
                "gdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftc" +
                "ucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggb" +
                "feebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtla" +
                "liyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwh" +
                "xfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbix" +
                "clcxqrtniznemxeahfozp"));
    }
}