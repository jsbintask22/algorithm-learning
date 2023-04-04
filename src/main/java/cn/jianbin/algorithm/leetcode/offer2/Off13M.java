package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/4 20:38
 *
 * 剑指 Offer II 013. 二维子矩阵的和
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 *
 * 计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 * 实现 NumMatrix 类：
 *
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回左上角 (row1, col1) 、右下角 (row2, col2) 的子矩阵的元素总和。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入:
 * ["NumMatrix","sumRegion","sumRegion","sumRegion"]
 * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
 * 输出:
 * [null, 8, 11, 12]
 *
 * 解释:
 * NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * -105 <= matrix[i][j] <= 105
 * 0 <= row1 <= row2 < m
 * 0 <= col1 <= col2 < n
 * 最多调用 104 次 sumRegion 方法
 *
 */
@UtilityClass
public class Off13M {

    public static void main(String[] args) {
        int[][] matrix = Utils.gen2DArr(3, 4);
        Utils.print2DArr(matrix);

        System.out.println();
        NumMatrix pre = new NumMatrix(matrix);
        Utils.print2DArr(pre.pre);

        System.out.println(pre.sumRegion(1, 2, 2, 3));
    }

    public static class NumMatrix {
        /**
         *   1  2  3  4
         *   1  2  -3  4
         *   1  2  3  -4
         */
        int[][] pre;

        public NumMatrix(int[][] matrix) {
            pre = new int[matrix.length + 1][matrix[0].length + 1];

            for (int i = 1; i < pre.length; i++) {
                int[] row = pre[i];

                for (int j = 1; j < row.length; j++) {
                    pre[i][j] = pre[i][j - 1] + pre[i - 1][j] - pre[i - 1][j - 1] + matrix[i  -1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return pre[row2 + 1][col2 + 1] - pre[row2 + 1][col1] - pre[row1][col2 + 1] + pre[row1][col1];
        }
    }
}
