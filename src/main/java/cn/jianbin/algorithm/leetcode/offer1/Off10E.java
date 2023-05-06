package cn.jianbin.algorithm.leetcode.offer1;

import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/5/6 7:15 PM
 *
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 * 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/
 */
@UtilityClass
public class Off10E {

    public static void main(String[] args) {
        System.out.println(numWays(46));
    }

    public int numWays(int n) {
        // 动态规划；
        // 青蛙可以一次跳 1 OR 2个台阶，假设当前为 第 N 个台阶
        // 则他可以由前面的  N- 1 OR N - 2 个台阶跳来；
        // 设 dp[i] 为跳到 第 i 个台阶的眺法； 则有
        //  dp[0] = 0  dp[1] = 1  dp[2] = 2

        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] % 1000000007 + dp[i - 2] % 1000000007;
        }

        return dp[n];
    }
}
