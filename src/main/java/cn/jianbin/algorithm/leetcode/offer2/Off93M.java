package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/5/4 20:04
 *
 * 剑指 Offer II 093. 最长斐波那契数列
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 *
 * n >= 3
 * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 *
 * （回想一下，子序列是从原序列  arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 *
 *
 *
 * 示例 1：
 *
 * 输入: arr = [1,2,3,4,5,6,7,8]
 * 输出: 5
 * 解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
 * 示例 2：
 *
 * 输入: arr = [1,3,7,11,12,14,18]
 * 输出: 3
 * 解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
 *
 *
 * 提示：
 *
 * 3 <= arr.length <= 1000
 * 1 <= arr[i] < arr[i + 1] <= 10^9
 *
 *
 *
 * 注意：本题与主站 873 题相同： https://leetcode-cn.com/problems/length-of-longest-fibonacci-subsequence/
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
