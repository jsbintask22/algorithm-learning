package cn.jianbin.algorithm.array;

import cn.jianbin.algorithm.utils.Utils;

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
