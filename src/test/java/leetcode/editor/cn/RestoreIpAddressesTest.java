package leetcode.editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/9/2020
 */
class RestoreIpAddressesTest {
    @Test
    void restoreIpAddresses() {
        RestoreIpAddresses.Solution solution = new RestoreIpAddresses().new Solution();
        List<String> ips = solution.restoreIpAddresses("25525511135");
        Assertions.assertEquals(2, ips.size());
        for (String ip : ips) {
            for (String num : ip.split(".")) {
                Assertions.assertTrue(Integer.parseInt(num) <= 255);
                Assertions.assertTrue(Integer.parseInt(num) >= 0);
            }
        }
        Assertions.assertIterableEquals(new ArrayList() {{
            add("0.0.0.0");
        }}, solution.restoreIpAddresses("0000"));
    }
}