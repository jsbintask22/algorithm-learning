package cn.jianbin.algorithm.array;

import cn.jianbin.algorithm.utils.Utils;

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
