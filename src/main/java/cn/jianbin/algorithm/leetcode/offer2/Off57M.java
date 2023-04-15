package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.TreeSet;

/**
 * @author aaron.zou
 * @date 2023/4/15 12:46 PM
 * 剑指 Offer II 057. 值和下标之差都在给定的范围内
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 *
 *
 * 注意：本题与主站 220 题相同： https://leetcode-cn.com/problems/contains-duplicate-iii/
 */
@UtilityClass
public class Off57M {

    public static void main(String[] args) {
        int[] arr = Utils.arr("1,2,2,3,4,5");

        System.out.println(containsNearbyAlmostDuplicate(arr, 3, 0));

        System.out.println(containsNearbyAlmostDuplicate(Utils.arr("7,1,3"), 2, 3));

    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 记得，不知道什么原理；
        int len = nums.length;
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < len; i++) {
            Long cell = set.ceiling((long) nums[i] - (long) t);
            if (cell != null && cell <= (long) nums[i] + (long) t) {
                return true;
            }

            set.add((long) nums[i]);
            if (i - k >= 0) {
                set.remove((long) nums[i - k]);
            }
        }

        return false;
    }
}
