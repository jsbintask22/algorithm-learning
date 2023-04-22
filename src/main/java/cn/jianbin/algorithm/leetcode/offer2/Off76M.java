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
        int[] arr1 = Utils.arr("1000,-1,2,1000,-50,100,50");
        Utils.printArr(arr1);

        heapSort(arr1);
        Utils.printArr(arr1);


        Utils.splitLine();
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


    public int findKthLargest3(int[] nums, int k) {
        heapSort2(nums, k);
        return nums[nums.length - k];
    }

    /**
     * 堆排序；
     * @param nums
     */
    public void heapSort(int[] nums) {
        // 先一个个插入构造大顶 堆；
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            heapInsert(nums, i);
        }

        // 构造了大顶堆以后，把 堆顶（最大） 一个个挪到 最后面.
        while (len > 1) {
            // 交换 堆顶 和 末尾；
            swap2(nums, 0, --len);

            // 重新构建 堆 关系. 找出两个子节点 谁更适合 当父节点；
            int cur = 0;
            int left = 1;
            int right = 2;

            int largest = right >= len ? left : (nums[left] >= nums[right] ? left : right);
            while (largest < len && nums[cur] < nums[largest]) {
                // 交换.
                swap2(nums, cur, largest);

                // 交换完了以后，再比较下面的；
                cur = largest;
                left = cur * 2 + 1;
                right = cur * 2 + 2;
                largest = right >= len ? left : (nums[left] >= nums[right] ? left : right);
            }
        }
    }

    /**
     * 对于找到 第k大的元素， 不需要全部重新排一遍， 排k遍 就够了.
     * @param nums
     * @param k
     */
    public void heapSort2(int[] nums, int k) {
        // 先一个个插入构造大顶 堆；
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            heapInsert(nums, i);
        }

        // 构造了大顶堆以后，把 堆顶（最大） 一个个挪到 最后面.
        while (len > 1 && k-- > 0) {
            // 交换 堆顶 和 末尾；
            swap2(nums, 0, --len);

            // 重新构建 堆 关系. 找出两个子节点 谁更适合 当父节点；
            int cur = 0;
            int left = 1;
            int right = 2;

            int largest = right >= len ? left : (nums[left] >= nums[right] ? left : right);
            while (largest < len && nums[cur] < nums[largest]) {
                // 交换.
                swap2(nums, cur, largest);

                // 交换完了以后，再比较下面的；
                cur = largest;
                left = cur * 2 + 1;
                right = cur * 2 + 2;
                largest = right >= len ? left : (nums[left] >= nums[right] ? left : right);
            }
        }
    }


    private void heapInsert(int[] arr, int idx) {
        // 把当前 idx 位置的元素插入大顶堆（根节点最大值）
        // 先找到 父节点；idx 已经在数组中最后一个位置了，
        int parent = (idx - 1) / 2;

        // 检查 idx 和 parent 谁的值大，谁大谁就做父节点；
        while (arr[idx] > arr[parent]) {
            // 交换.
            swap2(arr, idx, parent);

            // 交换了以后重新赋值 parent 和 idx
            idx = parent;
            parent = (idx - 1) / 2;
        }
    }

    private void swap2(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
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
