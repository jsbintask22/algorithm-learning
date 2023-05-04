package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/4/28 8:46 PM
 *
 * 剑指 Offer II 092. 翻转字符
 * 如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）的形式组成的，那么该字符串是 单调递增 的。
 *
 * 我们给出一个由字符 '0' 和 '1' 组成的字符串 s，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。
 *
 * 返回使 s 单调递增 的最小翻转次数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "00110"
 * 输出：1
 * 解释：我们翻转最后一位得到 00111.
 * 示例 2：
 *
 * 输入：s = "010110"
 * 输出：2
 * 解释：我们翻转得到 011111，或者是 000111。
 * 示例 3：
 *
 * 输入：s = "00011000"
 * 输出：2
 * 解释：我们翻转得到 00000000。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 20000
 * s 中只包含字符 '0' 和 '1'
 *
 *
 * 注意：本题与主站 926 题相同： https://leetcode-cn.com/problems/flip-string-to-monotone-increasing/
 */
@UtilityClass
public class Off92M {

    public static void main(String[] args) {
        System.out.println(minFlipsMonoIncr("00011000"));
    }

    public int minFlipsMonoIncr(String s) {
        // 解法； 动态规划；
        // 1. 定义动态规划数组，确定下标含义；
        // 设 dp[i][0] 为第 i 个字符为 0 时保持 “单调递增” 所需要的最小反转次数
        // 则 dp[i][1] 为第 i 个字符为 1 时保持“单调递增” 所需要的最小 反转次数

        // 2. 确定初始值；
        // 有了上面的定义；  则有 dp[0][0] = 0, dp[0][1] = 0 （第0个字符）
        // 当 i = 1 时表示第 1 个字符所需要的反转的次数； 则有
        // dp[1][0] = f(s[i - 1] == 1)    dp[1][1] = f(s[i - 1] == 0)
        // 假设当前 i 为 “1“， 则变成 ”0“ 需要翻转 1 次，否则为0
        // 假设当前 i 为 ”0"， 则变成 “1” 需要翻转 1 次，否则 为 0

        // 3. 确定状态转移方程；
        // 为了 保持 (1...i) 这些字符一直都是单调递增的，除了要看当前是 0 OR 1 外，这是已经确定的反转次数；
        // 所以 当 s[i - 1] = 0，时，前面 [0...i - 1] 只能为 0， 则有
        // dp[i][0] = dp[i - 1][0] + f(s[i - 1] == 1)
        // 并有 当 s[i - 1] = 1，时，前面 [0...i - 1] 可有 0 OR 1，取前面反转的最小值即可；
        // dp[i][1] = Min(dp[i - 1][0]，dp[i - 1][1] + f(s[i - 1] == 0))

        // 4. 确定遍历方式；
        // 从状态转移方程可得；  i 只与 i - 1 相关，直接从 0 ... n 遍历即可

        // 5. 确定结果；
        // 题目求最小反转次数，所以最后返回 Min(dp[n][0], dp[n][1]) 即可


        int len = s.length();
        int[][] dp = new int[len + 1][2];

        for (int i = 1; i < dp.length; i++) {
            // 当前为 0 ，前面只能为 0 ；
            char cur = s.charAt(i - 1);
            dp[i][0] = dp[i - 1][0] + reverse('0', cur);
            // 当前为 1， 前面可为 0 OR 1；  为 0 则当前不必反转；
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1] + reverse('1', cur));
        }

        return Math.min(dp[len][0], dp[len][1]);
    }

    private int reverse(char target, char cur) {
        if (target == cur) {
            return 0;
        }

        return 1;
    }
}
