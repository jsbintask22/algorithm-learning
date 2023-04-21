package cn.jianbin.algorithm.array;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = Utils.genArr(10);
        Utils.printArr(arr);
        Utils.splitLine();

        heapSort(arr);
        Utils.printArr(arr);
    }

    public static int[] heapSort(int[] arr) {
        if (arr == null) {
            return null;
        }

        // O(N) * O(logN)
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            // 先把数组 构造成一个大根堆
            heapInsert(arr, i);
        }

        // 再一次对每个节点 进行 堆调整；  将第一个元素替换到最后
        // O(N) * O(logN)
        while (size > 0) {
            // 先替换
            swap(arr, 0, --size);
            // 替换完了后，重新调整成大根堆
            heapify(arr, size);
        }

        return arr;
    }


    public void heapify(int[] arr, int heapMaxIdx) {
        int left = 1;
        int right = left + 1;
        int index = 0;

        while (left < heapMaxIdx) {
            int largest = right < heapMaxIdx ? (arr[right] > arr[left] ? right : left) : left;
            if (arr[index] >= arr[largest]) {
                break;
            }

            swap(arr, index, largest);
            index = largest;
            left = (index << 1) + 1;
            right = left + 1;
        }
    }

    public void heapInsert(int[] arr, int index) {
        int parent = (index - 1) / 2;

        // 5 9 10 4
        while (arr[index] > arr[parent]) {
            swap(arr, index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    public void swap(int[] arr, int i1, int i2) {
        // a ^ a = 0
        // 0 ^ a = a 利用这原理.
        int temp = arr[i1] ^ arr[i2];
        arr[i1] = temp ^ arr[i1];
        arr[i2] = temp ^ arr[i1];
    }

}
