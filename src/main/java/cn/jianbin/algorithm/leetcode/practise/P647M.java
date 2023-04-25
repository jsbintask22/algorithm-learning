package cn.jianbin.algorithm.leetcode.practise;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aaron.zou
 * @date 2023/4/25 8:15 PM
 *
 * 647. 回文子串
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 *
 * 回文字符串 是正着读和倒过来读一样的字符串。
 *
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 */
@UtilityClass
public class P647M {

    public static void main(String[] args) {
        System.out.println(countSubstrings("12321"));
        System.out.println(subs);

        System.out.println(solution2("12321"));

        System.out.println(solution3("12321"));

    }

    public int solution3(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        // 先遍历列，再遍历行；只能这么操作.
        for (int col = 0; col < len; col++) {
            for (int row = 0; row <= col; row++) {
                dp[row][col] = s.charAt(col) == s.charAt(row) && (col - row < 2 || dp[row + 1][col - 1]);
            }
        }


        int ret = 0;
        for (int i = 0; i < dp.length; i++) {
            boolean[] row = dp[i];

            for (int j = 0; j < row.length; j++) {
                // i > j 没有意义，所以 j 不能从 0 开始；
                if (dp[i][j]) {
                    ret++;
                }
            }
        }

        return ret;
    }

    public int solution2(String s) {
        // 解法2； 动态规划；
        // 假设 dp[i][j] 为 [i,j] 这个区间是否为回文字符串
        // 所以；  当 i == j 时，必为 回文串
        // 同理；  当 j - i = 1 且 s[i] = s[j]  则  dp[i][j] 必为回文子串
        // 推导：  当 j - i > 1 且 s[i] = s[j] 且 dp[i + 1][j - 1] 也是回文串，则 dp[i][j] 是回文串；

        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            // i = j 必为 true
            dp[i][i] = true;

            if (i < len - 1) {
                // 当 j - i = 1 且 s[i] = s[j]  则  dp[i][j] 必为回文子串
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            }
        }

        // 需要注意的是， 如果 i > j 则没有意义，所以为false，为了 不让 i > j
        // 外层循环从 j 开始;
        for (int col = 2; col < len; col++) {
            for (int row = 0; row < col; row++) {
                dp[row][col] = s.charAt(row) == s.charAt(col) && dp[row + 1][col - 1];
            }
        }

        int ret = 0;
        for (int i = 0; i < dp.length; i++) {
            boolean[] row = dp[i];

            for (int j = 0; j < row.length; j++) {
                // i > j 没有意义，所以 j 不能从 0 开始；
                if (dp[i][j]) {
                    ret++;
                }
            }
        }

        return ret;

    }

    public int countSubstrings(String s) {
        char[] chars = s.toCharArray();

        int ret = 0;
        for (int i = 0; i < chars.length; i++) {
            // 中心可以是 1 个，也可以是两个.
            ret += count(s, i, i);
            ret += count(s, i, i + 1);
        }

        return ret;
    }

    List<String> subs = new ArrayList<>();
    public int count(String s, int start, int end) {
        // 统计 以 start - end 区间为起点，一共有多少个回文子串.
        int ret = 0;
        int len = s.length();

        while (start >= 0 && end < len && s.charAt(start) == s.charAt(end)) {
            subs.add(s.substring(start, end + 1));
            ret++;
            start--;
            end++;
        }

        return ret;
    }
}
