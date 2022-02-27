package cn.jianbin.algorithm.array;

import cn.jianbin.algorithm.utils.Utils;

/**
 * @author aaron.zou
 * @date 2021/12/11 8:53 下午
 * <p>
 * https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/solution/mian-shi-ti-04-er-wei-shu-zu-zhong-de-cha-zhao-zuo/
 */
public class SortMatrixFindNum {

    public static void main(String[] args) {
        int[][] matrix = Utils.gen2DArr(3, 5);
        Utils.print2DArr(matrix);
    }

    public boolean orderMatrixFindNum(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int i = 0;
        int j = matrix[0].length - 1;

        do {
            int val = matrix[i][j];
            if (val == target) {
                return true;
            }

            if (val > target) {
                j--;
            } else {
                i++;
            }
        }
        while (i < matrix.length && j >= 0);

        return false;
    }
}
