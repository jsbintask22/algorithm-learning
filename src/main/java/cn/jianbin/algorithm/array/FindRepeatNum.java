package cn.jianbin.algorithm.array;

import cn.jianbin.algorithm.utils.Utils;

/**
 * @author aaron.zou
 * @date 2021/12/11 12:37 下午
 *
 * 找出数组中重复的数字。
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0 ～ n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 */
public class FindRepeatNum {

    public static void main(String[] args) {
        int arr[] = new int[]{0, 7, 2, 3, 3, 3, 1, 2, 3};
        Utils.printArr(arr);

        int arr2[] = new int[10];
        Utils.printArr(arr2);

        System.out.println(findRepeatNum(arr));
        System.out.println(findRepeatNum(arr2));


        System.out.println(findRepeatNum2(arr));
        System.out.println(findRepeatNum2(arr2));

        int[] arr3 = new int[]{2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNum2(arr3));

    }

    /**
     * @param arr 1 2 3 4 4 3 5 7
     */
    public static int findRepeatNum(int[] arr) {
        int[] index = new int[arr.length];

        for (int i = 0, n = arr.length; i < n; i++) {
            index[arr[i]]++;

            if (index[arr[i]] > 1) {
                return arr[i];
            }
        }


        return -1;
    }

    public static int findRepeatNum2(int[] nums) {
        for (int i = 0, n = nums.length; i < n; i++) {
            if (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }

                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;

                --i;
            }
        }

        return -1;
    }
}
