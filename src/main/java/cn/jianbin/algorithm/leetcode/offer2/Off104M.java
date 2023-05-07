package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.Arrays;

/**
 * @author jianbin.
 * @date 2023/5/7 15:27
 * <p>
 * 剑指 Offer II 104. 排列的数目
 * 给定一个由 不同 正整数组成的数组 nums ，和一个目标整数 target 。请从 nums 中找出并返回总和为 target 的元素组合的个数。数组中的数字可以在一次排列中出现任意次，但是顺序不同的序列被视作不同的组合。
 * <p>
 * 题目数据保证答案符合 32 位整数范围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 示例 2：
 * <p>
 * 输入：nums = [9], target = 3
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 * <p>
 * <p>
 * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
 * <p>
 * <p>
 * <p>
 * 注意：本题与主站 377 题相同：https://leetcode-cn.com/problems/combination-sum-iv/
 */
@UtilityClass
public class Off104M {

    public static void main(String[] args) {
        int[] arr = Utils.arr("1,2,3");

        System.out.println(combinationSum4(arr, 4));
    }

    public int combinationSum4(int[] arr, int target) {
        return dp2(arr, target);
    }


    public int dp2(int[] nums, int target) {
        int[] dp = new int[target +1];
        dp[0] = 1;

        for (int i = 0; i <= target; i++) {
            for (int num : nums) {
                if (num <= i){
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }


    public int dp(int[] nums, int target) {
        // 解法2： 动态规划；
        // 本题属于 完全背包问题 + 恰好满足的最优解；
        int len = nums.length;

        // dp[i][j] 为得到 恰好得到目标 j 的组合次数；
        int[][] dp = new int[len + 1][target + 1];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -2);
            // 得到目标为 0 的组合，只可能是一个都不选，
            dp[i][0] = 1;
        }
        dp[0][0] = 1;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j < nums[i - 1]) {
                    // 剩余容量 要小，没办法；
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 有足够剩余容量，有选或者不选；
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - nums[i - 1]] + 1);
                }
            }
        }

        return dp[len][target] == -2 ? 0 : dp[len][target];
    }

    int[] nums;
    int[] cache;
    int ret = 0;

    public void dfs2(int[] nums, int remained) {
        if (remained == 0) {
            // 剩余 remained = 0，说明这个组合排满了；
            ret++;
            return;
        }

        // 如果已经没得拍了 OR remained < 0 了，那就都是不合规了；
        if (remained < 0) {
            return;
        }

        // 走到这里，说明还能继续排列；
        for (int num : nums) {
            if (remained < num) {
                continue;
            }

            // 选择当前元素；
            // 这里要记住一点，对于不选择当前元素，其实已经被这个 for 循环代替了；
            dfs2(nums, remained - num);
        }

    }

    /**
     * 标准回溯写法； 答案必须满足 顺序摆放
     */
    public void dfs1(int[] nums, int idx, int remained) {
        if (remained == 0) {
            // 剩余 remained = 0，说明这个组合排满了；
            ret++;
            return;
        }

        // 如果已经没得拍了 OR remained < 0 了，那就都是不合规了；
        if (remained < 0 || nums.length == idx) {
            return;
        }


        // 还没到最后，继续回溯；
        // 选择当前元素；
        dfs1(nums, idx, remained - nums[idx]);

        // 不选择；
        dfs1(nums, idx + 1, remained);
    }
}
