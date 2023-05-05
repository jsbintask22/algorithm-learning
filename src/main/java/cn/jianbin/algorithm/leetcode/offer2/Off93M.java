package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/5/4 20:04
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
public class Off93M {

    public static void main(String[] args) {
        int[] arr = Utils.arr("1,2,3,4,5,6,7,8");

        Utils.printArr(arr);

        System.out.println(lenLongestFibSubseq(arr));
    }

    public int lenLongestFibSubseq(int[] arr) {
        // 解法； 动态规划；
        // 每个斐波那契数列。 a[i] = a[j] + a[k]
        // 定义dp数组； dp[i][j] 为以 a[i] a[j] 作为后两位固定元素的情况下
        // 斐波那契的长度； 则有 dp[i < 2][j] = 0，i 从 2开始（前面最少有两个数）
        // 在固定了后两位数的情况下，如果 有 上面的等式成立，则是斐波那契数列； 长度为 1 + dp[j][k]

        int len = arr.length;
        int[][] dp = new int[len][len];
        int max = 0;

        // i 从 2 开始；
        for (int i = 2; i < len; i++) {
            // 给定前面两个的值；
            int k = 0, j = i - 1;

            while (k < j) {
                if (arr[i] == arr[j] + arr[k]) {
                    // 已经相等了，当前这个位置就是 斐波那契数列；
                    dp[i][j] = Math.max(1 + dp[j][k], 3);
                    max = Math.max(max, dp[i][j]);

                    k++;
                    j--;
                } else if (arr[i] > arr[j] + arr[k]) {
                    // 当前位置打了， 把 k 往右边移动；
                    k++;
                } else {
                    // 当前位置小了，把 j 往左边移动；
                    j--;
                }

            }
        }

        return max;
    }
}
