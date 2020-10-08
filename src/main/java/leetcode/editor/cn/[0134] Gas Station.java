package leetcode.editor.cn;

/**
 * <pre>
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station
 * (i+1). You begin the journey with an empty tank at one of the gas stations.
 *
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
 * otherwise return -1.
 *
 * Note:
 *
 *     If there exists a solution, it is guaranteed to be unique.
 *     Both input arrays are non-empty and have the same length.
 *     Each element in the input arrays is a non-negative integer.
 *
 * Example 1:
 *
 * Input:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * Output: 3
 *
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 *
 * Example 2:
 *
 * Input:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * Output: -1
 *
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gas-station
 * </pre>
 */
class GasStation {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            int totalTank = 0;
            int currTank = 0;
            int startingStation = 0;
            for (int i = 0; i < n; ++i) {
                totalTank += gas[i] - cost[i];
                currTank += gas[i] - cost[i];
                // If one couldn't reach the next station
                if (currTank < 0) {
                    // Pick up the next station as the starting one
                    startingStation = i + 1;
                    // Start with an empty tank
                    currTank = 0;
                }
            }
            return totalTank >= 0 ? startingStation : -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class SimpleSolution {
        /**
         * 可以借鉴买卖股票问题的图思想，从 0 号加油站出发，一直到 N-1 号加油站，将油量画成折线图：
         * 1. 该折线的终点如果在 x 轴（加油站的序号）的上方或者刚好在 x 轴上，也就是 y >= 0（油量），
         * 表明 sum(gas) - sum(cost) >= 0
         * 2. 该折线的终点如果在 x 轴（加油站的序号）的下方，则直接返回 -1 即可
         * <p>
         * 仔细观察该折线的最低点：
         * 显然，如果我们从该点的下一个点出发，必定可以环绕一圈。（仔细想想为什么？）
         *
         * @param gas
         * @param cost
         * @return
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int len = gas.length;

            int remained = 0;
            int minRemained = Integer.MAX_VALUE;
            int minIndex = 0;

            for (int i = 0; i < len; i++) {
                remained += gas[i] - cost[i];
                if (remained < minRemained) {
                    minRemained = remained;
                    minIndex = i;
                }
            }
            return remained < 0 ? -1 : (minIndex + 1) % len;
        }
    }

}
