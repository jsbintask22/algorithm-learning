package cn.jianbin.algorithm.array;

import lombok.SneakyThrows;

import java.util.Scanner;

/**
 * @author jianbin
 * @date 2020/9/3 20:03
 */
public class YangHuiTest {
    @SneakyThrows
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            print(scanner.nextInt());
        }
    }

    public static void print(int n) {
        int[][] counts = new int[n][n];

        for (int i = 0; i < n; i++) {
           counts[i][0] = 1;
            for (int j = 0; j < i + 1; j++) {
                if (j ==0 || j == i) {
                    counts[i][j] = 1;
                } else {
                    counts[i][j] = counts[i - 1][j] + counts[i - 1][j - 1];
                }
            }
        }

        for (int i = 0; i < counts.length; i++) {
            int[] count = counts[i];
            for (int i1 = 0; i1 < count.length; i1++) {
                int v = counts[i][i1];
                if (v != 0) {
                    System.out.print(v + " ");
                }
            }
            System.out.println();
        }

    }
}
