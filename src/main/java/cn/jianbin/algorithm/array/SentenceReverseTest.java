package cn.jianbin.algorithm.array;


import java.util.Scanner;

public class SentenceReverseTest {

    public static void main(String[] args) {
        int[][] ints = m2(6);
        for (int i = 0; i < ints.length; i++) {
            int length = ints[i].length;
            for (int j = 0; j < length; j++) {
                int v = ints[i][j];
                if (v != 0) {
                    System.out.print(v + " ");
                }
            }
            System.out.println();
        }
    }

    public static String reverse(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return "";
        }

        String[] arr = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[arr.length - 1 - i]);

            if (i != arr.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    /**
     * 蛇形矩阵是由1开始的自然数依次排列成的一个矩阵上三角形。

     * 样例输入
     *
     * 5
     *
     * 1 3 6 10 15
     * 2 5 9 14
     * 4 8 13
     * 7 12
     * 11
     *
     * 数列1  2、 4、 7、 11、 16的通项公式是(n^2-n+2)/2。
     *
     *    1  3  6   10  15  通项公式： n(n+1)/2。
     */
    public static int[][] m2(int n) {
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = (i + 1), k = 0; j < n + 1; j++, k++) {
                int value = j * (j + 1) / 2 - i;
                result[i][k] = value;
            }
        }

        return result;
    }
}
