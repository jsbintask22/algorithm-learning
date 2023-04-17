package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/17 20:18
 *
 * 剑指 Offer II 067. 最大的异或
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * 示例 2：
 *
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 105
 * 0 <= nums[i] <= 231 - 1
 *
 *
 * 注意：本题与主站 421 题相同： https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
@UtilityClass
public class Off67M {

    public static void main(String[] args) {

    }

    public int findMaximumXOR(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return 0;
        }


        int max = nums[0] ^ nums[1];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max =Math.max(nums[i] ^ nums[j], max);
            }
        }

        return max;
    }
}
