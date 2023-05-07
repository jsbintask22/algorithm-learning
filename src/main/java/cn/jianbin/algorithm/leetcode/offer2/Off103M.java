package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.Arrays;

/**
 * @author jianbin.
 * @date 2023/5/7 14:35
 *
 * 剑指 Offer II 103. 最少的硬币数目
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 *
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 *
 * 输入：coins = [1], amount = 2
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 *
 *
 * 注意：本题与主站 322 题相同： https://leetcode-cn.com/problems/coin-change/
 */
@UtilityClass
public class Off103M {

    public static void main(String[] args) {
        int[] coins = Utils.arr("2");

        System.out.println(coinChange(coins, 3));
    }

    public int coinChange(int[] coins, int amount) {

        if (amount <= 0) {
            return 0;
        }

        // 解法；动态规划；
        // 对于背包问题而言，可分为 01 背包 和 完全 背包；
        // 指的是 每样物品只能选择 0 OR 1 次
        // 指的是 每样物品可选择  0 OR 1 OR 2 OR n 次；

        // 对于两种不同选择方式，定义特定状态转移方程；
        // 设 dp[i][j] 为 前 i （包括） 物品重量在 j 内 所能囊括的最大价值；
        // 01 背包：  dp[i][j] = Max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + values[i - 1])
        // 完全背包： dp[i][j] = Max(dp[i - 1][j], dp[i][j - weights[i - 1]] + values[i - 1]);

        // 另外，对于结果的 解 不同，初始化状态也不同。
        // 1. 如果要求恰好装满 背包， 则应该初始化 dp[i][0] = 0，表示要想得到 价值为 0 的组合，那只能 背包容量也为 0（因为恰好），
        // 其他的都需要填充 dp[i][j] = Min.Value (一个反向的不可能的值）

        // 2. 如果只是要求容量不超过 不最大值的情况下使得价值最大，初始值 dp[i][j] = 0，此时表示 对于价值 为0， 在每个容量下都可以什么都不选

        // 硬币问题为 完全背包（可以选很多次） 并且要求 恰好填满背包。 所以需要填充初始值；

        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        for (int i = 0; i < dp.length; i++) {
            // 填充一个不可能的值(反向的）
            Arrays.fill(dp[i], amount + 1);

            // 表示为了价值为0，那只能 容量也为 0 ；
            dp[i][0] = 0;
        }


        // 开始遍历；
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= amount; j++) {
                // 检查是否还有足够多的容量；
                if (coins[i - 1] > j) {
                    // 没得选，只能当前不选l;
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 有得选，那就看谁更小（符合结果）
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                }
            }
        }

        // 返回结果；
        return dp[len][amount] == amount + 1 ? -1 : dp[len][amount];
    }
}
