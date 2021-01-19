package leetcode.editor.cn;

import java.util.*;

/**
 * <pre>
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different
 * task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could
 * complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same
 * letter in the array), that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 *
 * Example 2:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 *
 * Example 3:
 *
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 *
 *
 *
 * Constraints:
 *
 *     1 <= task.length <= 10^4
 *     tasks[i] is upper-case English letter.
 *     The integer n is in the range [0, 100].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/task-scheduler
 * </pre>
 */
class TaskScheduler {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * Schedule by sorting and greedy.
         *
         * @param tasks
         * @param n
         * @return
         */
        public int leastInterval(char[] tasks, int n) {
            if (n == 0) return tasks.length;
            int[] counts = new int[26];
            for (char task : tasks) {
                counts[task - 'A']++;
            }
            Arrays.sort(counts);
            int ans = 0;
            while (counts[25]/*剩余数量最多的任务*/ > 0) {
                // n+1 个任务为一轮，一轮中，不能出现相同的任务
                int i = 0;
                while (i <= n) {
                    if (counts[25] == 0)
                        break;
                    // 尽量选择剩余数量较多的任务，如果各异的任务都选择了一遍，还无法凑齐 n+1 个任务，那么需要使用空闲时间片段
                    if (i < 26 && counts[25 - i] > 0)
                        counts[25 - i]--;
                    ans++;
                    i++;
                }
                Arrays.sort(counts);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class PriorityQueueSolution {
        public int leastInterval(char[] tasks, int n) {
            int[] counts = new int[26];
            for (char ch : tasks)
                counts[ch - 'A']++;
            // The head of the queue is the task whose count is the largest.
            PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
            for (int count : counts) {
                if (count > 0)
                    queue.add(count);
            }
            int ans = 0;
            while (!queue.isEmpty()) {
                List<Integer> temp = new ArrayList<>();
                int i = 0;
                // n+1 个任务为一轮
                while (i <= n) {
                    if (!queue.isEmpty()) {
                        // 将剩余数量最多的任务依次减 1
                        if (queue.peek() > 1)
                            temp.add(queue.poll() - 1);
                        else
                            queue.poll();
                    }
                    ans++;
                    if (queue.isEmpty() && temp.size() == 0) // 任务已分配完成
                        break;
                    i++;
                }
                for (int l : temp)
                    queue.add(l);
            }
            return ans;
        }
    }

    class DesignSolution {
        /**
         * https://pic.leetcode-cn.com/Figures/621_Task_Scheduler_new.PNG
         *
         * @param tasks
         * @param n
         * @return
         */
        public int leastInterval(char[] tasks, int n) {
            int[] counts = new int[26];
            for (char ch : tasks)
                counts[ch - 'A']++;
            Arrays.sort(counts);
            int rows = counts[25] - 1, idleCount = rows * n;
            for (int i = 24; i >= 0 && counts[i] > 0; i--) { // 将其它任务填充到空闲时间中
                idleCount -= Math.min(counts[i], rows);
            }
            return idleCount > 0 ? idleCount + tasks.length : tasks.length;
        }
    }

}
