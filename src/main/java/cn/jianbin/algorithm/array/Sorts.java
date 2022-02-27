package cn.jianbin.algorithm.array;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author aaron.zou
 * @date 2021/12/19 10:36 上午
 * <p>
 * 各种排序
 */
@UtilityClass
public class Sorts {

    public static void main(String[] args) {
        System.out.println("select sort: ");
        int[] arr = Utils.genArr(7);
        Utils.printArr(arr);

        selectSort(arr);
        Utils.printArr(arr);
        System.out.println();

        System.out.println("bubble sort: ");
        int[] arr2 = Utils.genArr(7);
        Utils.printArr(arr2);

        bubbleSort(arr2);
        Utils.printArr(arr2);
        System.out.println();


        System.out.println("insert sort: ");
        int[] arr3 = Utils.genArr(7);
        Utils.printArr(arr3);

        insertSort(arr3);
        Utils.printArr(arr3);
        System.out.println();


        System.out.println("shell sort: ");
        int[] arr4 = Utils.genArr(7);
        Utils.printArr(arr4);

        shellSort(arr4);
        Utils.printArr(arr4);
        System.out.println();


        System.out.println("quick sort: ");
        int[] arr5 = Utils.genArr(7);
        Utils.printArr(arr5);

        quickSort(arr5, 0, arr5.length - 1);
        Utils.printArr(arr5);
        System.out.println();

        String arrStr = "21, 29, 42, 78, 81, 41, 98";
        int[] arr6 = Utils.arr(arrStr);
        System.out.println("quick sort: ");
        Utils.printArr(arr6);

        quickSort(arr6, 0, arr6.length - 1);
        Utils.printArr(arr6);
        System.out.println();

        System.out.println("merger sored arr:");
        int[] sortedArr = mergerSortedArr(arr5, arr6);
        Utils.printArr(sortedArr);
        System.out.println();


        System.out.println("merger sored arr22:");
        int[] arr8 = Utils.genArr(20);
        Utils.printArr(arr8);
        mergerArr(arr8, 0, arr8.length - 1);
        Utils.printArr(arr8);
        System.out.println();


        System.out.println("count sort:");
        int[] arr9 = Utils.genArr(20);
        Utils.printArr(arr9);
        countSort(arr9);
        Utils.printArr(arr9);
        System.out.println();

        System.out.println(binarySearch(sortedArr, 21));
        System.out.println(binarySearch(sortedArr, 98));
        System.out.println(binarySearch(sortedArr, 78));
    }

    /**
     * 选择排序
     */
    public int[] selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        // 选择排序； 遍历 n 次；最初最小值，然后交换
        for (int i = 0, s = arr.length; i < s - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < s; j++) {
                if (arr[minIdx] > arr[j]) {
                    minIdx = j;
                }
            }

            // 交换
            if (i != minIdx) {
                swap(arr, minIdx, i);
            }
        }

        return arr;
    }

    /**
     * 冒泡排序
     */
    public int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        // 将最大值一个个往上面冒泡
        for (int i = 0, s = arr.length; i < s - 1; i++) {

            for (int j = 0; j < s - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }

        return arr;
    }


    /**
     * 插入排序
     */
    public int[] insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        // 插入排序； 假设一个数组的前半部分已经排好序了；  这样往前面插入的时候，只需要比较到最后一个大于
        // 即可
        for (int i = 1, s = arr.length; i < s; i++) {

            // 一共要进行 length - 1 次比较（一直要交换到最后面一个元素）
            for (int j = i - 1; j >= 0; j--) {
                // 再每个当前比较元素的前面数组都是有序的； 如果遇到当前元素 < 前面一个元素则交换
                // 否则； 当前元素大于前面一个元素，直接终止（因为前面都是有序的）
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                } else {
                    // 这里如果不想 break； 把上面的比较移到for里面去
                    break;
                }
            }

        }

        // 扩展：希尔排序；  在插入排序的基础上改进；  如果一个数组很长并且没有顺序，后面的小数往前面排效率并不高；
        // 直接跳级； 每间隔 4 个数再排

        return arr;
    }

    /**
     * 希尔排序
     */
    public int[] shellSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        // 定一个间隔
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {

            for (int i = gap; i < arr.length; i++) {

                for (int j = i - gap; j >= gap - 1; j = j - gap) {
                    if (arr[j] > arr[j + gap]) {
                        swap(arr, j, j + gap);
                    } else {
                        break;
                    }
                }
            }
        }

        return arr;
    }

    /**
     * 快速排序
     *
     * 脑海中一定要想象交换过程；   左指针右移、右指针左移； 最后考虑极值情况；  最大值、最小值在最右边
     */
    public int[] quickSort(int[] arr, int start, int end) {
        if (arr == null || end - start < 1) {
            return arr;
        }

        // 找到一个轴 pivot, 把小于这个轴的数放左边、大于这个轴的放右边；
        int left = start;
        int right = end - 1;

        int pivot = end;

        while (left <= right) {
            // 一直查找，找到一个 左边大于 pivot 的值
            while (arr[left] <= arr[pivot] && left <= right) {
                left++;
            }

            // 右边也一直查找，找到一个 右边 小于 pivot 的值
            while (right > -1 && arr[right] >= arr[pivot] && left <= right) {
                right--;
            }

            // 从上面两个循环出来以后； 这两个值要做交换（不相等的情况)
            if (right > left) {
                swap(arr, left, right);
            }
        }

        // 能够从上面走到这里； 有两个情况； 第一 left >= right
        // 考虑只有两个 元素的情况
        swap2(arr, left, pivot);

        quickSort(arr, 0, left - 1);
        quickSort(arr, left + 1, end);

        return arr;

    }

    /**
     *  合并两个有序的数组
     */
    public int[] mergerSortedArr(int[] arr1, int [] arr2) {
        if (arr1 == null || arr1.length == 0) {
            return arr2;
        }

        if (arr2 == null) {
            return arr1;
        }

        int[] merger = new int[arr1.length + arr2.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                merger[k++] = arr1[i++];
            } else {
                merger[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            merger[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            merger[k++] = arr2[j++];
        }

        return merger;
    }

    /**
     *  有了下面的基础，接下来要把范围缩小，一直缩小到长度只生效 1 或者 2
     */
    public void mergerArr(int[] arr, int left, int right) {
        // 递归跳出 break 的条件是  只有一个元素，也就是 left = right；
        if (left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergerArr(arr, left, mid);

        mergerArr(arr, mid + 1, right);

        mergerArr(arr, left, mid + 1, right);
    }


    /**
     *  对单个数组进行归并排序；  设置左右指针，想象两边的数组都应排好序了
     */
    public void mergerArr(int[] arr, int left, int mid, int right) {
        if (arr == null || left >= mid) {
            return;
        }

        int arr1Point = left;
        int arr2Point = mid;
        // 申请新的空间
        int[] tempArr = new int[right - left + 1];
        int k = 0;

        while (arr1Point < mid && arr2Point < (right + 1)) {
            tempArr[k++] = arr[arr1Point] <= arr[arr2Point] ? arr[arr1Point++] : arr[arr2Point++];
        }

        while (arr1Point < mid) {
            tempArr[k++] = arr[arr1Point++];
        }

        while (arr2Point < (right + 1)) {
            tempArr[k++] = arr[arr2Point++];
        }

        // 新申请的数组排好序了，重新放回原来的数组
        for (int i = 0; i < tempArr.length; i++) {
            arr[left + i] = tempArr[i];
        }
    }

    /**
     *  计数排序； 适合量大但是 值 范围不大的类型
     */
    public void countSort(int[] arr) {
        // 值 -> 次数
        Map<Integer, Integer> countMap = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            countMap.put(key, countMap.containsKey(key) ? countMap.get(key) + 1 : 1);
        }

        AtomicInteger idx = new AtomicInteger();
        countMap.forEach((key, val) -> {
            while (val-- > 0) {
                arr[idx.getAndIncrement()] = key;
            }
        });

    }

    public int binarySearch(int[] arr, int val) {
        if (arr == null) {
            return -1;
        }

        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (end + start) / 2;
            if (arr[mid] == val) {
                return mid;
            }

            if (arr[mid] > val) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }


    private void swap(int[] arr, int i, int j) {
        // a = x, b = y;
        // a = a ^ b = x ^ y;
        // b = a ^ b = x ^ y ^ y = x;
        // a = a ^ b = x ^ y ^ x = y;

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    private void swap2(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
