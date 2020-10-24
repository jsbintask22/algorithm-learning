package cn.jianbin.algorithm.array;

import lombok.SneakyThrows;

/**
 * @author jianbin
 * @date 2020/9/12 17:46
 */
public class CharSwapTest {

    @SneakyThrows
    public static void main(String[] args) {
        int[] ori = new int[]{5, 7, 10, 20, 4, 1, 0, 3};
        int[] ori2 = new int[]{1, 2, 3, 4};
        int[] ori3 = new int[]{3};

        print(swap(ori));
        print(swap(ori2));
        print(swap(ori3));

    }


    public static int[] swap(int[] ori) {
        if (ori == null || ori.length == 0) {
            return ori;
        }

        int length = ori.length - 1;
        int mid = length / 2;
        for (int i = mid; i >= 0; i--) {
            int k = ori[i];
            int j = ori[length - i];
            // swap
            ori[i] = j;
            ori[length - i] = k;
        }
        return ori;
    }

    public static void print(int[] ori) {
        for (int i = 0; i < ori.length; i++) {
            System.out.print(ori[i] + " ");
        }
        System.out.println();
    }
}
