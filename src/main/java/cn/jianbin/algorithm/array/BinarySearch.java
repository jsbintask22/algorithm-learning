package cn.jianbin.algorithm.array;

import cn.hutool.core.util.RandomUtil;
import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BinarySearch {

    public static void main(String[] args) {
        int oldCapacity = 2;
        for (int i = 0 ; i < 10; i++) {
            oldCapacity = oldCapacity + (oldCapacity >> 1);
            System.out.println("i : " + i + ", " + oldCapacity);
        }


        for (int i = 0; i < 10; i++) {
            int[] arr = genArr(20);
            Utils.printArr(arr);
            int minIdx = searchPartialMinIdx(arr);
            System.out.println("partial min idx = " + minIdx + " arr[" + minIdx + "] = " + arr[minIdx]);

            Sorts.mergerArr(arr, 0, arr.length - 1);
            int targetIdx = RandomUtil.randomInt(0, arr.length);
            int target = arr[targetIdx];

            Utils.printArr(arr);
            System.out.println("target = " + target + ",  target_idx = " + targetIdx +
                    ", search_idx = " + binSearch(arr, target));
            System.out.println();
        }

        System.out.println("=================================================================");
        System.out.println();

    }

    public int binSearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        // 二分查找，每次二分一下，找到即返回，没找到则缩小边界值
        // [1, 2, 3]   1
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target > arr[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }

    public int searchFarLeftIdxMoreThanTarget(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int l = 0;
        int r = arr.length - 1;
        int ret = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) {
                ret = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ret;
    }

    public int searchPartialMinIdx(int[] arr)  {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        // 只有一个数的情况； 直接返回该数
        if (arr.length == 1) {
            return 0;
        }

        // [1, 2]
        if (arr[0] < arr[1]) {
            return 0;
        }

        // [6, 5]
        if (arr[arr.length - 2] > arr[arr.length - 1]) {
            return arr.length - 1;
        }

        int l = 0;
        int r = arr.length - 1;

        // [2, 1, 3, 5, 8, 9]
        while (l < r - 1) {
            int mid = l + (r - l) / 2;

            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                // 满足，则是局部最小；
                return mid;
            }

            // 在不满足的情况下，则要么坐标大于 mid，要么右边大于
            // 如果是左边大于 mid
            // 左     中     右
            // 已知   左 > 中  且   右 > 中不存在
            // 还存在   左 > 中， 右 < 中
            //         左 < 中，右 < 中
            //         左 < 中，右 > 中
            if (arr[mid - 1] < arr[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return arr[l] < arr[r] ? l : r;
    }

    public int[] genArr(int len) {
        int[] ret = new int[len];
        ret[0] = RandomUtil.randomInt(0, 100);

        for (int i = 1; i < len; i++) {
            do {
                ret[i] = RandomUtil.randomInt(0, 100);
            } while (ret[i - 1] == ret[i]);
        }

        return ret;
    }
}
