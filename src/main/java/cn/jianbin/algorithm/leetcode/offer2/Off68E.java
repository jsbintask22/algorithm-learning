package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/17 20:54
 *
 * 剑指 Offer II 068. 查找插入位置
 * 给定一个排序的整数数组 nums 和一个整数目标值 target ，请在数组中找到 target ，并返回其下标。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: nums = [1,3,5,6], target = 0
 * 输出: 0
 * 示例 5:
 *
 * 输入: nums = [1], target = 0
 * 输出: 0
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为无重复元素的升序排列数组
 * -104 <= target <= 104
 *
 *
 * 注意：本题与主站 35 题相同： https://leetcode-cn.com/problems/search-insert-position/
 */
@UtilityClass
public class Off68E {
    public static void main(String[] args) {
        System.out.println(searchInsert(Utils.arr("1,3,5,6"), 5));
    }

    public int searchInsert(int[] nums, int target) {
        // 解法； 二分查找法；
        // 题目要求如果没有这样的 数字， 则返回第一个大于 target 的数的位置（插入位置）
        // 则直接在二分查找中 left 指针即可（因为 left 总是因为 arr[left] 的位置小于 target 而移动的，
        // 所以移动以后 left 应该就是要大于 target（如果是小于，只会移动 right）

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left >> 1);

            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                // 大于，right 左移动；
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
