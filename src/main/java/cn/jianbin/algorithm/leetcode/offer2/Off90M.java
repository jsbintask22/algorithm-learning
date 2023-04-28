package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/4/28 7:26 PM
 *
 * 剑指 Offer II 090. 环形房屋偷盗
 * 一个专业的小偷，计划偷窃一个环形街道上沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 *
 *
 * 注意：本题与主站 213 题相同： https://leetcode-cn.com/problems/house-robber-ii/
 */
@UtilityClass
public class Off90M {

    public static void main(String[] args) {
        int[] arr = Utils.arr("0");
        Utils.printArr(arr);

        System.out.println(rob(arr));
    }

    public int rob(int[] nums) {
        // 解法； 动态规划；
        // 设 dp[i] 为前 i 座房子所能盗窃到的最大金额；
        // 则当 i = 0，时；  dp[i] = nums[0]，直接盗窃 第一个 房子即可
        // 当  i = 1，是； dp[i] = Max(nums[0], nums[1]), 选择一个最大值；
        // 当  i > 1 时，所以有  dp[i] = Max(dp[i - 2] + nums[i], dp[i - 1]) 取一个最大值；

        // 但是因为房屋是环形的，所以 答案不能直接选 dp[n - 1]
        // 当选了 第一座房子，则不能选 最后一座。 此时 dp 长度为 [0, n - 2]
        // 当选了 最后一座房子，则不能选第一座房子，此时 dp 长度为 [1, n - 1]

        if (nums.length == 1) {
           return nums[0];
        }


        // 选取两个 dp 中的最大值即可；
        int[] dp1 = genDp(nums, 0, nums.length - 2);

        int[] dp2 = genDp(nums, 1, nums.length - 1);

        return Math.max(dp1[dp1.length - 1], dp2[dp2.length - 1]);
    }

    private int[] genDp(int[] nums, int start, int end) {
        // 在这个取值范围上 生成 dp

        int[] dp = new int[end - start + 1];

        for (int i = start; i <= end; i++) {
            if (i == start) {
                dp[i - start] = nums[i];
            } else if (i == start + 1) {
                dp[i - start] = Math.max(nums[i], nums[i - 1]);
            } else {
                dp[i - start] = Math.max(nums[i] + dp[i - start - 2], dp[i - start - 1]);
            }
        }

        return dp;
    }
}
