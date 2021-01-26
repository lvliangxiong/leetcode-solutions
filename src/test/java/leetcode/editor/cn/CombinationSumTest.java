package leetcode.editor.cn;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinationSumTest {
    @Test
    void t() {
        CombinationSum.ddSolution solution = new CombinationSum().new ddSolution();
        for (List<Integer> integers : solution.combinationSum(new int[]{2, 5}, 10)) {
            System.out.println(integers);
        }
    }
}