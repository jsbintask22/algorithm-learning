package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/5/5 7:35 PM
 *
 * 剑指 Offer II 095. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 *
 * 提示：
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 *
 *
 * 注意：本题与主站 1143 题相同： https://leetcode-cn.com/problems/longest-common-subsequence/
 */
@UtilityClass
public class Off95M {

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("a", "aaa"));
    }

    public int longestCommonSubsequence(String text1, String text2) {

        // 解法； 动态规划；
        // 要求最长的公共子串； 从两个字符串中 各拿出一个字符以后，检查这个字符是否相同，然后加上前面的 i - 1个
        // 公共长度，则为当前长度；
        // 定义 dp[i][j] 为 t1[0..i] 和 t2[0..j] 的最长公共子串长度；
        // 则当 t1[i] == t2[j] 时，dp[i][j] = 1 + dp[i - 1][j - 1]
        // 当   t1[i] != t2[j] 是，dp[i][j] = Max(dp[i][j - 1], dp[i - 1][j])
        // 考虑到每个 i 和 j 都只跟前面的 i - 1 和 j - 1相关，所以 设 i <= t1.len   j <= t2.len

        int l1 = text1.length();
        int l2 = text2.length();

        int[][] dp = new int[l1 + 1][l2 + 1];

        int i = 1;

        while (i < l1 + 1) {

            int j = 1;

            while (j < l2 + 1) {
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);

                if (c1 == c2) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // 不等于，检查下前面哪个 大一点 再移动哪个；
                    int v1 = dp[i - 1][j];
                    int v2 = dp[i][j - 1];
                    dp[i][j] = Math.max(v1, v2);
                }

                // 再相等的 情况下，往上面 +1，其实结果是不会被改变的；
                j++;
            }

            i++;
        }

        return dp[l1][l2];
    }
}
