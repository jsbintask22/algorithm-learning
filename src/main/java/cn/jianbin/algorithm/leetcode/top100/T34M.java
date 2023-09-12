package cn.jianbin.algorithm.leetcode.top100;

import cn.jianbin.algorithm.utils.Utils;

/**
 * @author jianbin.
 * @date 2023/8/13 9:37
 */
public class T34M {

    public static void main(String[] args) {
        int[] arr = Utils.arr("5,7,7,8,8,10");

        Utils.printArr(searchRange(arr, 8));
    }



    public static int[] searchRange(int[] arr, int target) {


        int start = 0;
        int end = arr.length - 1;

        int[] ret = new int[] {-1, -1};

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                // 等于，不用管；
                // 找到了，往左右两边扩展；

                int minMid = mid;
                while (minMid >= 0 && arr[minMid] == target) {
                    ret[0] = minMid;
                    minMid--;

                }

                int maxMid = mid;
                while (maxMid < arr.length && arr[maxMid] == target) {
                    ret[1] = maxMid;
                    maxMid++;

                }

                break;

            } else if (arr[mid] > target) {
                // 中间位置比 target 大，还要往左边移动.
                end--;

            } else {
                // start 是最后一个满足 arr[mid] > target 的元素
                start++;
            }
        }

        return ret;

    }
}
