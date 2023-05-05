package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/5/5 8:31 PM
 *
 * 剑指 Offer II 098. 路径的数目
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：m = 3, n = 7
 * 输出：28
 * 示例 2：
 *
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * 示例 3：
 *
 * 输入：m = 7, n = 3
 * 输出：28
 * 示例 4：
 *
 * 输入：m = 3, n = 3
 * 输出：6
 *
 *
 * 提示：
 *
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 *
 *
 * 注意：本题与主站 62 题相同： https://leetcode-cn.com/problems/unique-paths/
 */
@UtilityClass
public class Off98M {

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }

    public int uniquePaths(int m, int n) {
        // 解法；动态规划；
        // 定义 dp[i][j] 为从 (0, 0) 叨叨 （i，j） 位置的路径；
        // 则有 dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        // 初始点； dp[0][0] 为 1（原地）

        // 为了方便期间，让 i 从1开始，则有 i <= m j <= n   dp[1][1] = 1;

        int[][] dp = new int[m + 1][n + 1];
        dp[1][1] = 1;

        for (int i = 1; i < m + 1; i++) {
            // j从2开始，因为 1，1 已经被我赋值了；
            for (int j = 1; j < n + 1; j++) {
                if (i != 1 || j != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }


        return dp[m][n];
    }
}
