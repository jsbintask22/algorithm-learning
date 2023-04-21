package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.Arrays;

/**
 * @author aaron.zou
 * @date 2023/4/21 6:53 PM
 *
 * 剑指 Offer II 076. 数组中的第 k 大的数字
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 *
 *
 * 注意：本题与主站 215 题相同： https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */
@UtilityClass
public class Off76M {

    public static void main(String[] args) {
        int[] arr = Utils.arr("100,50");
        Utils.printArr(arr);

        quickSort(arr);
        Utils.printArr(arr);
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 0; i--) {
            if (--k == 0) {
                return nums[i];
            }
        }

        return -1;
    }


    public int findKthLargest2(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    public void quickSort(int[] nums) {
        // 快排；
        // 选定一个基点，排序后 该基点左边所有 值都小该基点，右边所有值都大于 该基点，然后再次对两边进行排序。[k...n][n + 1, m]

        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            // = 只有一个数，结束了   > 说明 start 已经跑到 end 后面去了，也结束了
            return;
        }

        // 选定 end 为基点；
        int pivot = end;
        int l = start;
        int r = end;

        // 1 5 2 6 3 7 2
        while (start < end) {

            // 一直移动到一个 大于 pivot 的位置.
            while ( start < end && nums[start] <= nums[pivot]) {
                start++;
            }

            // 一直移动到一个 小于 pivot 的未知.
            while ( start < end && nums[end] >= nums[pivot]) {
                end--;
            }


            if (start < end) {
                // 交换.
               swap(nums, start, end);
            }
        }

        swap(nums, start, pivot);

        // 左右两边分别排序；
        quickSort(nums, l, start - 1);
        quickSort(nums, start + 1, r);
    }

    public void swap(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }

        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}
