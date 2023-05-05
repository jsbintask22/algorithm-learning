package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/5/5 8:43 PM
 *
 * 剑指 Offer II 099. 最小路径之和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：一个机器人每次只能向下或者向右移动一步。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 *
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 *
 *
 * 注意：本题与主站 64 题相同： https://leetcode-cn.com/problems/minimum-path-sum/
 */
@UtilityClass
public class Off99M {

    public static void main(String[] args) {
        int[][] matrix = Utils.gen2DArr(3, 3);
        Utils.print2DArr(matrix);

        System.out.println(minPathSum(matrix));
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 解法；动态规划；
        // 定义 dp[i][j] 为从 (0, 0) 叨叨 （i，j） 位置的路径；
        // 则有 dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        // 初始点； dp[0][0] 为 1（原地）

        // 为了方便期间，让 i 从1开始，则有 i <= m j <= n   dp[1][1] = 1;

        int[][] dp = new int[m + 1][n + 1];
        dp[1][1] = grid[0][0];




        for (int i = 0; i < m + 1; i++) {
            // j从2开始，因为 1，1 已经被我赋值了；
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) {
                    // 伪装一个最大值；
                    dp[i][j] = Integer.MAX_VALUE;
                } else if (i != 1 || j != 1) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
                }
            }
        }


        return dp[m][n];
    }


}
