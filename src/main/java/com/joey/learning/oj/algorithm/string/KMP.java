package com.joey.learning.oj.algorithm.string;

/**
 * Knuth-Morris-Pratt Algorithm.
 * <p>
 * The core of KMP search is never back the pointer on txt when failed. It only change the state on pat to
 * the most appropriate state, and this is implemented by an extra array.
 */
public class KMP {
    /**
     * extra array record the state transition for every step in matching.
     * <p>
     * dp[state][ch] indicates the next state of matching when the current state encounters char ch.
     */
    private int[][] dp;
    private String pat;

    public KMP(String pat) {
        this.pat = pat;
        buildDP();
    }

    /**
     * build DP array, which has the information where the pattern pointer should be replaced when failed.
     */
    public void buildDP() {
        int M = pat.length();
        dp = new int[M][256];
        dp[0][pat.charAt(0)] = 1; // base case
        int x = 0;
        for (int state = 1; state < M; state++) {
            for (int ch = 0; ch < 256; ch++) {
                if (pat.charAt(state) == ch) {
                    dp[state][ch] = state + 1;
                } else {
                    dp[state][ch] = dp[x][ch];
                }
            }
            // search for longest repeat prefix
            x = dp[x][pat.charAt(state)];
        }
    }

    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        int state = 0; // current state, state == M means matching succeeds.
        for (int i = 0; i < N; i++) {
            state = dp[state][txt.charAt(i)]; // using dp to compute the next state
            if (state == M) return i - M + 1;
        }
        return -1; // matching fails.
    }
}