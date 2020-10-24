package cn.jianbin.algorithm.sort;

import lombok.SneakyThrows;

import java.util.Arrays;

/**
 * @author jianbin
 * @date 2020/9/12 18:44
 */
public class BubbleSort {

    @SneakyThrows
    public static void main(String[] args) {
        int[] ori = new int[]{10, 3, 4, 10, 100, 0, -1, 2, 3};
        print(bubble(ori));

        ori = new int[]{10, 3, 4, 10, 100, 0, -1, 2, 3};
        print(select(ori));

        ori = new int[]{10, 3, 4, 10, 100, 0, -1, 2, 3};
        print(insert(ori));

        ori = new int[]{10, 3, 4, 10, 100, 0, -1, 2, 3};
        print(merger(ori));
    }

    public static int[] bubble(int [] ori) {
        int length = ori.length;

        // O(n^2)   0(1)
        //  核心： 每次将最大的的数 冒泡 到最上方
        for (int i = 0; i < ori.length; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                int current = ori[j];
                int next = ori[j + 1];

                if (current > next) {
                    ori[j] = next;
                    ori[j + 1] = current;
                }
            }
        }

        return ori;
    }

    public static int[] select(int[] ori) {

        // 0(N^2)   O(1)
        // 核心： 从第一个数开始， 每一个依次和后面所有元素比较大小
        for (int i = 0; i < ori.length - 1; i++) {
            int minIdx = i;
            for (int j = i +1; j < ori.length; j++) {
                if (ori[minIdx] > ori[j]) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                int temp = ori[i];
                ori[i] = ori[minIdx];
                ori[minIdx] = temp;
            }
        }

        return ori;
    }

    public static int[] insert(int[] ori) {
        // O(N^2)  0(1)
        // 核心： 从第一个元素开始，认为它是一个有序数组，后面的元素向该数组比较，直到找到大于的元素，然后向后推

        int length = ori.length;
        for (int i = 0; i < length - 1; i++) {
            int currentIdx = i;
            int nextIdx = i + 1;

            while (currentIdx >= 0 && ori[currentIdx] > ori[nextIdx]) {
                int temp = ori[nextIdx];
                ori[nextIdx] = ori[currentIdx];
                ori[currentIdx] = temp;

                currentIdx--;
                nextIdx--;
            }
        }

        return ori;
    }

    public static int[] shell(int[] ori) {
        // 希尔排序： 插入排序改进版  突破 O(N^2)
        int length = ori.length;
        for (int gap = length / 2; gap > 0; gap = gap / 2) {

            for (int i = gap; i < length; i++) {

            }
        }

        return ori;
    }


    public static int[] merger(int[] ori) {
        // 归并排序， 0(NlogN) 0(N)

        if (ori.length == 1) {
            return ori;
        }
        int mid = ori.length / 2;
        int[] left  = Arrays.copyOfRange(ori, 0, mid);
        int[] right = Arrays.copyOfRange(ori, mid, ori.length);

        return doMerger(merger(left), merger(right));
    }

    private static int[] doMerger(int[] left, int[] right) {
        int rl = 0;
        int ll = 0;
        int maxLt = left.length;
        int maxRt = right.length;
        int[] result = new int[maxLt + maxRt];
        int idx = 0;

        while (rl < maxRt && ll < maxLt) {
            if (left[ll] < right[rl]) {
                result[idx] = left[ll];
                ll++;
            } else {
                result[idx] = right[rl];
                rl++;
            }

            idx++;
        }

        while (rl < maxRt) {
            result[idx++] = right[rl++];
        }
        while (ll < maxLt) {
            result[idx++] = left[ll++];
        }

        return result;
    }

    private static int[] quick(int[] ori) {
        int length = ori.length;

        int pivot = ori[length - 1];
        int midIdx = length / 2;
        int idx = 0;

        for (int i = 0; i < length; i++) {
            if (ori[i] > pivot) {
                int temp = ori[i];
                ori[i] = ori[midIdx];
                ori[midIdx] = temp;


            }
        }

        return null;
    }

    public static void print(int[] ori) {
        for (int i = 0; i < ori.length; i++) {
            System.out.print(ori[i] + " ");
        }
        System.out.println();
    }

}
