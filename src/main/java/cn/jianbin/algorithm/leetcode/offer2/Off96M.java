package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/5/5 8:11 PM
 *
 * 剑指 Offer II 096. 字符串交织
 * 给定三个字符串 s1、s2、s3，请判断 s3 能不能由 s1 和 s2 交织（交错） 组成。
 *
 * 两个字符串 s 和 t 交织 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交织 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 *
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * 示例 3：
 *
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 *
 *
 * 提示：
 *
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 *
 *
 * 注意：本题与主站 97 题相同： https://leetcode-cn.com/problems/interleaving-string/
 */
@UtilityClass
public class Off96M {

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        // 解法；动态规划；
        // 定义 dp[i][j] 为 s1[0...i] 和 s2[0...j] 能否交织组成 s3
        // 则当 s1[i] == s3[i + j] 时，
        // dp[i][j] 由前面的 dp[i - 1][j] 决定
        // 同样，当 s2[j] == s3[i + j] 是，
        // dp[i][j] 由前面的 dp[i][j - 1]决定;

        // 由于都是有前面的 元素决定，所以 i <= s1.length

        int l1 = s1.length();
        int l2 = s2.length();

        if (s3.length() != l1 + l2) {
            return false;
        }

        boolean[][] dp = new boolean[l1 + 1][l2 + 1];

        dp[0][0] = true;

        // 这里必须要从 0 开始，想象 dp[0][1] 也是有可能的；
        for (int i = 0; i < l1 + 1; i++) {
            for (int j = 0; j < l2 + 1; j++) {

                if (i > 0 && s1.charAt(i - 1) == s3.charAt(j + i - 1)) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(j + i - 1)) {
                    dp[i][j] = dp[i][j] || dp[i][j - 1];
                }
            }
        }

        return dp[l1][l2];
    }
}
