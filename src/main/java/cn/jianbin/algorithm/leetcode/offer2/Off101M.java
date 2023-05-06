package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/5/6 6:57 PM
 *
 * 剑指 Offer II 101. 分割等和子集
 * 给定一个非空的正整数数组 nums ，请判断能否将这些数字分成元素和相等的两部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：nums 可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：nums 不可以分为和相等的两部分
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 *
 *
 * 注意：本题与主站 416 题相同： https://leetcode-cn.com/problems/partition-equal-subset-sum/
 */
@UtilityClass
public class Off101M {

    public static void main(String[] args) {
        int[] arr = Utils.arr("1,2,3,5");
        Utils.printArr(arr);

        System.out.println(canPartition(arr));
    }

    public boolean canPartition(int[] nums) {
        // 解法；动态规划；
        // 本题的本质为 从数组中找出若干个 数字 使得 他们的和 为数组总和的一半，则返回true
        // 否则为 false
        // 所以如果数组总和 为 奇数，直接返回false 即可；

        // 为了找到 target 大小的 数字，每个数字都可以选或者不选.
        // 设 dp[i][j] 为从前（包括） i 个数字中选择若干后恰好有和 为 j 的可能； 则有
        // dp[0][0] 表示从 0 个数字中选择 和 为 0 的可能，为true  dp[0][0] = true
        // dp[i][j] = dp[i-1][j] || dp[i - 1][j - nums[i - 1]]

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;

        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        dp[0][0] = true;

        for (int i = 1; i < dp.length; i++) {
            boolean[] row = dp[i];
            for (int j = 0; j < row.length; j++) {
                // 如果当前没办法选了，那就只能跟前面一样了；
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[nums.length][target];
    }
}
