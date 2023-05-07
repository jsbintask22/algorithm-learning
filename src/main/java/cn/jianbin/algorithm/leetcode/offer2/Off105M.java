package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/5/7 16:52
 *
 * 剑指 Offer II 105. 岛屿的最大面积
 * 给定一个由 0 和 1 组成的非空二维数组 grid ，用来表示海洋岛屿地图。
 *
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出: 6
 * 解释: 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 * 示例 2:
 *
 * 输入: grid = [[0,0,0,0,0,0,0,0]]
 * 输出: 0
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1
 *
 *
 * 注意：本题与主站 695 题相同： https://leetcode-cn.com/problems/max-area-of-island/
 */
@UtilityClass
public class Off105M {

    public static void main(String[] args) {

    }

    public int maxAreaOfIsland(int[][] grid) {
        // 解法； 深度优先，以每个格子为起始点，往四周扩散，直到遇到 0 停止，
        // 每个探索过的格子都要重置为 0（防止重复探索）

        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(max, dfs(grid, i, j));
            }
        }

        return max;
    }

    public int dfs(int[][] grid, int i, int j) {
        // 检查是否到了 边界 或者 探索到了一个 0 ； 则本次探索结束返回 0;
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }

        // 走到这里，说明 肯定探索到了 “1”， 先把当前格子标记为 0
        grid[i][j] = 0;

        // 本轮探索加上自己 至少都是 1； 先把结果放这里；
        int ret = 1;

        // 从这个格子开始，再往四周探索；
        // 往上
        ret += dfs(grid, i - 1, j);
        // 往下
        ret += dfs(grid, i + 1, j);
        // 往左
        ret += dfs(grid, i, j - 1);
        // 往右
        ret += dfs(grid, i, j + 1);

        return ret;
    }
}
