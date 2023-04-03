package cn.jianbin.algorithm.array;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MergerSort {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int[] arr = Utils.genArr(10);
            Utils.printArr(arr);

            int length = arr.length;
            merger(arr, 0, length - 1);
            Utils.printArr(arr);
            Utils.splitLine();
        }
    }

    public void merger(int [] arr, int start, int end) {
        if (end == start) {
            return;
        }

        //  >> 优先级是最低的！！！！！
        // 注意；这里的mid 怎么算很重要， mid 放到那边也很重要！！！！
        int mid = start + (end - start >> 1);
        // 让 start - mid 有序
        merger(arr, start, mid);

        // 让 mid + 1 - end 有序
        merger(arr, mid + 1, end);

        // 两个都有序了，合并
        merger(arr, start, end, mid);
    }


    public void merger(int[] arr, int l, int r, int mid) {
        if (r == l) {
            return;
        }

        int[] temp = new int[r - l + 1];
        int s1 = l;
        int s2 = mid + 1;
        int k = 0;

        while (s1 <= mid && s2 <= r) {
            temp[k++] = arr[s1] < arr[s2] ? arr[s1++] : arr[s2++];
        }

        while (s1 <= mid) {
            temp[k++] = arr[s1++];
        }

        while (s2 <= r) {
            temp[k++] = arr[s2++];
        }

        s1 = 0;
        while (s1 < k) {
            arr[l++] = temp[s1++];
        }
    }
}
