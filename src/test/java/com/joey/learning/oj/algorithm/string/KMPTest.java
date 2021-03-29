package com.joey.learning.oj.algorithm.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KMPTest {
    @Test
    void search() {
        KMP solution = new KMP("ABABC");
        Assertions.assertEquals(7, solution.search("ABABDABABABC"));
    }
}