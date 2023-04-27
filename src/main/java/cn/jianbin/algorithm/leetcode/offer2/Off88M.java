package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aaron.zou
 * @date 2023/4/27 7:50 PM
 *
 * 剑指 Offer II 088. 爬楼梯的最少成本
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
 *
 * 每当爬上一个阶梯都要花费对应的体力值，一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者爬两个阶梯。
 *
 * 请找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 *
 *
 *
 * 示例 1：
 *
 * 输入：cost = [10, 15, 20]
 * 输出：15
 * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
 *  示例 2：
 *
 * 输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出：6
 * 解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
 *
 *
 * 提示：
 *
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 *
 *
 * 注意：本题与主站 746 题相同： https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 */
@UtilityClass
public class Off88M {

    public static void main(String[] args) {
        int[] arr = Utils.arr("1, 100, 1, 1, 1, 100, 1, 1, 100, 1");
        Utils.printArr(arr);
        System.out.println(minCostClimbingStairs(arr));


        dfs(arr, -1, 0, new ArrayList<>());

        System.out.println(min);
        System.out.println(ret);
    }


    public int minCostClimbingStairs(int[] cost) {
        // 解法； 假设数组长度为 n，则 题目实际为求解跳到下标 n 的最小代价；
        // 设 dp[n] 为跳到 n 的最小代价，总长度为  n + 1;
        // 当 n <= 2 时，则 dp[n] = Min(dp[0], dp[1]) + 0(代价)
        // 当 n > 2 时， 则 dp[n] = Min(dp[n - 1] + cost[n - 1], dp[n - 2] + cost[n - 2])

        int[] dp = new int[cost.length + 1];
        // 0 1 位置的代价为0（因为是起始点）

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[dp.length - 1];
    }


    public int minCostClimbingStairs2(int[] cost) {
        dfs(cost, -1, 0, new ArrayList<>());

        return min;
    }


    int min = Integer.MAX_VALUE;
    List<List<Integer>> ret = new ArrayList<>();
    public void dfs(int[] cost, int position, int totalCost, List<Integer> path) {
        // 解法2； 回溯，遍历每一条路径，达到最终点后 判断当前是否最小代价；
        if (position == cost.length) {
            // 达到终点，检查代价；
            if (totalCost < min) {
                min = totalCost;
                //ret.add(new ArrayList<>(path));
            }

            return;
        }

        // 还没到终点，继续选择路径，跳 1步 或者 2 步
        // 跳 1
        path.add(position < 0 ? -1 : cost[position]);
        dfs(cost, position + 1, totalCost + (position < 0 ? 0 : cost[position]), path);
        path.remove(path.size() - 1);

        // 跳 2 步（如果可以）
        if (position + 2 <= cost.length) {
            path.add(position < 0 ? -1 : cost[position]);
            dfs(cost, position + 2, totalCost + (position < 0 ? 0 : cost[position]), path);
            path.remove(path.size() - 1);
        }
    }

}
