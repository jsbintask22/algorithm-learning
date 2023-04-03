package cn.jianbin.algorithm.array;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuickSort {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int[] a1 = Utils.genArr(7);
            Utils.printArr(a1);

            quickSort2(a1, 0, a1.length - 1);
            Utils.printArr(a1);
            Utils.splitLine();
        }
    }

    public void quickSort(int[] arr, int start, int end) {
        if (end <= start) {
            return;
        }

        int left = start;
        int pivot = end;
        int right = pivot - 1;

        // 这里就算 left 已经 = right 了，也要进去； 不然就直接走到下面的 交换了；
        // 如果此时是  28, 20  是不应该替换的。 所以要让它进去并且 在第二个while 判断
        while (left <= right) {
            // 先从左边找大于 pivot 的，因为左边本来都应该小于 pivot
            while (left <= right && arr[left] <= arr[pivot]) {
                left++;
            }

            // 再从右边找小于 pivot 的，因为右边本来都应该大于 pivot
            while (left <= right && arr[right] >= arr[pivot]) {
                right--;
            }

            // 经过了上面之后，要交换 left 和 right（就算相等）
            if (right > left) {
                swap(arr, left, right);
            }
        }

        // 从上面出来后，left = right
        swap(arr, left, pivot);

        // 再排 left 的左边和右边分别这样排序
        quickSort(arr, start, left - 1);
        quickSort(arr, left + 1, end);
    }

    public void quickSort2(int[] arr, int start, int end) {
        if (end <= start) {
            return;
        }

        if (end - start == 1) {
            if (arr[start] > arr[end]) {
                swap(arr, start, end);
            }
            return;
        }

        int left = start;
        int pivot = end;
        int right = pivot - 1;

        // 这里就算 left 已经 = right 了，也要进去； 不然就直接走到下面的 交换了；
        // 如果此时是  28, 20  是不应该替换的。 所以要让它进去并且 在第二个while 判断
        while (left < right) {
            // 先从左边找大于 pivot 的，因为左边本来都应该小于 pivot
            while (left < right && arr[left] <= arr[pivot]) {
                left++;
            }

            // 再从右边找小于 pivot 的，因为右边本来都应该大于 pivot
            while (left < right && arr[right] >= arr[pivot]) {
                right--;
            }

            // 经过了上面之后，要交换 left 和 right（就算相等）
            swap(arr, left, right);
        }

        // 从上面出来后，left = right
        swap(arr, left, pivot);

        // 再排 left 的左边和右边分别这样排序
        quickSort(arr, start, left - 1);
        quickSort(arr, left + 1, end);
    }


    private void swap(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }

        int temp = arr[l] ^ arr[r];
        arr[l] = temp ^ arr[l];
        arr[r] = temp ^ arr[r];
    }
}
