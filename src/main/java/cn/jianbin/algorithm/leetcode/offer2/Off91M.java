package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/4/28 8:18 PM
 *
 * 剑指 Offer II 091. 粉刷房子
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 *
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的正整数矩阵 costs 来表示的。
 *
 * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
 *
 * 请计算出粉刷完所有房子最少的花费成本。
 *
 *
 *
 * 示例 1：
 *
 * 输入: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * 输出: 10
 * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
 *      最少花费: 2 + 5 + 3 = 10。
 * 示例 2：
 *
 * 输入: costs = [[7,6,2]]
 * 输出: 2
 *
 *
 * 提示:
 *
 * costs.length == n
 * costs[i].length == 3
 * 1 <= n <= 100
 * 1 <= costs[i][j] <= 20
 *
 *
 * 注意：本题与主站 256 题相同：https://leetcode-cn.com/problems/paint-house/
 */
@UtilityClass
public class Off91M {


    public static void main(String[] args) {

    }

    public int minCost(int[][] costs) {
        // 解法； 每刷一个房子都跟之前刷的房子有关，因此可用 暴力枚举 每种可能然后找出
        // 最小代价；
        // 但是暴力的过程中有很多步骤是重复的，因此可使用 动态规划；

        // 动态规划 5 部曲
        // 1. 确定dp 数组以及 每个下标的含义；
        // 题目给的条件为 每个房子 i 刷成 j（0 - 2） 所花费的代价
        // 定义 dp[i][j] 为把第 i 个房子 刷成  j（0 - 2）颜色的最小代价；

        // 2. 找出能直接推出的 初始值；
        // 因为 i 的粉刷依靠前面已粉刷的房子；所以当 i = 0时，此时只依靠自己；
        // 则 dp[0][j] = costs[0][j]

        // 3. 确定状态转移方程（递推公式）
        // 当 i > 0 时，找出一般性 状态转移方程；
        // 假设 i 房子要选择 粉刷成 0 号； 那么 i - 1 这个房子只能选择 1 OR 2 号，
        // 则当前 dp[i][0] 的最小值为 从 i - 1中选一个颜色 然后 加上当前 i 的
        // 房子 的 0 号颜色让其 代价最小；
        // 则 dp[i][0] = cost[i][0] + Min(dp[i - 1][1], dp[i - 1][2]);
        // 在 dp[i][1] = cost[i][1] + Min(dp[i - 1][0], dp[i - 1][2]);
        // 则 dp[i][2] = cost[i][2] + Min(dp[i - 1][0], dp[i - 1][1]);

        // 4. 确定遍历规则（顺序）OR 赋值顺序
        // 这里 每个 dp[i][j] 都只跟 dp[i - 1][!= j] 相关，
        // 因此 i 从 0 - n 遍历，j 也从 0 - n 遍历；

        // 5. 确定结果；
        // 最小代价从 最后房子粉刷成 某个种颜色的最小代价中选择；
        // Min(dp[n][0], dp[n][1], dp[n][2])

        // - 1. 确定 dp 数组
        int[][] dp = new int[costs.length][3];

        // - 2. 确定初始值；
        for (int i = 0; i < 3; i++) {
            dp[0][i] = costs[0][i];
        }

        // - 3. 遍历方式
        for (int i = 1; i < dp.length; i++) {
            int[] minCs = dp[i];

            // -4. 状态转移方程.
            minCs[0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            minCs[1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            minCs[2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        // -5. 确定结果
        return Math.min(dp[dp.length - 1][0], Math.min(dp[dp.length - 1][1], dp[dp.length - 1][2]));
    }
}
