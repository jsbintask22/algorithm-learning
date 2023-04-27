package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/4/27 8:37 PM
 *
 * 剑指 Offer II 089. 房屋偷盗
 * 一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响小偷偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *
 * 输入：nums = [2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 *
 * 注意：本题与主站 198 题相同： https://leetcode-cn.com/problems/house-robber/
 */
@UtilityClass
public class Off89M {

    public static void main(String[] args) {

    }

    public int rob(int[] nums) {
        // 解法； 动态规划；
        // 假设 nums 长度为 n
        // 当 n = 1， 则 nums[0] 为最大值
        // 当 n = 2 ，则 Max(nums[1], nums[0]) 为最大值；
        // 设 dp[i] 为前 i 座房子所能盗窃到的最大金额；
        // 则 dp[i] = Max(nums[i] + nums[i - 2], nums[i - 1])  比较 选择当前房子 + 往前面2个 房子 OR 直接选择前面 1 个房子最大值；

        int[] dp = new int[nums.length];

        for (int i = 0; i < dp.length; i++) {
            if (i == 0) {
                dp[i] = nums[i];
            } else if (i == 1) {
                dp[i] = Math.max(nums[i], nums[i - 1]);
            }else {
                dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
            }

        }

        return dp[dp.length - 1];
    }
}
