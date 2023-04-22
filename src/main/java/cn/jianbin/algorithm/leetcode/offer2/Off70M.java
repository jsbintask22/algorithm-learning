package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/18 19:14
 *
 * 剑指 Offer II 070. 排序数组中只出现一次的数字
 * 给定一个只包含整数的有序数组 nums ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 *
 *
 * 注意：本题与主站 540 题相同：https://leetcode-cn.com/problems/single-element-in-a-sorted-array/
 */
@UtilityClass
public class Off70M {

    public static void main(String[] args) {

    }

    public int singleNonDuplicate(int[] nums) {
        // 解法； 2分查找；
        // 在这样一个数组中   1 1 2 3 3 4 4 5 5
        // 假设  2（下标2） 就是要找的值，则可以观察得到;
        //  2 的左边  arr[y] = arr[y + 1]  此时 y 一定是 偶数
        // 在 2 的右边， arr[z] = arr[z + 1] 此时 z 一定是 奇数
        // 则可以通过 二分， 每次得到 mid，假设  arr[mid] = arr[mid + 1]
        // 则  2 肯定在 mid 的右边， left 指针右移动,
        // 假设     arr[mid] = arr[mid - 1]，则 mid 肯定在 2 的左边，
        // right 右移动.
        // 假设不满足上面条件，说明  2 就找到了.

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left >> 1);

            if (nums[mid] == nums[mid + 1]) {
                left = mid + 1;
            } else if (nums[mid] == nums[mid - 1]) {
                right = mid - 1;
            } else {
                return left;
            }
        }

        return left;
    }
}
