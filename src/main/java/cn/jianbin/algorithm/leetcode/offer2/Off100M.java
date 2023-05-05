package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aaron.zou
 * @date 2023/5/5 8:58 PM
 *
 * 剑指 Offer II 100. 三角形中最小路径之和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 */
@UtilityClass
public class Off100M {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(2);
        List<Integer> row2 = new ArrayList<>();
        row2.add(3);
        row2.add(4);

        List<Integer> row3 = new ArrayList<>();
        row3.add(6);
        row3.add(5);
        row3.add(7);

        triangle.add(row1);
        triangle.add(row2);
        triangle.add(row3);

        System.out.println(triangle);


        System.out.println(minimumTotal(triangle));
    }


    public int minimumTotal(List<List<Integer>> triangle) {
        // 解法；动态规划；
        // 1
        // 2 3
        // 3 5 6

        // 定义 dp[i][j] 为 到达 (i, j) 最小路径和；
        // 则有 dp[i][j] = arr[i][j] + Min(arr[i - 1][j], arr[i - 1][j - 1])
        // 发现 i j 只跟前面 一个元素有关，所以定义 i <= n j <= n

        // 赋值 dp[1][1] = arr[0][0]
        // 并且有 i >= j

        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];
        
        dp[1][1] = triangle.get(0).get(0);

        for (int i = 0; i < n + 1; i++) {
          /*  if (i != 0) {
                dp[i] = new int[triangle.get(i - 1).size()];
            }*/

            for (int j = 0; j <= i; j++) {
                if (i == 0 || j == 0) {
                    // 伪装成最大值；
                    dp[i][j] = Integer.MAX_VALUE;
                } else if (i != 1 || j != 1) {
                    // 不能是 （1，1） 已经被赋值过了；
                    dp[i][j] = triangle.get(i - 1).get(j - 1) + Math.min((i - 1 >= j) ? dp[i - 1][j] : Integer.MAX_VALUE, dp[i - 1][j - 1]);
                }
            }
        }


        // 结果为最后一行的最小值；
        int min = dp[n][0];
        for (int i = 1; i < n + 1; i++) {
            min = Math.min(min, dp[n][i]);
        }

        return min;
    }
}
